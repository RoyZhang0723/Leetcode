/**
 * https://leetcode.cn/problems/partition-list/description/
 * 分隔链表
 */
public class Partition {
    /**
     * 思想妙在维护两个链表，分别完成任务，利用了链表的特性。
     */
    class Solution {
        public ListNode partition(ListNode head, int x) {
            ListNode small = new ListNode(0);
            ListNode smallHead = small;
            ListNode large = new ListNode(0);
            ListNode largeHead = large;
            while (head != null) {
                if (head.val < x) {
                    small.next = head;
                    small = small.next;
                } else {
                    large.next = head;
                    large = large.next;
                }
                head = head.next;
            }
            large.next = null;
            small.next = largeHead.next;
            return smallHead.next;
        }
    }

}
