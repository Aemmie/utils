package io.github.aemmie.utils.function;

import lombok.extern.slf4j.Slf4j;

import java.util.function.*;

@Slf4j
public class FunctionUtils {

    @SafeVarargs
    public static <T> T any(Supplier<T>... suppliers) {
        for (var supp : suppliers) {
            try {
                T result = supp.get();
                if (result != null) {
                    return result;
                }
            } catch (Exception e) {
                log.debug("Inner exception: ", e);
            }
        }
        log.debug("All suppliers are failed...");
        return null;
    }

    @SafeVarargs
    public static <T, S> T any(S value, Function<S, T>... functions) {
        for (var func : functions) {
            try {
                T result = func.apply(value);
                if (result != null) {
                    return result;
                }
            } catch (Exception e) {
                log.debug("Inner exception: ", e);
            }
        }
        log.debug("All functions are failed...");
        return null;
    }

    @SafeVarargs
    public static <T, S, V> T any(S value1, V value2, BiFunction<S, V, T>... functions) {
        for (var func : functions) {
            try {
                T result = func.apply(value1, value2);
                if (result != null) {
                    return result;
                }
            } catch (Exception e) {
                log.debug("Inner exception: ", e);
            }
        }
        log.debug("All functions are failed...");
        return null;
    }

    public static <T> UnaryOperator<T> peek(Consumer<T> c) {
        return x -> {
            c.accept(x);
            return x;
        };
    }
}
