package hse.zoo.domain.valueobjets;

public record EnclosureID(int value) {
    private static int counter = 0;

    public static EnclosureID create() {
        return new EnclosureID(counter++);
    }

    public static EnclosureID create(int value) {
        return new EnclosureID(value);
    }
}
