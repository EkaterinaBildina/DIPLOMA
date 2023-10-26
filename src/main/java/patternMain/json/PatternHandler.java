package patternMain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.HttpExchange;
import exeptions.PathParameterException;
import exeptions.PatternNotFountException;
import patternMain.pattern.Pattern;
import patternMain.patternDB.IPatternDB;

import java.io.*;
import java.util.Arrays;

public class PatternHandler {

    private final IPatternDB patternDB;

    public PatternHandler(IPatternDB patternInput) {
        this.patternDB = patternInput;
    }

    private final ObjectMapper objectMapper = new ObjectMapper();


    public void handle(HttpExchange exchange) throws IOException {
        String requestMethod = exchange.getRequestMethod();
        StringBuilder responseBody = new StringBuilder();
        exchange.getResponseHeaders().set("Content-Type", "application/json");
        exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "DELETE, POST, GET, OPTIONS");
        exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Content-Type");
        String[] pathElements = exchange.getRequestURI().getPath().split("/");

        switch (requestMethod) {
            case "GET":
                responseBody.append(objectMapper.writeValueAsString(patternDB.getPattern()));
                exchange.sendResponseHeaders(200, responseBody.length());
                break;
            case "POST":
                try {
                    if (pathElements.length == 3) {
                        Pattern newPattern = patternDB.addPattern(pathElements[5]);
                        responseBody.append(objectMapper.writeValueAsString(newPattern));
                        exchange.sendResponseHeaders(201, responseBody.length());
                    } else if (pathElements.length == 5) {
                        String bodyString = getRequestBodyString(exchange.getRequestBody());
                        patternDB.addAll(Arrays.asList(objectMapper.readValue(bodyString, Pattern[].class)));
                        exchange.sendResponseHeaders(200, responseBody.length());
                    } else {
                        throw new PathParameterException("Pattern name is not found in Data Base");
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
                        responseBody.append(objectMapper.writeValueAsString(patternDB.deletePattern(id)));
                        exchange.sendResponseHeaders(200, responseBody.length());
                    } else {
                        throw new PathParameterException("Pattern id is not found in DataBase");
                    }
                } catch (PatternNotFountException e) {
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
