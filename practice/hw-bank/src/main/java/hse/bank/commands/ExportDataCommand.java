package hse.bank.commands;

import hse.bank.interfaces.Command;

public class ExportDataCommand implements Command<Void> {
    private final String filePath;

    public ExportDataCommand(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public Void execute() {
        System.out.println("Exporting data to " + filePath);
        // Implement export logic
        return null;
    }
}
