package patternsDataBase.patternDB;

import patternsDataBase.pattern.Pattern;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class ConnectionPattern {

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
            insertData(new Pattern( "Flora", 42, 168));
            insertData(new Pattern( "Flora", 44, 168));
            insertData(new Pattern( "Flora", 46, 168));
            insertData(new Pattern( "Flora", 48, 168));
            insertData(new Pattern( "Relaxed", 42, 168));
            insertData(new Pattern( "Relaxed", 44, 168));
            insertData(new Pattern( "Relaxed", 46, 168));
            insertData(new Pattern( "Relaxed", 48, 168));
            insertData(new Pattern( "Office-style", 42, 168));
            insertData(new Pattern( "Office-style", 44, 168));
            insertData(new Pattern( "Office-style", 46, 168));
            insertData(new Pattern( "Office-style", 48, 168));
            System.out.println("Таблица заполена - Успешно!");


            Collection<Pattern> pattern = readData(connection);

            // Удаление данных
            for (var name : pattern)
                deleteData(connection, name.getId());
            //System.out.println("Delete data successfully");

            updateData(connection, "Mira", 1);
            // Чтение данных
            for (var name : pattern)
                 System.out.println(name);
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
        String createDBSQL = "CREATE DATABASE IF NOT EXISTS PatternDB;";
        try (PreparedStatement statement = connection.prepareStatement(createDBSQL)) {
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException {
        String useDatabaseSQL = "USE PatternDB;";
        try (PreparedStatement statement = connection.prepareStatement(useDatabaseSQL)) {
            statement.execute();
        }
    }

    // создание таблицы
    private static void createTable(Connection connection) throws SQLException {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS pattern (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255) NULL, size INT NOT NULL, height INT NOT NULL);";
        try (PreparedStatement statement = connection.prepareStatement(createTableSQL)) {
            statement.execute();
        }
    }


    // добавление данных в таблицу
    // @param connection Соединение с БД
    // @param pattern
    // @throws SQLException Исключение при выполнении запроса
    private static void insertData(Pattern pattern) throws SQLException {
        String insertDataSQL = "INSERT INTO pattern (name, size, height) VALUES (?, ?, ?);";
        try (PreparedStatement statement = connection.prepareStatement(insertDataSQL)) {
            statement.setString(1, pattern.getName());
            statement.setInt(2, pattern.getSize());
            statement.setInt(3, pattern.getHeight());
            statement.executeUpdate();
        }
    }

    //Чтение данных из таблицы textile
    //@param connection Соединение с БД
    //@return Коллекция тканей
    //@throws SQLException Исключение при выполнении запроса
    private static Collection<Pattern> readData(Connection connection) throws SQLException {
        ArrayList<Pattern> patternsList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM pattern;";
        try (PreparedStatement statement = connection.prepareStatement(readDataSQL)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int size = resultSet.getInt("size");
                int height = resultSet.getInt("height");
                patternsList.add(new Pattern(name, size, height));
            }
            return patternsList;
        }
    }


    // удаление записей из таблицы
    private static void deleteData(Connection connection, int id) throws SQLException {
        String deleteDataSQL = "DELETE FROM pattern WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(deleteDataSQL)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }


    //Обновление названия в таблице  по идентификатору

    private static void updateData(Connection connection, String name, int id) throws SQLException {
        String updateDataSQL = "UPDATE pattern SET name=?  WHERE id=?;";
        try (PreparedStatement statement = connection.prepareStatement(updateDataSQL)) {
            statement.setString(1, name);
            statement.setInt(2, id);
            statement.executeUpdate();
        }
    }

}
