package service;

import database.Database;
import model.Food;

public class AdminService {

    public void addFood(String name, boolean available, double price) {
        Food food = new Food(name, available, price);
        for (int i = 0; i < Database.getFoods().length; i++) {
            if (Database.getFoods()[i] == null) {
                Database.addFood(food);
                break;
            }
        }
    }

    public void changeAvailability(Food food, boolean available) {
        if (food.isAvailable() == available)
            System.err.println("The availability is already" + available);
        else
            food.changeAvailability(available);
    }
}
