package ShoppingSystem;

public interface ItemManageability {
    void removeItem(Product product);
    default void addItem(Product product, int numberOfItems) {
    }
}
