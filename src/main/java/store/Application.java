package store;

import store.controller.StoreController;
import store.service.StoreService;

public class Application {
    public static void main(String[] args) {
        StoreService storeService = new StoreService();
        StoreController storeController = new StoreController(storeService);
        storeController.run();
    }
}
