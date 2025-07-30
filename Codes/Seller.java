package ShoppingSystem;

public class Seller extends User implements ItemManageability{
    private Product [] products = new Product[0];
    private int itemsSold;
    public Seller(String name, String phone, String email, int age, String address){
        super(name, phone, email, age, address);
    }

    public Product[] getProducts() {
        return products;
    }

    public int getItemsSold() {
        return itemsSold;
    }

    public void addCar(double price, String condition, int stock, String brand, String model, int productionYear, double horsePower, String color){
        Product x = new Car(price, condition, stock, brand, model, productionYear, horsePower, color);
        //send ref x to static array in abstract class product:
        Product.addItem(x);
        //add new product created in seller's products list (related to object):
        addProductToSeller(x);
    }
    public void addAccessories(double price, String condition, int stock, String origin, String type){
        Product x = new Accessories(price, condition, stock, origin, type);
        //send ref x to static array in abstract class product:
        Product.addItem(x);
        //add new product created in seller's products list (related to object):
        addProductToSeller(x);
    }
    private void addProductToSeller(Product x){
        Product [] tempArr = new Product[products.length + 1];
        for(int i=0; i<products.length; i++){
            tempArr[i] = products[i];
        }
        tempArr[products.length] = x;
        products = tempArr;
    }
    @Override
    public void removeItem(Product item){
        int i;
        boolean inProducts = false;
        for(i=0; i<products.length; i++){
            if(item == products[i]){
                inProducts = true;        //item is available in seller products list (array)
                break;                    //i is the position of item required to remove in the products array
            }
            else if(i == (products.length - 1)){
                inProducts = false;
                System.out.println("Product isn't in Seller's available products");
            }
        }
        if(inProducts){
            //Remove product from static array in abstract class product
            Product.removeItem(item);
            //Remove product from seller's products list (related to object):
            Product [] tempArr = new Product[products.length - 1];
            int k=0;
            for(int j=0; j< products.length; j++){
                if(j==i){
                    continue;
                }
                else{
                    tempArr[k] = products[j];
                    k++;
                }
            }
            products = tempArr;
        }
    }
}
