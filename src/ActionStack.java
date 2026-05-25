import java.util.Stack;

public class ActionStack {
        private Stack<String> actions;

        public ActionStack() {
            actions = new Stack<>();
        }

        public void pushAction(String action) {
            actions.push(action);
        }

        public String undo(Pet pet){
            if (actions.isEmpty()){
                return "nothing to undo!";

            }

            String lastAction = actions.pop();

            if (lastAction.equals("feed")) {
                pet.setHunger(Math.max(0, pet.getHunger() - 30));
                pet.setHappiness(Math.max(0, pet.getHappiness() - 10));
            } else if (lastAction.equals("giveWater")) {
                pet.setThirst(Math.max(0, pet.getThirst() - 30));
                pet.setHappiness(Math.max(0, pet.getHappiness() - 5));
            } else if (lastAction.equals("play")) {
                pet.setHappiness(Math.max(0, pet.getHappiness() - 25));
                pet.setEnergy(Math.min(100, pet.getEnergy() + 20));
            } else if (lastAction.equals("sleep")) {
                pet.setEnergy(Math.max(0, pet.getEnergy() - 40));
                pet.setHunger(Math.min(100, pet.getHunger() + 10));
            }

            return "Undid: " + lastAction;
        }

    public boolean isEmpty() {
        return actions.isEmpty();
    }

    public String toString() {
        return actions.toString();
    }
}


