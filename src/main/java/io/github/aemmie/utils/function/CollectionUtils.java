package io.github.aemmie.utils.function;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
public class CollectionUtils {
    public static <T, V, C extends Collection<T>> C nonNullForEach(Iterable<V> items, Function<V, T> function, Supplier<C> collection) {
        C list = collection.get();
        if (items == null) {
            return list;
        }
        for (V item : items) {
            try {
                var elem = function.apply(item);
                if (elem != null) {
                    list.add(elem);
                }
            } catch (Exception e) {
                log.debug("nonNullForEach: " + e.getMessage() + ", Item: " + item, e);
            }
        }
        return list;
    }

    public static <T, V> List<T> nonNullForEach(Iterable<V> items, Function<V, T> function) {
        return nonNullForEach(items, function, ArrayList::new);
    }
}
