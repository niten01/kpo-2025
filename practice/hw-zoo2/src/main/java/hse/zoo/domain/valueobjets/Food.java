package hse.zoo.domain.valueobjets;

public record Food(String name) {
    public static Food create(String name) {
        return new Food(name);
    }
}
