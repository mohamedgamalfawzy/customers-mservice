package org.jumia.customers.util;

@FunctionalInterface
public interface IValidator<T> {
    boolean validate(T param);
}
