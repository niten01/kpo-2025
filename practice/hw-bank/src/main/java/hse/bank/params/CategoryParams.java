package hse.bank.params;

import hse.bank.domains.OperationType;

/**
 * Category creation params
 * @param name category name
 * @param type category type
 */
public record CategoryParams(String name, OperationType type) {
}
