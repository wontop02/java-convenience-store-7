package store.domain;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private static final String BASIC = "basic";

    private final String name;
    private final int price;
    private final Map<String, Integer> quantity = new HashMap<>();

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void updateQuantity(String promotion, Integer quantity) {
        this.quantity.put(promotion, quantity);
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public Integer getQuantity(String promotion) {
        return quantity.get(promotion);
    }

    public int getTotalQuantity() {
        return quantity.values().stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public Map<String, Integer> quantity() {
        return Map.copyOf(quantity);
    }


    public String getPromotionName() {
        return quantity.keySet().stream()
                .filter(k -> !k.equals(BASIC))
                .findAny()
                .orElse(null);
    }
}
