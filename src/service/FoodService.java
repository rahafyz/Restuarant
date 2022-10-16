package service;

import database.Database;
import exception.CustomException;
import model.Food;

public class FoodService {

    public Food findFoodById(int id) {
        for (int i = 0; i < Database.getFoods().length; i++) {
            if (Database.getFoods()[i].getId() == id)
                return Database.getFoods()[i];
        }
        throw new CustomException("there is no food by this id.");
    }

    public void checkAvailability(Food[] foods){
        for (int i = 0; i < foods.length; i++) {
            if (!foods[i].isAvailable())
                throw new CustomException(foods[i].getName() + "is unavailable now.");
        }
    }
}
