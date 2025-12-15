package store.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.List;
import store.domain.Product;
import store.domain.Promotion;
import store.repository.ProductRepository;
import store.repository.PromotionRepository;

public class StoreService {
    private static final String PRODUCT_FILE_PATH = "src/main/resources/products.md";
    private static final String PROMOTION_FILE_PATH = "src/main/resources/promotions.md";
    private static final String CAN_NOT_LOAD_FILE = "파일을 불러오는 데 문제가 발생했습니다.";

    private static final String NOT_PROMOTION = "null";
    private static final String BASIC = "basic";

    public List<Product> initProducts() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PRODUCT_FILE_PATH))) {
            String line = reader.readLine(); // 첫 번째 라인 사용 X
            while ((line = reader.readLine()) != null) {
                List<String> items = Arrays.asList(line.split(",", -1));
                String name = items.get(0);
                int price = Integer.parseInt(items.get(1));
                int quantity = Integer.parseInt(items.get(2));
                String promotion = items.get(3);
                if (promotion.equals(NOT_PROMOTION)) {
                    promotion = BASIC;
                }
                addProduct(name, price, quantity, promotion);
            }
            return ProductRepository.products();
        } catch (Exception e) {
            throw new IllegalStateException(CAN_NOT_LOAD_FILE);
        }
    }

    private void addProduct(String name, int price, int quantity, String promotion) {
        if (ProductRepository.findByName(name) != null) {
            Product product = ProductRepository.findByName(name);
            product.addQuantity(promotion, quantity);
            return;
        }
        Product product = new Product(name, price);
        ProductRepository.addProduct(product);
        product.addQuantity(promotion, quantity);
    }

    public List<Promotion> initPromotions() {
        try (BufferedReader reader = new BufferedReader(new FileReader(PROMOTION_FILE_PATH))) {
            String line = reader.readLine(); // 첫 번째 라인 사용 X
            while ((line = reader.readLine()) != null) {
                List<String> items = Arrays.asList(line.split(",", -1));
                String name = items.get(0);
                int buy = Integer.parseInt(items.get(1));
                int get = Integer.parseInt(items.get(2));
                String startDate = items.get(3);
                String endDate = items.get(4);
                PromotionRepository.addPromotion(new Promotion(name, buy, get, startDate, endDate));
            }
            return PromotionRepository.promotions();
        } catch (Exception e) {
            throw new IllegalStateException(CAN_NOT_LOAD_FILE);
        }
    }
}
