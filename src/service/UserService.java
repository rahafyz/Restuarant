package service;

import database.Database;
import enums.UserType;
import exception.CustomException;
import model.Admin;
import model.Customer;
import model.User;

import java.util.Objects;

public class UserService {

    public void register(UserType type,String firstName,String lastName,String nationalCode,String password){
        User newUser;
        if (type.equals(UserType.ADMIN))
            newUser = new Admin(firstName,lastName,nationalCode,password);
        else
            newUser = new Customer(firstName,lastName,nationalCode,password);
        Database.addUser(newUser);
    }

    public User login(UserType type,String userName, String password){
        User user = findUserByUsername(userName);
        if (type.equals(UserType.ADMIN)){
            if (user instanceof Admin){
                if (user.validation(password))
                    return user;
                throw new CustomException("invalid password.");
            }
            throw new CustomException("you can't login as a admin");
        }
        else {
            if (user instanceof Customer) {
                if (user.validation(password))
                    return user;
            }
            throw new CustomException("you can't login as a customer");
        }
    }

    private User findUserByUsername(String username){
        for (int i = 0; i < Database.getUsers().length; i++) {
            if (Objects.nonNull(Database.getUsers()[i])){
                if (Database.getUsers()[i].getNationalCode().equals(username))
                    return Database.getUsers()[i];
            }
        }
        throw new CustomException("there is no user by this username");
    }
}
