package otus.spring.homework.comparators;

import lombok.NonNull;

import java.util.function.BiPredicate;

public final class Utils {
    private Utils() {

    }

    public static Integer compareIfNull(Object o1, Object o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }

        return null;
    }

    public static int compareAsStrings(@NonNull Object o1, @NonNull Object o2) {
        return o1.toString().compareTo(o2.toString()) > 0 ? 1 : -1;
    }

    public static <T> int compare(T o1, T o2, BiPredicate<T, T> equalsPredicate) {
        Integer comparisonIfNull = Utils.compareIfNull(o1, o2);
        if (comparisonIfNull != null) {
            return comparisonIfNull;
        }

        if (equalsPredicate.test(o1, o2)) {
            return 0;
        }
        return compareAsStrings(o1, o2);
    }
}
