package utils;

public class CustomExceptions{

    public class LoginInvalidoException extends Exception {
        LoginInvalidoException(String message){
            super(message);
        }
    }

}
