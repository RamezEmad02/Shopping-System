package ShoppingSystem;

public class Car extends Product {
    private String brand;
    private String model;
    private int productionYear;
    private double horsePower;
    private String color;
    Car(double price, String condition, int stock, String brand, String model, int productionYear, double horsePower, String color){
        super(price,condition,stock);
        this.brand=brand;
        this.model=model;
        this.productionYear=productionYear;
        this.horsePower=horsePower;
        this.color=color;
    }
    public String getBrand() {
        return brand;
    }
    public String getModel() {
        return model;
    }
    public int getProductionYear() {
        return productionYear;
    }
    public double getHorsePower() {
        return horsePower;
    }
    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        String s = "The car is a "+ color + " " + brand + " " + model + " " + productionYear + " " + horsePower + "HP ";
        return s; //returns car's data as a string
    }
}
