package store.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import store.domain.Promotion;

public class PromotionRepository {
    private static final List<Promotion> promotions = new ArrayList<>();

    public static List<Promotion> promotions() {
        return Collections.unmodifiableList(promotions);
    }

    public static void addPromotion(Promotion promotion) {
        promotions.add(promotion);
    }

    public static boolean deletePromotionByName(String name) {
        return promotions.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static void deleteAll() {
        promotions.clear();
    }

    public static Promotion findByName(String name) {
        return promotions.stream()
                .filter(l -> l.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
