package textileMain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import textileMain.textile.Textile;

public class TextileDemo {
    public static void main(String[] args) throws Exception {
        // Создаем объект ObjectMapper для преобразования между объектами Java и JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 1. Создаем новый объект Textile
        Textile textile = new Textile(1, "Россыпь", 2.8);

        // 2. Сериализуем объект Book в строку JSON
        String jsonString = objectMapper.writeValueAsString(textile);
        System.out.println("Serialized JSON: " + jsonString);

        // 3. Десериализуем строку JSON обратно в объект Textile
        Textile deserializedTextile = objectMapper.readValue(jsonString, Textile.class);
        System.out.println("Deserialized Object: " + deserializedTextile);
    }
}

