package com.zeus.vet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {

        private Connection connection;

        // Конструктор - создает подключение к базе данных
        public Database() {
            try {
                // SQLite - простая файловая база данных
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:animals.db");
                createTable(); // Создаем таблицу, если её нет
                System.out.println("База данных подключена успешно!");
            } catch (Exception e) {
                System.out.println("Ошибка подключения к базе данных: " + e.getMessage());
            }
        }

        // Создаем таблицу для животных
        private void createTable() {
            String sql = """
            CREATE TABLE IF NOT EXISTS animals (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                name TEXT NOT NULL UNIQUE,
                species TEXT NOT NULL,
                gender TEXT NOT NULL,
                health_status TEXT NOT NULL,
                created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
            )
        """;

            try (Statement stmt = connection.createStatement()) {
                stmt.execute(sql);
                System.out.println("Таблица животных создана или уже существует");
            } catch (SQLException e) {
                System.out.println("Ошибка создания таблицы: " + e.getMessage());
            }
        }

        // Добавляем новое животное в базу данных
        public boolean addAnimal(Animal animal) {
            String sql = "INSERT INTO animals (name, species, gender, health_status) VALUES (?, ?, ?, ?)";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, animal.getName());
                pstmt.setString(2, animal.getSpecies());
                pstmt.setString(3, animal.getGender());
                pstmt.setString(4, animal.getHealthStatus());
                pstmt.executeUpdate();
                return true;
            } catch (SQLException e) {
                System.out.println("Ошибка добавления животного: " + e.getMessage());
                return false;
            }
        }

        // Ищем животное по кличке
        public Animal findAnimalByName(String name) {
            String sql = "SELECT * FROM animals WHERE name = ?";

            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setString(1, name);
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    Animal animal = new Animal(
                            rs.getString("name"),
                            rs.getString("species"),
                            rs.getString("gender"),
                            rs.getString("health_status")
                    );
                    animal.setId(rs.getLong("id"));
                    return animal;
                }
            } catch (SQLException e) {
                System.out.println("Ошибка поиска животного: " + e.getMessage());
            }
            return null;
        }

        // Получаем всех животных
        public List<Animal> getAllAnimals() {
            List<Animal> animals = new ArrayList<>();
            String sql = "SELECT * FROM animals";

            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Animal animal = new Animal(
                            rs.getString("name"),
                            rs.getString("species"),
                            rs.getString("gender"),
                            rs.getString("health_status")
                    );
                    animal.setId(rs.getLong("id"));
                    animals.add(animal);
                }
            } catch (SQLException e) {
                System.out.println("Ошибка получения животных: " + e.getMessage());
            }
            return animals;
        }

        // Закрываем подключение к базе данных
        public void close() {
            try {
                if (connection != null) {
                    connection.close();
                    System.out.println("Подключение к базе данных закрыто");
                }
            } catch (SQLException e) {
                System.out.println("Ошибка закрытия подключения: " + e.getMessage());
            }
        }
    }

