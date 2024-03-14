package textileDB;

import textile.Textile;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ConnectionServer {
    private static final String URL = "jdbc:mysql://localhost:3366";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";
    public static Connection connection;


    public static void connect() {
        try {

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Подключение к Базе Данных - Успешно!");

            createDB(connection);
            System.out.println("Создание Базы Данных - Успешно!");

            // Использование базы данных
            useDatabase(connection);
            System.out.println("Использование Базы Данных");

            //Создание таблицы
            createTable(connection);
            System.out.println("Создание таблицы - Успешно!");


            //добавление данных
            insertData(new Textile( "Полевые цветы", 100.0));
            insertData(new Textile("Зайцы в лесу", 85.4));
            insertData(new Textile("Зимние забавы", (double) 0));
            insertData(new Textile("Шотландский олень", 40.0));
            insertData(new Textile("Тюльпаны.Белый фон", 10.3));
            System.out.println("Таблица заполена - Успешно!");

           deleteIfZeroInStock(connection);
            Collection<Textile> textile = readData(connection);

            // Удаление данных
            for (var name : textile)
                deleteData(connection, name.getId());
            //System.out.println("Delete data successfully");

            updateData(connection, 1, "Flower in the garden");
            // Чтение данных
           // for (var name : textile)
           //     System.out.println(name);
           // System.out.println("Read data successfully");


           connection.close();
           System.out.println("Подключение к Базе Данных остановлено!");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            throw new RuntimeException();
        }


    }



    // ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ
    // создание БД
    private static void createDB(Connection connection) throws SQLException {
        String createDBSQL = "CREATE DATABASE IF NOT EXISTS TextileDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDBSQL)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE TextileDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    // создание таблицы
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS textile (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NULL, active_qty DOUBLE NOT NULL);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }


    // добавление данных в таблицу текстиль
    // @param connection Соединение с БД
    // @param textile
    // @throws SQLException Исключение при выполнении запроса
    private static void insertData(Textile textile) throws SQLException {
        String insertDataSQL = "INSERT INTO textile (name, active_qty) VALUES (?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, textile.getName());
            statement.setDouble(2, textile.getActiveQTY());
            statement.executeUpdate();
        }
    }

    //Чтение данных из таблицы textile
    //@param connection Соединение с БД
    //@return Коллекция тканей
    //@throws SQLException Исключение при выполнении запроса
    private static Collection<Textile> readData(Connection connection) throws SQLException {
        ArrayList<Textile> textileList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM textile;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                double activeQTY = resultSet.getDouble("active_qty");
                textileList.add(new Textile(name, activeQTY));
            }
            return textileList;
        }
    }

    // Удаление записи из таблицы Ткани по количеству 0
    // @param connection Соединение с БД
    // @param activeQTY Идентификатор записи
    // @throws SQLException Исключение при выполнении запроса

    private static void deleteIfZeroInStock(Connection connection) throws SQLException {
        String deleteDataSQL = "DELETE FROM textile WHERE active_qty=0;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.executeUpdate();
        }
    }

    // удаление записей из таблицы
    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM textile WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }


    //Обновление названия в таблице  по идентификатору

    private static void updateData(Connection connection, int id, String name) throws SQLException {
        String updateDataSQL = "UPDATE textile SET name=?  WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.executeUpdate();
        }
    }


}


