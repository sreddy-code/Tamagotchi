

public class ActionHistory {
    private Node head;
    private int size;

    private class Node {
        String action;
        Node next;

        Node(String action) {
            this.action = action;
            this.next = null;

        }
    }

        public ActionHistory() {
            head = null;
            size = 0;
        }

        public void addAction(String action) {
            Node newNode = new Node(action);
            newNode.next = head;
            head = newNode;
            size++;
    }

    public String getRecentActions(int n){
        String result = "";
        Node current = head;
        int count = 0;
        while (current != null  && count < n) {
            result += "- " +current.action + "\n";
            current = current.next;
            count ++;
        }

        if (result.equals("")){
            return "No actions yet!";

        }

        return result;


    }
}
