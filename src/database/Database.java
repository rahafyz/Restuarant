package database;

import model.Food;
import model.User;

import java.util.Objects;

public class Database {
    private static Food[] foods = new Food[20];
    private static User[] users = new User[100];

    public static Food[] getFoods() {
        return foods;
    }

    public static User[] getUsers() {
        return users;
    }

    public static void addFood(Food food){
        for (int i = 0; i < foods.length; i++) {
            if (Objects.isNull(foods[i])) {
                foods[i] = food;
                break;
            }
        }
    }

    public static void addUser(User user){
        for (int i = 0; i < users.length; i++) {
            if (Objects.isNull(users[i])) {
                users[i] = user;
                break;
            }
        }
    }


}
