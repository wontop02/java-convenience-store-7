package store.view;

public class OutputView {
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String PRINT_START = "안녕하세요. W편의점입니다.\n"
            + "현재 보유하고 있는 상품입니다.\n";
    private static final String PRINT_NAME_AND_PRICE = "- %s %,d원 ";
    private static final String PRINT_QUANTITY = "%d개 ";
    private static final String PRINT_ZERO_QUANTITY = "재고 없음 ";

    public static void printErrorMessage(String message) {
        System.out.println(ERROR_PREFIX + message);
    }

    public static void printStart() {
        System.out.println(PRINT_START);
    }

    public static void printProducts(String name, int price, Integer quantity, String promotion) {
        System.out.printf(PRINT_NAME_AND_PRICE, name, price);
        if (quantity == null) {
            System.out.printf(PRINT_ZERO_QUANTITY);
        }
        if (quantity != null) {
            System.out.printf(PRINT_QUANTITY, quantity);
        }
        System.out.printf(promotion);
        System.out.println();
    }
}
