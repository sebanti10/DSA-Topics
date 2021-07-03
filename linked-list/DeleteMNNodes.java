// Leetcode 1474: Delete (every) N Nodes After (every) M Nodes of a Linked List

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

public class DeleteMNNodes {
    public static ListNode deleteMNNodes(ListNode head, int m, int n) {
        ListNode curr = head, tail = head;
        int add = m, del = n;

        while (curr != null) {
            while (curr != null && add > 1) {
                curr = curr.next;
                add--;
            }
            tail = curr.next;
            while (tail != null && del != 0) {
                tail = tail.next;
                del--;
            }
            if (add == 1) {
                curr.next = tail;
                add = m;
                del = n;
                curr = curr.next;
            }
        }
        return head;
    }
    public static void display(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val + " ");
            curr = curr.next;
        }
        System.out.println();
    }
    public static ListNode addAtEnd(ListNode head, int val) {
        ListNode newNode = new ListNode(val, null);
        if (head == null) {
            head = newNode;
            return head;
        }
        ListNode curr = head;
        while (curr.next != null) {
            curr = curr.next;
        }
        curr.next = newNode;
        return head;
    }
    public static void main(String[] args) {
        int[] input = new int[] {9,3,7,7,9,10,8,2};
        ListNode head = null;
        for (int i = 0; i < input.length; i++) {
            head = addAtEnd(head, input[i]);
        }
        display(head);

        head = deleteMNNodes(head, 1, 2);
        display(head);
    }
}
