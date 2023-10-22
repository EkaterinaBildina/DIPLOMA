package json;

import com.fasterxml.jackson.databind.ObjectMapper;
import textile.Textile;

public class TextileDemo {
    public static void main(String[] args) throws Exception {
        // Создаем объект ObjectMapper для преобразования между объектами Java и JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 1. Создаем новый объект Book
        Textile textile = new Textile(1, "Россыпь", 2.8);

        // 2. Сериализуем объект Book в строку JSON
        String jsonString = objectMapper.writeValueAsString(textile);
        System.out.println("Serialized JSON: " + jsonString);

        // 3. Десериализуем строку JSON обратно в объект Book
        Textile deserializedTextile = objectMapper.readValue(jsonString, Textile.class);
        System.out.println("Deserialized Object: " + deserializedTextile);
    }
}

