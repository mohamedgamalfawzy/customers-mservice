package org.jumia.customers.util;

import java.util.function.Predicate;

public class Validator<T> implements  IValidator<T>{

    private Predicate <T> predicate;

    private Validator(Predicate <T> predicate) {
        this.predicate = predicate;
    }
    public static <T> Validator <T> from(Predicate <T> predicate) {
        return new Validator <T> (predicate);
    }
    @Override
    public boolean validate(T param) {
        return predicate.test(param) ? true : false;
    }
}
