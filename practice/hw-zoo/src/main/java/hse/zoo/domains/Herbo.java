package hse.zoo.domains;

import lombok.Getter;
import lombok.ToString;

@ToString
@Getter
public abstract class Herbo  extends Animal{
    protected int kindness;

    public Herbo(int food, int kindness) {
        super(food);
        this.kindness = kindness;
    }
}
