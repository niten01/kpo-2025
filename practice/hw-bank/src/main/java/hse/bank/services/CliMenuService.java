package hse.bank.services;

import hse.bank.domains.OperationType;
import hse.bank.factories.CommandFactory;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@Component
@RequiredArgsConstructor
@Slf4j
public class CliMenuService {
    private Scanner scanner = null;
    private boolean isAlive = false;
    private final CommandFactory commandFactory;

    public void setStream(InputStream in) {
        scanner = new Scanner(in);
    }

    public void start() {
        if (scanner == null) {
            log.error("Input stream for CLI not provided");
            return;
        }
        isAlive = true;
        printHelp();
        while (isAlive) {
            String input = scanner.nextLine();
            try {
                handleInput(input);
                // better rethrow all exceptions to IllegalArgumentException, but i'm tired
            } catch (RuntimeException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void handleInput(String input) {
        Command<?> command = null;

        List<String> parts = List.of(input.split(" "));
        String commandName = parts.getFirst();
        parts = parts.subList(1, parts.size());

        if (commandName.equals("create")) {
            String type = parts.getFirst();
            parts = parts.subList(1, parts.size());
            switch (type) {
                case "account" -> command = commandFactory.createCreateAccountCommand(parts.getFirst());
                case "category" ->
                        command = commandFactory.createCreatCategoryCommand(parts.getFirst(), OperationType.valueOf(parts.get(1)));
                case "operation" ->
                        command = commandFactory.createCreateOperationCommand(Integer.parseInt(parts.get(0)), Integer.parseInt(parts.get(1)), OperationType.valueOf(parts.get(2)), Double.parseDouble(parts.get(3)), parts.size() > 4 ? parts.get(4) : "");
                default -> throw new IllegalArgumentException("Invalid type for create command");
            }
        } else if (commandName.equals("list")) {
            switch (parts.getFirst()) {
                case "category" -> command = commandFactory.createListCategoriesCommand();
                case "account" -> command = commandFactory.createListAccountsCommand();
                case "operation" -> command = commandFactory.createListOperationsCommand();
                default -> throw new IllegalArgumentException("Invalid type for list command");
            }
        } else if (commandName.equals("analytics")) {
            var dayBefore = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            command = commandFactory.createAnalyticsCommand(dayBefore, new Date());
        } else if (commandName.equals("set_balance")) {
            command = commandFactory.createSetBalanceCommand(Integer.parseInt(parts.get(0)), Double.parseDouble(parts.get(1)));
        } else if (commandName.equals("fix_balance")) {
            command = commandFactory.createFixBalanceCommand();
        } else if (commandName.equals("import")) {
            command = commandFactory.createImportCommand();
        } else if (commandName.equals("help")) {
            printHelp();
        } else if (commandName.equals("quit")) {
            isAlive = false;
            command = commandFactory.createExportCommand();
        }

        if (command != null) {
            if (!parts.isEmpty() && parts.getLast().equals("timed")) {
                command = commandFactory.makeTimed(command);
            }
            var res = command.execute();
            System.out.println("-> " + res);
        }

    }

    private void printHelp() {
        System.out.println("Type can be one of: category, account, operation");
        System.out.println("Enter command:");
        System.out.println("- create <type> <args ...> - args are constructor arguments (i.e. (name)/(name type)/(accId categoryId type amount [description]))");
        System.out.println("- list <type>");
        System.out.println("- analytics");
        System.out.println("- set_balance <accId> <amount>");
        System.out.println("- fix_balance");
        System.out.println("- import");
        System.out.println("- help");
        System.out.println("- quit - export and exit");
        System.out.println("Suffix any command with 'timed' to measure execution time");
    }
}
