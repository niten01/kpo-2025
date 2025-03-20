package hse.bank.commands;

import hse.bank.facades.AccountFacade;
import lombok.RequiredArgsConstructor;

import hse.bank.interfaces.Command;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
public class CreateAccountCommand implements Command<Void> {
    private final AccountFacade accounts;
    private final String name;

    @Override
    public Void execute() {
        accounts.addAccount(name);
        return null;
    }
}
