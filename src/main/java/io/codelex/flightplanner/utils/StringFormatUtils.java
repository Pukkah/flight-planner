package io.codelex.flightplanner.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StringFormatUtils {

    public static String capitalize(String s) {
        return Arrays.stream(s.trim().split(" "))
                     .map(String::toLowerCase)
                     .map(org.springframework.util.StringUtils::capitalize)
                     .collect(Collectors.joining(" "));
    }

    public static String toUpperCase(String s) {
        return s.trim().toUpperCase();
    }

}
