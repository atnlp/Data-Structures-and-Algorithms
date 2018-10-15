/*
24. 两两交换链表中的节点
https://leetcode-cn.com/problems/swap-nodes-in-pairs/description/
给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

示例:

给定 1->2->3->4, 你应该返回 2->1->4->3.
说明:

你的算法只能使用常数的额外空间。
你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
*/
// 解法1 利用虚拟头结点   提交记录超过99.51%的提交结果。
public class Solution {

    public ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        // 把head指向了虚拟头结点，并在while循环中对head进行移动，
        // 但是虚拟头节点dummyHead还指向原先的位置，所以最后直接返回dummyHead.next即可
        dummyHead.next = head;
        head = dummyHead;
        while (head.next != null && head.next.next != null) {
            // 在内部定义两个链表节点，虽然多维护了两个变量，但是逻辑变得更清晰
            ListNode n1 = head.next, n2 = head.next.next;
            head.next = n2;
            n1.next = n2.next;
            n2.next = n1;

            head = n1;
        }

        return dummyHead.next;
    }
}

/*
// 解法2，利用递归，比较精妙，提交记录超过12.57%的提交结果。递归不适合线性数据结构中，比较耗时
public class Solution {
    public ListNode swapPairs(ListNode head) {
        // Write your code here
        ListNode p = head;
        if(p==null || p.next==null)
            return p;
        ListNode pNextNext = p.next.next;
        ListNode swapHead = p.next; // 交换p 和 p.next 两个结点
        swapHead.next = p; // 进行交换
        ListNode swapNextNext = swapPairs(pNextNext); // 递归
        p.next = swapNextNext;
        return swapHead;
    }
}
*/

/*
// 通过变换节点内的值进行交换，不符合题目要求，但是也可以完美得到解。提交记录超过75%的提交结果。
public class Solution {

    public ListNode swapPairs(ListNode head) {
        // Write your code here
        ListNode p = head;
        if(p==null || p.next==null)
            return p;

        while(p != null && p.next != null){
            int val = p.val;
            p.val = p.next.val;
            p.next.val = val;
            p = p.next.next;
        }
        return head;

    }
}
*/

