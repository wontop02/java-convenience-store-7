package store.domain;

import java.util.HashMap;
import java.util.Map;

public class Product {
    private final String name;
    private final int price;
    private final Map<String, Integer> quantity = new HashMap<>();

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public void addQuantity(String promotion, Integer quantity) {
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
}
