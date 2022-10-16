package model;

import exception.CustomException;

import java.util.Objects;

public class Customer extends User {

    private Order[] orderList;

    public Customer(String firstName, String lastName, String nationalCode, String password) {
        super(firstName, lastName, nationalCode, password);
    }

    public Order[] getOrderList() {
        return orderList;
    }

    public void setOrderList(Order[] orderList) {
        this.orderList = orderList;
    }

    public void order(Food[] foods) {
        if (this.orderList == null)
            orderList = new Order[5];
        else {
            orderList = this.orderList;
        }
        for (int i = 0; i < orderList.length; i++) {
            if (orderList[i] == null) {
                Order order = new Order(foods);
                orderList[i] = order;
                break;
            }
        }
    }

    public void showOrderList() {
        if (Objects.isNull(this.orderList))
            throw new CustomException("have no food");
        else {
            for (int i = 0; i < this.orderList.length; i++) {
                if (Objects.nonNull(this.orderList[i])) {
                    Food[] foods = this.orderList[i].getFoods();
                    System.out.println("order" + i+1);
                    for (int j = 0; j < foods.length; j++) {
                        if (Objects.nonNull(foods[j]))
                            System.out.println(j + 1 + ":" +foods[j].getName());
                    }
                }
            }
        }
    }
}
