package hse.zoo.exceptions;

import lombok.Getter;

@Getter
public class ZooException extends RuntimeException {
    public ZooException(String message) {
        super(message);
    }
}
