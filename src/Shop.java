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
        public void earnTokens(Pet pet) {
        int earned = 0;


        if (pet.getHunger() >80) earned += 2;
        if (pet.getThirst() >80) earned += 2;
        if (pet.getEnergy() >80) earned += 2;
        if (pet.getHappiness() > 80) earned += 2;
        if (pet.getHunger() > 70 && pet.getHappiness() > 70
                    && pet.getEnergy() > 70 && pet.getThirst() > 70) {
                earned += 2;// streak bonus
            }

            tokens += earned;

        }
         public boolean buyItem(ShopItem item) {
             if (tokens >= item.getCost() && !item.isOwned()) {
                 tokens -= item.getCost();
                 item.setOwned(true);
                 return true; //purchase successful
             } else {
                 return false;
             }

         }
             public ArrayList<ShopItem> getItems() { return items; }
             public int getTokens() { return tokens; }

             public ArrayList<ShopItem> getHats() {
                 ArrayList<ShopItem> hats = new ArrayList<>();
                 for (ShopItem item : items) {
                     if (item.getType().equals("hat")) {
                         hats.add(item);
                     }
                 }
                 return hats;
             }


    public ArrayList<ShopItem> getBackgrounds() {
        ArrayList<ShopItem> backgrounds = new ArrayList<>();
        for (ShopItem item : items) {
            if (item.getType().equals("background")) {
                backgrounds.add(item);
            }
        }
        return backgrounds;
    }
}


