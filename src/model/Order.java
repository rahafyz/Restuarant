package model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Order {
    private int id;
    private Food[] foods;
    private LocalDate date;

    public Order(Food[] foods) {
        Random random = new Random();
        this.id = random.nextInt(1,100);
        this.date = LocalDate.now();
        this.foods = foods;
    }

    public int getId() {
        return id;
    }

    public Food[] getFoods() {
        return foods;
    }

    public void setFoods(Food[] foods) {
        this.foods = foods;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
