package ShoppingSystem;

public class ShoppingCart implements ItemManageability {
    private int [] noOfItems = new int[0];
    private Product [] items = new Product[0];
    private Orders orderRef;
    private String paymentMethod;
    private long totalPrice = 0;
    public ShoppingCart(){}     //default constructor

    public int[] getNoOfItems() {
        return noOfItems;
    }

    public Product[] getItems() {
        return items;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public long getTotalPrice() {
        return totalPrice;
    }
    public int getNoOfItemsOfIndexi (int i){
        if(i>(noOfItems.length-1)){
            throw new IndexOutOfBoundsException();
        }
        return noOfItems[i];
    }

    @Override
    public void addItem(Product product, int numberOfItems){
        if(numberOfItems > product.getStock()){     //check stock
            //System.out.println("Not enough stock");  ////Exception handling
            throw new StockException(numberOfItems, product.getStock());
        }
        else if(numberOfItems<=0){
            throw new StockException(numberOfItems);
        }
        else {
            //add product ref in items array:
            Product [] x = new Product[items.length + 1];
            for (int i=0; i<items.length; i++){
                x[i] = items[i];
            }
            x[items.length] = product;
            items = x;
            //add numberOfItems in noOfItems array:
            int [] y = new int[noOfItems.length + 1];
            for (int i=0; i<noOfItems.length; i++){
                y[i]= noOfItems[i];
            }
            y[noOfItems.length] = numberOfItems;
            noOfItems = y;
            //update total price:
            totalPrice += product.getPrice() * numberOfItems;
        }
    }
    @Override
    public void removeItem(Product product){    //removes 1st product(and its quantity) in Shopping cart if found
        int i;
        boolean isInCart = false;
        for (i=0; i<items.length; i++){
            if(product == items[i]){    //check if product is in shopping cart
                isInCart = true;
                break;                  //i is the position of item required to remove in the items array
            }
            else if(i==(items.length - 1)) {
                System.out.println("Item isn't in the Shopping Cart"); ////Exception Handling
                isInCart = false;
            }
        }
        if (isInCart){
            //remove product ref from items array:
            Product [] x = new Product[items.length - 1];
            int k=0; //counter for array x
            for(int j=0; j< items.length;j++){
                if(j == i){
                    continue;
                }
                else{
                    x[k] = items[j];
                    k++;
                }
            }
            items = x;
            //update total price:
            totalPrice -= product.getPrice() * noOfItems[i];
            //remove noOfItems of removed product from noOfItems array:
            int [] y = new int[noOfItems.length - 1];
            int a =0; //counter for array y
            for (int j=0; j<noOfItems.length; j++){
                if(j == i){
                    continue;
                }
                else{
                    y[a] = noOfItems[j];
                    a++;
                }
            }
            noOfItems = y;
        }
    }

    public Orders getOrderRef() {
        return orderRef;
    }
    public void confirmOrder (){
        orderRef = new Orders(items, noOfItems, totalPrice); //pass the items in cart and their quantity to order
        items = new Product[0];                  //make the shopping cart empty
        noOfItems = new int[0];
        totalPrice = 0;                          //no products, so reset totalPrice
    }
}
