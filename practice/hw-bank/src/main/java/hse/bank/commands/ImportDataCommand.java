package hse.bank.commands;

import hse.bank.interfaces.Command;

public class ImportDataCommand implements Command<Void> {
    private final String filePath;

    public ImportDataCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Void execute() {
        System.out.println("Importing data from " + filePath);
        // Implement import logic
        return null;
    }
}
