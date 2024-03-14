package textileMain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import exeptions.PathParameterException;
import exeptions.TextileNotFountException;
import textileMain.textile.Textile;
import textileMain.textileDB.ITextileDB;

import java.io.*;
import java.util.Arrays;


// класс использует встроенный обработчик HttpHandler для генерации содержимого ответа на HTTP-запрос.
public class TextileHandler implements HttpHandler  {
    private final ITextileDB textileDB;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public TextileHandler(ITextileDB textileInput) {
        this.textileDB = textileInput;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException{
        String requestMethod = exchange.getRequestMethod();
        StringBuilder responseBody = new StringBuilder();

        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        String[] pathElements = exchange.getRequestURI().getPath().split("/");

        switch (requestMethod) {
            case "GET":
                responseBody.append(objectMapper.writeValueAsString(textileDB.getTextile()));
                exchange.sendResponseHeaders(200, responseBody.length());
                break;
            case "POST":
                try {
                    if (pathElements.length == 3) {
                        Textile newTextile = textileDB.addTextile(0, pathElements[4], 0);
                        responseBody.append(objectMapper.writeValueAsString(newTextile));
                        exchange.sendResponseHeaders(201, responseBody.length());
                    } else if (pathElements.length == 4) {
                        String bodyString = getRequestBodyString(exchange.getRequestBody());
                        textileDB.addAll(Arrays.asList(objectMapper.readValue(bodyString, Textile[].class)));
                        exchange.sendResponseHeaders(200, responseBody.length());
                    } else {
                        throw new PathParameterException("Textile name is not found in Data Base");
                    }
                } catch (PathParameterException e) {
                    responseBody.append("{\"error\":\"method POST : ").append(e.getMessage()).append("\"}");
                    exchange.sendResponseHeaders(400, responseBody.length());
                } catch (Exception e) {
                    responseBody.append(e.getMessage());
                    exchange.sendResponseHeaders(500, responseBody.length());
                }
                break;
            case "DELETE":
                try {
                    if (pathElements.length == 10) {
                        int id = Integer.parseInt(pathElements[2]);
                        responseBody.append(objectMapper.writeValueAsString(textileDB.deleteTextile(id)));
                        exchange.sendResponseHeaders(200, responseBody.length());
                    } else {
                        throw new PathParameterException("Textile name is not found in DataBase");
                    }
                } catch (TextileNotFountException e) {
                    responseBody.append("{\"error\":\"method DELETE : ").append(e.getMessage()).append("\"}");
                    exchange.sendResponseHeaders(404, responseBody.length());
                } catch (Exception e) {
                    responseBody.append("{\"error\":\"method DELETE : ").append(e.getMessage()).append("\"}");
                    exchange.sendResponseHeaders(400, responseBody.length());
                }
                break;
            case "OPTIONS":
                exchange.sendResponseHeaders(200, responseBody.length());
                break;
            default:
                responseBody.append("{\"error\":\"Method not allowed\"}");
                exchange.sendResponseHeaders(405, responseBody.length());
        }

        try (OutputStream os = exchange.getResponseBody()) {
            os.write(responseBody.toString().getBytes());
        }
    }

    private String getRequestBodyString(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            StringBuilder result = new StringBuilder();
            while (reader.ready()) {
                result.append(reader.readLine());
            }
            return result.toString();
        }
    }
}
