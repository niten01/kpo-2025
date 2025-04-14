package hse.bank.facades;

import hse.bank.domains.Category;
import hse.bank.domains.Operation;
import hse.bank.domains.OperationType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Analytics provider service
 */
@Component
@RequiredArgsConstructor
public class AnalyticsFacade {
    private final OperationFacade operationFacade;
    private final CategoryFacade categoryFacade;

    public double calculateBalanceDifference(Date startDate, Date endDate) {
        Predicate<Operation> opDateInRange = op -> op.getDate().after(startDate) && op.getDate().before(endDate);
        double income = operationFacade.getAllOperations().stream().filter(op -> op.getType() == OperationType.INCOME && opDateInRange.test(op)).mapToDouble(Operation::getAmount).sum();
        double expense = operationFacade.getAllOperations().stream().filter(op -> op.getType() == OperationType.EXPENSE && opDateInRange.test(op)).mapToDouble(Operation::getAmount).sum();
        return income - expense;
    }

    public Map<Category, List<Operation>> groupOperations() {
        return operationFacade.getAllOperations().stream().collect(Collectors.groupingBy(op -> categoryFacade.getCategory(op.getCategoryId())));
    }
}
