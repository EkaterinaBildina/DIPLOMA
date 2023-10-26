package patternMain.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import patternMain.pattern.Pattern;


public class PatternDemo {
    public static void main(String[] args) throws Exception {

        // Создаем объект ObjectMapper для преобразования между объектами Java и JSON
        ObjectMapper objectMapper = new ObjectMapper();

        // 1. Создаем новый объект
        Pattern pattern1 = new Pattern(1, "Кейли", 36, 168);
        Pattern pattern2 = new Pattern(2, "Дафна", 42, 174);

        // 2. Сериализуем объект Book в строку JSON:
        // процесс перевода структуры данных в битовую последовательность.
        String jsonString1 = objectMapper.writeValueAsString(pattern1);
        System.out.println("Serialized JSON: " + jsonString1);

        String jsonString2 = objectMapper.writeValueAsString(pattern2);
        System.out.println("Serialized JSON: " + jsonString2);

        // 3. Десериализуем строку JSON обратно в объект Textile:
        // создание структуры данных из битовой последовательности
        Pattern deserializedPattern1 = objectMapper.readValue(jsonString1, Pattern.class);
        System.out.println("Deserialized Object: " + deserializedPattern1);

        Pattern deserializedPattern2 = objectMapper.readValue(jsonString2, Pattern.class);
        System.out.println("Deserialized Object: " + deserializedPattern2);
    }

    }

