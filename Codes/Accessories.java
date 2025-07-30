package ShoppingSystem;

public class Accessories extends Product{
    private String origin;
    private String type;

    Accessories(double price, String condition, int stock, String origin, String type){
        super(price,condition,stock);
        this.origin= origin;
        this.type= type;
    }
    public String getType() {
        return type;
    }
    public String getOrigin() {
        return origin;
    }
    @Override
    public String toString() {
        String s = "The accessory is a " + type  ;
        return s; //returns accessory's data as a string
    }

}

