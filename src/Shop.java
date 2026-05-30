import java.util.ArrayList;
public class Shop {
    private ArrayList<ShopItem> items;
    private int tokens;

    public Shop() {
        tokens = 0;
        items = new ArrayList<>();

        items.add(new ShopItem("Bow Hat", 20, "hat"));
        items.add(new ShopItem("Party Hat", 30, "hat"));
        items.add(new ShopItem("Witch Hat", 50, "hat"));

        items.add(new ShopItem("Sakura Garden", 40, "background"));
        items.add(new ShopItem("Starry Night", 60, "background"));
        items.add(new ShopItem("Dreamscape", 100, "background"));

    }
}
