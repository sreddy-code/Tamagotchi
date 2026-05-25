

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
        Node newNode = new
    }
}
