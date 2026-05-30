public class ShopItem {

    private String name;
    private int cost;
    private String type;
    private boolean owned;

    public ShopItem(String name, int cost, String type) {
        this.name = name;
        this.cost = cost;
        this.type = type;
        this.owned = false;
    }

    public String getName() {
        return name;
    }
    public int getCost() {
        return cost;
    }
    public String getType() {
        return type;
    }
    public boolean isOwned() {
        return isOwned();
    }
    public void setOwned(boolean owned) {
        this.owned = owned;
    }

}
