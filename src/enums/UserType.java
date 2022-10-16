package enums;

public enum UserType {
    ADMIN(1), CUSTOMER(2);

    private final int index;

    UserType(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
