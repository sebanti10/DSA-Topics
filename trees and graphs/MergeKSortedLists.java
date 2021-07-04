// Leetcode 23

// You are given an array of k linked-lists lists, each linked-list is sorted in
// ascending order. Merge all the linked-lists into one sorted linked-list and return it.

class PriorityComparator implements Comparator<ListNode> {
    public int compare (ListNode l1, ListNode l2) {
        if (l1.val > l2.val)
            return 1;
        else if (l1.val < l2.val)
            return -1;
        else
            return 0;
    }
}
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new PriorityComparator());
        
        for (ListNode list: lists) {
            if (list != null)
                pq.add(list);
        }
        
        ListNode result = new ListNode(0, null);
        ListNode curr = result;
        
    
        while (!pq.isEmpty()) {
            curr.next = pq.poll();
            curr = curr.next;
         
            if (curr.next != null)
                pq.add(curr.next);
        }
        
        return result.next;
    }
}