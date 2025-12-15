package store.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import store.domain.Product;

public class ProductRepository {
    private static final List<Product> products = new ArrayList<>();

    public static List<Product> products() {
        return Collections.unmodifiableList(products);
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static boolean deletePromotionByName(String name) {
        return products.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        products.clear();
    }

    public static Product findByName(String name) {
        return products.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
