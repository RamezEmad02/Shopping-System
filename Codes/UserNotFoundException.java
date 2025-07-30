package ShoppingSystem;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(){
        super("User not found in database\n please create new account");
    }
}
