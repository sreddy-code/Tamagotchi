public class Pet {
    private String name;
    private int hunger;
    private int thirst;
    private int energy;
    private int happiness;
    private boolean isSick;

    //constructor
    public Pet(String name) {
        this.name = name;
        this.hunger = 80;
        this.thirst = 80;
        this.energy = 80;
        this.happiness = 80;
        this.isSick = false;
    }

    public void decayStats() {
        hunger = Math.max(0, hunger - 5);
        thirst = Math.max(0, thirst - 7);
        energy = Math.max(0, energy - 3);
        happiness = Math.max(0, happiness - 4);


        if (hunger == 0 || thirst == 0 || energy == 0) {
            isSick = true;
        }
    }
    public void feed() {
        hunger = Math.min(100, hunger + 30);
        happiness = Math.min(100, happiness + 10);

    }

    public void giveWater() {
        thirst = Math.min(100, thirst + 30);
        happiness = Math.min(100, happiness + 10);
    }

    public void play() {
        happiness = Math.min(100, happiness + 30);
        energy = Math.max(0, energy - 20);
    }

    public void sleep() {
        energy = Math.min(100, energy + 30);
        hunger = Math.max(0, hunger - 20);

    }
    public String getName() { return name; }
    public int getHunger() { return hunger; }
    public int getThirst() { return thirst; }
    public int getEnergy() { return energy; }
    public int getHappiness() { return happiness; }
    public boolean isSick() { return isSick; }
}



