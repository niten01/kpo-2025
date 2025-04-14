package hse.bank.domains;

import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * Object of this class is returned by analytics
 * @param balanceDifference difference between income and expense
 * @param groupedOperations operations grouped by category
 */
public record AnalyticsResult(double balanceDifference, Map<Category, List<Operation>> groupedOperations) {
}
