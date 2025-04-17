package nettee.enums;

import java.util.EnumSet;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EnumUtil {
    private EnumUtil() {}

    public static <E extends Enum<E>, T> boolean isUniqueAllOf(Class<E> enumClass, Function<E, T> getter) {
//        Set<T> set = new HashSet<>(enumClass.getEnumConstants().length);
//        for (var item : enumClass.getEnumConstants()) {
//            set.add(getter.apply(item)); // static int getCode(SampleStatus item) { return item.code }
//        }
//
//        return set.size() == enumClass.getEnumConstants().length;
        return EnumSet.allOf(enumClass).stream()
                .map(getter)
                .collect(Collectors.toSet())
                .size() == enumClass.getEnumConstants().length;
    }

    // ... boolean isUniqueOf(E item1, E item2, Function<E, T> getter) {}
    // ... boolean isUniqueOf(E[] items, Function<E, T> getter) {}
}
