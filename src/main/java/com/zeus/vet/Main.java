package com.zeus.vet;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Приложение запущено!");

        // Здесь будет инициализация БД и Кафки (позже)
        // initDatabase();
        // initKafka();

        // Цикл для взаимодействия с пользователем
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                printMenu();
                String command = scanner.nextLine().trim();

                switch (command) {
                    case "1":
                        addNewAnimalCard(scanner);
                        break;
                    case "2":
                        getAnimalStatusByName(scanner);
                        break;
                    case "exit":
                        System.out.println("Завершение работы.");
                        return;
                    default:
                        System.out.println("Неизвестная команда. Попробуйте снова.");
                }
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Меню ---");
        System.out.println("1 - Добавить новую карточку животного");
        System.out.println("2 - Получить статус животного по имени");
        System.out.println("exit - Выйти из программы");
        System.out.print("Введите команду: ");
    }

    private static void addNewAnimalCard(Scanner scanner) {
        // Заглушка. Здесь будет логика добавления новой карточки.
        System.out.println("Метод для добавления новой карточки животного.");
    }

    private static void getAnimalStatusByName(Scanner scanner) {
        // Заглушка. Здесь будет логика поиска и вывода статуса.
        System.out.println("Метод для получения статуса животного по имени.");
    }
}

