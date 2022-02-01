package io.github.aemmie.utils.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public interface EnumKebabStyle {
    String name();

    @JsonValue
    default String toKebab() {
        return name().toLowerCase().replace('_', '-');
    }
}
