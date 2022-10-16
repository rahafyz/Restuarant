package exception;

public class CustomException extends RuntimeException {

    private static final  String m="";

    public CustomException() {
        super(m);
    }
}
