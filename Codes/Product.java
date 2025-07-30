package ShoppingSystem;

import java.util.Arrays;

public abstract class Product implements Comparable<Product> {
    private double price;
    private String condition;
    private int stock;
    private int ProductID;
    private static int noOfProducts;
    private static Product[] items = new Product[0];

    public Product(double price, String condition, int stock) {
        this.price = price;
        this.condition = condition;
        if(stock<=0){
            throw new StockException(stock);
        }
        else{
            this.stock = stock;
        }
        noOfProducts++;
        this.ProductID=noOfProducts;
    }

    public double getPrice() {
        return price;
    }
    public String getCondition() {
        return condition;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getProductID() {
        return ProductID;
    }

    public static int getNoOfProducts() {
        return noOfProducts;
    }
    public static Product[] getItems() {
        return items;
    }
    public static void addItem(Product item) {
        Product[] tempArr= new Product[items.length+1];
        for (int i = 0; i < items.length; i++) {
            tempArr[i]=items[i];
        }
        tempArr[items.length]=item;
        items=tempArr;
    }
    public static void removeItem(Product item) {
        Product[] tempArr= new Product[items.length-1];
        int found=0;
        boolean flag =false;
        for (int i = 0; i < items.length; i++) {
            if(item==items[i]) {
                found=i;
                flag=true;
                break;
            }
        }
        if(flag) {
            for (int i = 0, j = 0; j < items.length; i++,j++) {
                if (j==found) {
                    i--;
                }
                else {
                    tempArr[i] = items[j];
                }
            }
            items = tempArr;
        }
        else {
            System.out.println("Product not found");
        }
    }
    @Override
    public int compareTo(Product o){        //compare acc to price
        if(o.getPrice() == this.getPrice())
            return 0;
        else if(o.getPrice() < this.getPrice())
            return 1;
        else
            return -1;
    }
    public static void sortItems (){               //sort price form high to low
        Arrays.sort(items);
    }
}