package ShoppingSystem;

public class StockException extends RuntimeException{
    public StockException(){
        super("Product isn't available for sale");
    }
    public StockException(int stock){
        super("Can't add negative or zero stock");
    }
    public StockException(int noOfItems, int availableStock){
        super("Can't add " + noOfItems + " items, only " + availableStock + " available");
    }
}
