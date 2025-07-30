package ShoppingSystem;

public class IllegalAgeException extends RuntimeException {
    public IllegalAgeException(){
        super("Age is smaller than 18");
    }
}
