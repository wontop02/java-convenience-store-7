package store.controller;

import store.service.StoreService;

public class StoreController {
    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    public void run() {
        storeService.init();
    }
}
