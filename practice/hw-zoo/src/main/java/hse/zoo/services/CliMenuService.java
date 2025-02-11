package hse.zoo.services;

import hse.zoo.factories.AnimalFactory;
import hse.zoo.factories.ThingFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class CliMenuService {
    @Autowired
    private Zoo zoo;
    @Autowired
    private AnimalFactory animalFactory;
    @Autowired
    private ThingFactory thingFactory;
    private final Scanner scanner = new Scanner(System.in);

    private void printMenu() {
        System.out.println("-------------------------------------");
        System.out.println("ZOO ERP");
        System.out.println("add_animal - Add animal");
        System.out.println("add_thing - Add thing");
        System.out.println("list - list all entities");
        System.out.println("q - quit");
        System.out.println("-------------------------------------");
    }

    private void addAnimal() {
        String type = null;
        Integer food = null;
        try {
            System.out.println("Enter type (monkey, rabbit, tiger, wolf):");
            type = scanner.nextLine();
            System.out.println("Enter food consumption (kg):");
            food = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            return;
        }

        Integer kindness = null;
        try {
            if (animalFactory.isHerbo(type)) {
                System.out.println("Enter kindness level (1-10):");
                kindness = scanner.nextInt();
                scanner.nextLine();
            }
            zoo.addAnimal(animalFactory.createAnimal(type, food, kindness));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void addThing() {
        System.out.println("Enter type (table, computer):");
        String type = scanner.nextLine();

        try {
            zoo.addThing(thingFactory.createThing(type));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        String command = "";
        while (!command.equals("q")) {
            printMenu();

            command = scanner.nextLine();
            switch (command) {
                case "add_animal" -> addAnimal();
                case "add_thing" -> addThing();
                case "list" -> {
                    zoo.getAnimals().forEach(System.out::println);
                    zoo.getThings().forEach(System.out::println);
                }
                default -> System.out.println("Invalid command.");
            }
        }
    }

}
