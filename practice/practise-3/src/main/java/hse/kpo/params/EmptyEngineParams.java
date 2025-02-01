package hse.kpo.params;

/**
 * Parameters class for engines with no parameters.
 */
public record EmptyEngineParams() {
    public static final EmptyEngineParams DEFAULT = new EmptyEngineParams();
}
