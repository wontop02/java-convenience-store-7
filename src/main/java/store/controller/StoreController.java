package store.controller;

import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.service.StoreService;
import store.view.OutputView;

public class StoreController {
    private final StoreService storeService;

    private static final String BASIC = "basic";

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void run() {
        List<Product> products = storeService.initProducts();
        List<Promotion> promotions = storeService.initPromotions();
        printProducts(products, promotions);
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
}
