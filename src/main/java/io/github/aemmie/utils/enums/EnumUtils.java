package io.github.aemmie.utils.enums;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EnumUtils {
    public static <E extends Enum<E>> List<E> parse(Class<E> cl, String s) {
        return Arrays.stream(s.split(","))
                .map(String::strip)
                .map(e -> getEnum(cl, e))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static <E extends Enum<E>> E getEnum(Class<E> cl, String s) {
        if (s == null || !cl.isEnum()) {
            return null;
        }
        for (E e : cl.getEnumConstants()) {
            if (e.name().equalsIgnoreCase(s)) {
                return e;
            }
        }
        return null;
    }
}
