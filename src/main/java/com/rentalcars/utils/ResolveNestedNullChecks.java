package com.rentalcars.utils;

import lombok.experimental.UtilityClass;

import java.util.Optional;
import java.util.function.Supplier;

@UtilityClass
public class ResolveNestedNullChecks {

    public static <T> T resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result).orElse((T) "");
        }
        catch (NullPointerException e) {
            return (T) "";
        }
    }
}
