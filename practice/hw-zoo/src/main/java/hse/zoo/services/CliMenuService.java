package hse.zoo.services;

import hse.zoo.factories.AnimalFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class CliMenuService {
    @Autowired
    private Zoo zoo;
    @Autowired
    private AnimalFactory animalFactory;
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
        System.out.println("Enter type (monkey, rabbit, tiger, wolf):");
        String type = scanner.nextLine();
        System.out.println("Enter food consumption (kg):");
        int food = scanner.nextInt();
        scanner.nextLine();

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

    public void start() {
        String command = "";
        while (!command.equals("q")) {
            printMenu();

            command = scanner.nextLine();
            switch (command) {
                case "add_animal" -> addAnimal();
                case "list" -> zoo.getAnimals().forEach(System.out::println);
            }
        }
    }
}
