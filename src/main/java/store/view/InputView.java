package store.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String REQUEST_PRODUCT = "\n구매하실 상품명과 수량을 입력해 주세요. (예: [사이다-2],[감자칩-1])";
    private static final String REQUEST_FIXED_PRICE = "\n현재 %s %d개는 프로모션 할인이 적용되지 않습니다. 그래도 구매하시겠습니까? (Y/N)\n";
    private static final String REQUEST_ADD_PRODUCT = "\n현재 %s은(는) 1개를 무료로 더 받을 수 있습니다. 추가하시겠습니까? (Y/N)\n";

    public static String requestProduct() {
        System.out.println(REQUEST_PRODUCT);
        return Console.readLine();
    }

    public static String requestFixedPrice(String name, int quantity) {
        System.out.printf(REQUEST_FIXED_PRICE, name, quantity);
        return Console.readLine();
    }

    public static String requestAddProduct(String name) {
        System.out.printf(REQUEST_ADD_PRODUCT, name);
        return Console.readLine();
    }
}
