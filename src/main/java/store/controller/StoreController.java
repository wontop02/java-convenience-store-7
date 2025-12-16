package store.controller;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import store.domain.Product;
import store.domain.Promotion;
import store.service.StoreService;
import store.util.InputValidator;
import store.view.InputView;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;

    private static final String PROMOTION = "promotion";
    private static final String BASIC = "basic";
    private static final String YES = "Y";
    private static final String NO = "N";

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void run() {
        List<Product> products = storeService.initProducts();
        List<Promotion> promotions = storeService.initPromotions();
        printProducts(products, promotions);
        Map<String, Integer> order = makeOrder();
        modifyOrder(order);
    }

    private void printProducts(List<Product> products, List<Promotion> promotions) {
        OutputView.printStart();
        for (Product product : products) {
            for (Promotion promotion : promotions) {
                String promotionName = promotion.getName();
                if (product.getQuantity(promotionName) != null) {
                    OutputView.printProducts(product.getName(), product.getPrice(), product.getQuantity(promotionName),
                            promotionName);
                    break;
                }
            }
            OutputView.printProducts(product.getName(), product.getPrice(), product.getQuantity(BASIC), "");
        }
    }

    private Map<String, Integer> makeOrder() {
        while (true) {
            try {
                String input = InputView.requestProduct();
                InputValidator.validateProductInput(input);
                return storeService.makeOrder(input);
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private Map<String, Map<String, Integer>> modifyOrder(Map<String, Integer> order) {
        Map<String, Map<String, Integer>> orders = new HashMap<>();
        orders.put(PROMOTION, new LinkedHashMap<>());
        orders.put(BASIC, new LinkedHashMap<>());
        checkPromotion(order, orders);
        return orders;
    }

    private void checkPromotion(Map<String, Integer> order, Map<String, Map<String, Integer>> orders) {
        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            Product product = storeService.findByNameProduct(entry.getKey());
            if (storeService.isPromotion(product.getPromotionName())) {
                int insufficient = storeService.insufficientPromotionQuantity(entry.getValue(), product);
                if (insufficient != 0) {
                    requestFixedPrice(orders, entry, insufficient);
                    continue;
                }
                int needQuantity = storeService.needPromotionQuantity(entry.getValue(), product);
                if (needQuantity != 0) {
                    requestAddProduct(orders, entry, needQuantity);
                    continue;
                }
            }
            orders.get(BASIC).put(entry.getKey(), entry.getValue());
        }
    }

    private void requestFixedPrice(Map<String, Map<String, Integer>> orders, Map.Entry<String, Integer> entry,
                                   int insufficient) {
        while (true) {
            try {
                String input = InputView.requestFixedPrice(entry.getKey(), insufficient);
                InputValidator.validateAnswer(input);
                if (input.equals(NO)) {
                    orders.get(PROMOTION).put(entry.getKey(), entry.getValue() - insufficient);
                }
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private void requestAddProduct(Map<String, Map<String, Integer>> orders, Map.Entry<String, Integer> entry,
                                   int needQuantity) {
        while (true) {
            try {
                String input = InputView.requestAddProduct(entry.getKey());
                InputValidator.validateAnswer(input);
                if (input.equals(YES)) {
                    orders.get(PROMOTION).put(entry.getKey(), entry.getValue() + needQuantity);
                }
                break;
            } catch (IllegalArgumentException e) {
                OutputView.printErrorMessage(e.getMessage());
            }
        }
    }
}
