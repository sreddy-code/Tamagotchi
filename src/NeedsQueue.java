import java.util.LinkedList;
import java.util.Queue;


public class NeedsQueue {
    private Queue<String> needs;
    public NeedsQueue() {
        needs = new LinkedList<>();
    }
    public void checkNeeds(Pet pet) {
        if (pet.getHunger() < 30 && !needs.contains("hungry")) {
            needs.add("hungry");

        }
        if (pet.getThirst() < 30 && !needs.contains("thirsty")) {
            needs.add("thirsty");
        }

        if (pet.getEnergy() < 30 && !needs.contains("tired")) {
            needs.add("tired");
        }
        if (pet.getHappiness() < 30 && !needs.contains("sad")) {
            needs.add("sad");
        }
        if (pet.isSick() && !needs.contains("sick")) {
            needs.add("sick");
        }
    }
        public String getNextNeed() {
            return needs.peek();
        }

        public void resolveNeed() {
            needs.poll();
        }

        public boolean hasNeeds() {
            return !needs.isEmpty();

        }

        public String toString() {
        return needs.toString();

    }

}

