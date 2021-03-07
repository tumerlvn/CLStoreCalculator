package models;

import java.util.ArrayList;

public class ProductList {
    public static final ArrayList<Product> PRODUCTS = new ArrayList<>();

    public static int curID = 0;

    public static void init() {
        PRODUCTS.clear();

        for (int i = 0; i <= 20; i++) {
            PRODUCTS.add(new Product(i, "prod" + i, i * 100));
        }
    }

    public static Product getProduct(int id) {
        for (Product p : PRODUCTS) {
            if (p.getId() == id) {
                return p;
            }
        }

        return null;
    }

    public static void addProduct(String name, int cost) {
        Product p = new Product(curID, name, cost);
        curID++;
        PRODUCTS.add(p);
    }

    public static void deleteProduct(int id) {
        for (Product p : PRODUCTS) {
            if (p.getId() == id) {
                PRODUCTS.remove(p);
            }
        }
    }
}
