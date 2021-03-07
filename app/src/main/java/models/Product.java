package models;

public class Product {
    private int id;
    private String Title;
    private int price;

    public Product() {};

    public Product(int id, String title, int price) {
        this.id = id;
        Title = title;
        this.price = price;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getId() {
        return id;
    }

    public int getPrice() {
        return price;
    }

    public String getTitle() {
        return Title;
    }
}
