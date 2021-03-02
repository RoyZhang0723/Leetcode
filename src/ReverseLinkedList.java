import java.util.ArrayList;
import java.util.List;

/**
 * 反转链表
 */
public class ReverseLinkedList {
    public static void main(String[] args) {
        ListNode a0 = new ListNode(0);
        ListNode a1 = new ListNode(1);
        ListNode a2 = new ListNode(2);
        ListNode a3 = new ListNode(3);
        a0.next = a1;
        a1.next = a2;
        a2.next = a3;
        a3.next = null;
        ListNode head = a0;
        while (head != null) {
            System.out.println(head.val);
            head = head.next;
        }
//        reverseLinkedList(a0);
        head = a0;
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }
//        ListNode resverseList = reverseLinkedList2(head, head, null);
        ListNode resverseList = reverseLinkedList2(head,null);
        while (resverseList != null){
            System.out.println(resverseList.val);
            resverseList = resverseList.next;
        }
    }

    /**
     * 链表结构不变，
     * @param head
     */
    public static void reverseLinkedList(ListNode head) {
        List<Integer> list = new ArrayList<>();
        ListNode pivot = head;
        while (pivot != null) {
            list.add(pivot.val);
            pivot = pivot.next;
        }
        pivot = head;
        for (int i = list.size() - 1; i >= 0; i--) {
            pivot.val = list.get(i);
            pivot = pivot.next;
        }
    }

    /**
     * 该函数是直接把每个节点当做整体进行反转操作
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList1(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    /**
     * 用递归来实现反转链表
     * @param head
     * @return
     */
    public static ListNode reverseLinkedList2(ListNode head, ListNode prev) {
        if (head == null) {
            return prev;
        } else {
            ListNode nextNode = head.next;
            head.next = prev;
            prev = head;
            return reverseLinkedList2(nextNode, prev);
        }
    }
}
