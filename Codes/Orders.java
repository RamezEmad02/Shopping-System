package ShoppingSystem;

public class Orders {
    private int [] noOfItems;
    private Product [] items;
    private static int noOfOrders = 0;
    private long totalPrice;
    public Orders(Product[] items, int[] noOfItems, long totalPrice) {
        this.items = items;
        this.noOfItems = noOfItems;
        noOfOrders++;
        this.totalPrice = totalPrice;
        updateStock(); //update stock once order is confirmed (object is created)
    }
    private void updateStock(){      //update stock in product data fields, updated once with constructor (private access modifier)
        int [] updatedStock = new int[items.length];
        for(int i=0; i< items.length; i++){
            //new stock = available stock of each product - quantity ordered
            updatedStock[i] = items[i].getStock() - noOfItems[i];
            //update stock (data field of Product) using setter function
            items[i].setStock(updatedStock[i]);
        }
    }

    public int[] getNoOfItems() {
        return noOfItems;
    }

    public Product[] getItems() {
        return items;
    }

    public static int getNoOfOrders() {
        return noOfOrders;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
}
