package hse.bank.persistence;

import lombok.Setter;

public abstract class CsvSerializerService<T> extends AbstractSerializationService<T> {
    @Setter
    protected String delimiter = ";";
}
