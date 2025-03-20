package hse.bank.factories;

import hse.bank.commands.*;
import hse.bank.domains.OperationType;
import hse.bank.facades.AccountFacade;
import hse.bank.facades.AnalyticsFacade;
import hse.bank.facades.CategoryFacade;
import hse.bank.facades.OperationFacade;
import hse.bank.interfaces.Command;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Creator of command objects
 */
@Component
@RequiredArgsConstructor
public class CommandFactory {
    private final AnalyticsFacade analytics;
    private final AccountFacade accounts;
    private final CategoryFacade categories;
    private final OperationFacade operations;

    public AnalyticsCommand createAnalyticsCommand(Date startDate, Date endDate) {
        return new AnalyticsCommand(analytics, startDate, endDate);
    }

    public FixBalanceCommand createFixBalanceCommand() {
        return new FixBalanceCommand(accounts);
    }

    public SetBalanceCommand createSetBalanceCommand(int id, double balance) {
        return new SetBalanceCommand(accounts, id, balance);
    }

    public CreateAccountCommand createCreateAccountCommand(String name) {
        return new CreateAccountCommand(accounts, name);
    }

    public CreateCategoryCommand createCreatCategoryCommand(String name, OperationType type) {
        return new CreateCategoryCommand(categories, name, type);
    }

    public CreateOperationCommand createCreateOperationCommand(int accountId, int categoryId, OperationType type, double amount, String description) {
        return new CreateOperationCommand(operations, accountId, categoryId, type, amount, description);
    }

    public <T> TimedCommandDecorator<T> makeTimed(Command<T> command) {
        return new TimedCommandDecorator<>(command);
    }

    public ListOperationsCommand createListOperationsCommand() {
        return new ListOperationsCommand(operations);
    }

    public ListCategoriesCommand createListCategoriesCommand() {
        return new ListCategoriesCommand(categories);
    }

    public ListAccountsCommand createListAccountsCommand() {
        return new ListAccountsCommand(accounts);
    }
}
