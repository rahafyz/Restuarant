package model;

import java.util.Random;

public class Food {
    private int id;
    private String name;
    private boolean available;
    private double price;

    public Food(String name, boolean available, double price) {
        Random random = new Random();
        this.id = random.nextInt(0,100);
        this.name = name;
        this.available = available;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAvailable() {
        return available;
    }

    public void changeAvailability(boolean available) {
        this.available = available;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
