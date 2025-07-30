package ShoppingSystem;

public class Buyer extends User implements ItemManageability {
    private ShoppingCart shoppingCart = new ShoppingCart();      //Buyer has a ShoppingCart (composition)
    private Orders [] orders = new Orders[0];                    //Buyer has orders (composition)
    private static int noOfBuyers = 0;
    public Buyer(String name, String phone, String email, int age, String address){
        super(name, phone, email, age, address);    //initialise data fields
        noOfBuyers += 1;        //update static no. of buyers with every created object
    }
    public ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public Orders[] getOrders() {
        return orders;
    }

    public static int getNoOfBuyers() {
        return noOfBuyers;
    }

    @Override
    public void addItem(Product product, int noOfItems) {
        boolean inItemsArray = false;
        for(int i=0; i< Product.getItems().length; i++){    //check if added item by buyer is available in products for sale
            if(product == Product.getItems()[i]){
                shoppingCart.addItem(product,noOfItems);    //if added item is available for sale --> add to shopping cart
                inItemsArray = true;
                break;
            }
        }
        if(!inItemsArray){
            //System.out.println("Product isn't available for sale");
            throw new StockException();
        }
    }
    @Override
    public void removeItem(Product product){    //removes product (all number of items) from shopping cart
        shoppingCart.removeItem(product);
    }
    public void addOrder(){     //confirm items in shopping cart and add order to buyer
        shoppingCart.confirmOrder();
        //add this order ref in the orders array of the buyer:
        Orders [] x= new Orders[orders.length + 1];
        for (int i=0; i< orders.length; i++){
            x[i] = orders[i];
        }
        x[orders.length] = shoppingCart.getOrderRef();
        orders = x;
    }
}
