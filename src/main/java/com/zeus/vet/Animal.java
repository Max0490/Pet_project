package com.zeus.vet;

import java.time.LocalDateTime;

// Это класс, который представляет животное
// Как шаблон для создания карточек животных
public class Animal {
   private Long id;
   private String name; //кличка
   private String species; //вид
   private String gender; //пол
   private String healthStatus; //статус здоровья
   private LocalDateTime createdAt; //создано

    // Конструктор - специальный метод для создания новых объектов
    public Animal(String name, String species, String gender, String healthStatus) {
        this.name = name;
        this.species = species;
        this.gender = gender;
        this.healthStatus = healthStatus;
    }

    // Геттеры - методы для получения значений полей
    public Long getId() {return id;}
    public String getName() {return name;}
    public String getSpecies() {return species;}
    public String getGender() {return gender;}
    public String getHealthStatus() {return healthStatus;}
    public LocalDateTime getCreatedAt() {return createdAt;}

    // Сеттеры - методы для установки значений полей

    public void setId(Long id) {this.id = id;}

    public void setName(String name) {this.name = name;}

    public void setSpecies(String species) {this.species = species;}

    public void setGender(String gender) {this.gender = gender;}

    public void setHealthStatus(String healthStatus) {this.healthStatus = healthStatus;}

    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}

    @Override
    public String toString() {
        return String.format(
                "Животное: %s\nВид: %s\nПол: %s\nСтатус здоровья: %s\nСоздано: %s",
                name, species, gender, healthStatus, createdAt
        );
    }
}
