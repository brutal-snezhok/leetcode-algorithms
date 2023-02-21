package linkedList.medium;

// https://leetcode.com/problems/design-browser-history/
public class DesignBrowserHistory_1472 {
    // solution1: using double linked list
    Node curr;

    public DesignBrowserHistory_1472(String homepage) {
        curr = new Node(null, homepage, null);
    }

    public void visit(String url) {
        Node newNode = new Node(curr, url, null);
        curr.next = newNode;
        curr = newNode;
    }

    public String back(int steps) {
        while (curr.prev != null && steps != 0) {
            curr = curr.prev;
            steps--;
        }

        return curr.url;
    }

    public String forward(int steps) {
        while (curr.next != null && steps != 0) {
            curr = curr.next;
            steps--;
        }

        return curr.url;
    }

    class Node {
        Node prev;
        String url;
        Node next;

        public Node(Node prev, String url, Node next) {
            this.prev = prev;
            this.url = url;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        DesignBrowserHistory_1472 browserHistory = new DesignBrowserHistory_1472("leetcode.com");
        browserHistory.visit("google.com");       // You are in "leetcode.com". Visit "google.com"
        browserHistory.visit("facebook.com");     // You are in "google.com". Visit "facebook.com"
        browserHistory.visit("youtube.com");      // You are in "facebook.com". Visit "youtube.com"
        browserHistory.back(1);                   // You are in "youtube.com", move back to "facebook.com" return "facebook.com"
        browserHistory.back(1);                   // You are in "facebook.com", move back to "google.com" return "google.com"
        browserHistory.forward(1);                // You are in "google.com", move forward to "facebook.com" return "facebook.com"
        browserHistory.visit("linkedin.com");     // You are in "facebook.com". Visit "linkedin.com"
        browserHistory.forward(2);                // You are in "linkedin.com", you cannot move forward any steps.
        browserHistory.back(2);                   // You are in "linkedin.com", move back two steps to "facebook.com" then to "google.com". return "google.com"
        browserHistory.back(7);                   // You are in "google.com", you can move back only one step to "leetcode.com". return "leetcode.com"
    }
}
