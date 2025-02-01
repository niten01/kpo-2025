package studying;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Customer {
    @Getter
    private final String name;

    @Getter
    @Setter
    private Car car;

    private

    public Customer(String name) {
        this.name = name;
    }

}
