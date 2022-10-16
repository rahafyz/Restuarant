package model;

import java.time.LocalDate;

public class Account {
    private String userName;
    private String password;
    private LocalDate createdAt;

    public Account(String userName, String password) {
        this.userName = userName;
        this.setPassword(password);
        this.createdAt = LocalDate.now();
    }

    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    public void setPassword(String password) {
        if (password.length() > 6)
            this.password = password;
        else
            System.err.println("your password should be at least 6 number");
    }

}
