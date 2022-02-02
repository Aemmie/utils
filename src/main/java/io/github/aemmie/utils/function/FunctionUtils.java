package io.github.aemmie.utils.function;

import com.pivovarit.function.ThrowingBiFunction;
import com.pivovarit.function.ThrowingFunction;
import com.pivovarit.function.ThrowingSupplier;
import lombok.extern.slf4j.Slf4j;

import java.util.function.Consumer;
import java.util.function.UnaryOperator;

@Slf4j
public class FunctionUtils {


    public static <T> T any(ThrowingSupplier<T, Exception> supplier) {
        return any(asArray(supplier));
    }
    @SafeVarargs
    public static <T> T any(ThrowingSupplier<T, Exception>... suppliers) {
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

    public static <T, S> T any(S value, ThrowingFunction<S, T, Exception> function) {
        return any(value, asArray(function));
    }
    @SafeVarargs
    public static <T, S> T any(S value, ThrowingFunction<S, T, Exception>... functions) {
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
    public static <T, S, V> T any(S value1, V value2, ThrowingBiFunction<S, V, T, Exception>... functions) {
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

    @SafeVarargs
    private static <T> T[] asArray(T... items){
        return items;
    }
}
