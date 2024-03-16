package chat.admin;


import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientManager implements Runnable {

    private final Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String name;

    public final static ArrayList<ClientManager> clients = new ArrayList<>();

    public ClientManager(Socket socket) {
        this.socket = socket;
        try {
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            name = bufferedReader.readLine();
            clients.add(this);
            System.out.println(name + " подключился к чату.");
            broadcastMessage("Admin: " + name + " подключился к чату.");
        } catch (IOException e) {
            closeEverything(socket, bufferedReader, bufferedWriter);
        }
    }

    @Override
    public void run() {
        String messageFromClient;

        while (socket.isConnected()) {
            try {
                messageFromClient = bufferedReader.readLine();
                broadcastMessage(messageFromClient);
            } catch (IOException e) {
                closeEverything(socket, bufferedReader, bufferedWriter);
                break;
            }
        }
    }

    //транслирование сообщений другим клиентам
    private void broadcastMessage(String message) {
        try {

                if (message.startsWith("@")) {
                    String[] units = message.split("\\s", 2);
                    if (units.length > 1) {
                        String recipient = units[0].substring(1);
                        for (ClientManager client : clients) {
                            if (client.equals(recipient)) {
                                client.bufferedWriter.write(units[1]);
                                //client.bufferedWriter.newLine();
                                //client.bufferedWriter.flush();
                                break;
                            }

                        }
                    }
                } else {
                    for (ClientManager client : clients) {
                        if (!client.name.equals(name)) {
                            client.bufferedWriter.write(message);
                            client.bufferedWriter.newLine();
                            client.bufferedWriter.flush();
                        }
                    }
                }


        } catch(IOException e){
        closeEverything(socket, bufferedReader, bufferedWriter);
    }

}


//        for (ClientManager client: clients) {
//            try {
//                if (!client.name.equals(name)) {
//                    client.bufferedWriter.write(message);
//                    client.bufferedWriter.newLine();
//                    client.bufferedWriter.flush();
//                }
//            }
//                    catch (IOException e) {
//                        closeEverything(socket, bufferedReader, bufferedWriter);
//                    }
//                }
//        }


    private void closeEverything(Socket socket, BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        // Удаление клиента из коллекции
        removeClient();
        try {
            // Завершаем работу буфера на чтение данных
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            // Завершаем работу буфера для записи данных
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            // Закрытие соединения с клиентским сокетом
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void removeClient() {
        clients.remove(this);
        System.out.println(name + " покинул чат.");
        broadcastMessage("Server: " + name + " покинул чат.");
    }

}
