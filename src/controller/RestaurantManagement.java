package controller;

import database.Database;
import enums.UserType;
import exception.CustomException;
import model.Admin;
import model.Customer;
import model.Food;
import model.User;
import service.AdminService;
import service.FoodService;
import service.UserService;

import java.util.Objects;
import java.util.Scanner;

public class RestaurantManagement {

    private static final int LOGIN = 1;
    private static final int REGISTER = 2;

    private static final int SHOW_FOODS = 1;
    private static final int ORDER_FOOD = 2;
    private static final int ORDER_LIST = 3;

    private static final int ADD_FOOD = 1;
    private static final int CHANGE_AVAILABILITY = 2;
    private static final int CHANGE_PRICE = 3;

    private static final int EXIT = 0;
    private static final int LOGOUT = 9;

    private boolean isExit = false;

    UserService userService = new UserService();
    FoodService foodService = new FoodService();
    AdminService adminService = new AdminService();


    private User currentUser;

    public void run() {
        firstMenu();
        while (!isExit) {
            if (currentUser instanceof Admin)
                adminMenu();
            else
                customerMenu();
        }
    }

    private void firstMenu() {
        int userType = intInput("""
                Select item:
                1. admin.
                2. customer.
                """);
        while (Objects.isNull(currentUser)) {
            if (UserType.ADMIN.getIndex() == userType) {
                authenticationMenu(UserType.ADMIN);
            } else
                authenticationMenu(UserType.CUSTOMER);
        }

    }

    private void adminMenu() {
        int item = intInput("""
                Select your item:
                1. Add food
                2. change food Availability
                3. change price
                9. logout
                0. Exit
                """);

        switch (item) {
            case ADD_FOOD: {
                String name = stringInput("Enter the name of food.");
                int availability = intInput("Is it available now? Enter number: \n 1.Yes 2.No");
                boolean available = availability == 1;
                double price = doubleInput("Enter price: ");
                adminService.addFood(name, available, price);
                break;
            }
            case CHANGE_AVAILABILITY: {
                int id = intInput("Enter food id: ");
                Food food = foodService.findFoodById(id);
                int availability = intInput("Is it available now? Enter number: \n 1.Yes 2.No");
                boolean available = availability == 1;
                if (Objects.nonNull(food))
                    adminService.changeAvailability(food, available);
                break;
            }
            case CHANGE_PRICE: {
                int id = intInput("Enter food id: ");
                Food food = foodService.findFoodById(id);
                double price = doubleInput("Enter new price: ");
                if (Objects.nonNull(food))
                    food.setPrice(price);
                break;
            }
            case LOGOUT: {
                currentUser = null;
                run();
                break;
            }
            case EXIT: {
                System.out.println("thank you, bye!");
                isExit = true;
                break;
            }
        }
    }

    private void customerMenu() {
        int item = intInput("""
                Select your item:
                1. show menu
                2. order food
                3. show your order history
                9. logout
                0. Exit
                """);

        switch (item) {
            case SHOW_FOODS: {
                showFoods();
                break;
            }
            case ORDER_FOOD: {
                order();
                break;
            }
            case ORDER_LIST: {
                ((Customer)currentUser).showOrderList();
                break;
            }
            case LOGOUT: {
                currentUser = null;
                run();
                break;
            }
            case EXIT: {
                System.out.println("thank you, bye!");
                isExit = true;
                break;
            }
        }
    }


    private void changeAvailability(Food food, boolean available) {
        if (food.isAvailable() == available)
            System.err.println("The availability is already" + available);
        else
            food.changeAvailability(available);
    }

    private void order() {
        Food[] foods = getOrder();
        try {
            foodService.checkAvailability(foods);
            ((Customer) currentUser).order(foods);
        }catch (CustomException exception){
            System.err.println(exception.getMessage());
            run();
        }
    }

    private Food[] getOrder(){
        Food[] orders = new Food[5];
        int cancel = 1;
        while (cancel !=0){
            int id = intInput("Enter food id: ");
            cancel = intInput("wanna order?"+"\n"+"1.Yes"+"\n"+"2.No");
            for (int i = 0; i < orders.length; i++) {
                if (Objects.nonNull(orders[i])){
                    orders[i] = foodService.findFoodById(id);
                }
            }
        }
        return orders;
    }

    private void showFoods() {
        for (int i = 0; i < Database.getFoods().length; i++) {
            System.out.println(i + ": " + Database.getFoods()[i]);
        }
    }

    private void login(UserType type) {
        try {
            String username = stringInput("Enter your username: ");
            String password = stringInput("Enter your password: ");
            currentUser = userService.login(type, username, password);
        }catch (CustomException customException){
            System.err.println(customException.getMessage());
            run();
        }

    }

    private void register(UserType type) {
        String firstName = stringInput("Enter your first name: ");
        String lastName = stringInput("Enter your last name: ");
        String nationalCode = stringInput("Enter your national code: ");
        String password = stringInput("Enter your password: ");
        userService.register(type, firstName, lastName, nationalCode, password);

        System.out.println("your account create successfully! " + "\n" +
                "your username is your national code.");
    }

    private void authenticationMenu(UserType type) {
        System.out.println("1- Login" + "\n" + "2- Register");
        int item = intInput("");
        switch (item) {
            case REGISTER: {
                register(type);
                break;
            }
            case LOGIN: {
                login(type);
                break;
            }
        }
    }

    private int intInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    private double doubleInput(String message) {
        System.out.println(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextDouble();
    }

    private String stringInput(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.next();
    }

    private boolean availabilityInput(String message) {
        System.out.print(message);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextBoolean();
    }
}

