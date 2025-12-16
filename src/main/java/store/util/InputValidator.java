package store.util;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputValidator {
    private static final String ELIMINATE_FORMAT = "[\\[\\]]";
    private static final String PRODUCT_FORMAT = "^[가-힣0-9a-zA-Z]+-\\d+";
    private static final String INVALID_FORMAT = "올바르지 않은 형식으로 입력했습니다. 다시 입력해 주세요.";
    private static final String INVALID_INPUT = "잘못된 입력입니다. 다시 입력해 주세요.";

    public static void validateProductInput(String input) {
        List<String> inputs = Arrays.asList(input.split(",", -1));
        List<String> names = new ArrayList<>();
        for (String format : inputs) {
            format = format.replaceAll(ELIMINATE_FORMAT, "");
            if (!format.matches(PRODUCT_FORMAT)) {
                throw new IllegalArgumentException(INVALID_FORMAT);
            }
            validateDuplicate(names, format);
            String quantity = Arrays.asList(format.split("-", -1)).get(1);
            validateWithinIntRange(quantity);
            validatePositive(quantity);
        }
    }

    private static void validateWithinIntRange(String input) {
        BigInteger value = new BigInteger(input);
        if (value.compareTo(BigInteger.valueOf(Integer.MIN_VALUE)) < 0
                || value.compareTo(BigInteger.valueOf(Integer.MAX_VALUE)) > 0) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private static void validatePositive(String input) {
        int quantity = Integer.parseInt(input);
        if (quantity < 1) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
    }

    private static void validateDuplicate(List<String> names, String format) {
        String name = Arrays.asList(format.split("-", -1)).get(0);
        if (names.contains(name)) {
            throw new IllegalArgumentException(INVALID_INPUT);
        }
        names.add(name);
    }
}
