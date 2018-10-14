// 203:移除链表元素
/*
删除链表中等于给定值 val 的所有节点。

示例:

输入: 1->2->6->3->4->5->6, val = 6
输出: 1->2->3->4->5

总结：如果不使用虚拟头结点，在判定第一个元素的时候需要单独判断一下。删除操作需要找到待删除元素的前一个节点，
如果第一个元素就是待删除元素，此时没有前一个节点存在。而使用虚拟头结点则没有这个问题，但是在返回的时候需要
注意应该返回虚拟头节点的next节点，而不是head节点(如果传入的链表只有一个元素，即head为待删除的元素，如果
返回haed会失败)。
*/

// 方案1：使用虚拟头结点
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        ListNode prev = dummyHead;
        prev.next = head;
        while(prev.next != null){
            if(prev.next.val == val){
                prev.next = prev.next.next;
            }
            else
                prev = prev.next;
        }
        return dummyHead.next;
    }
}

// 方案2：不适用虚拟头结点
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        while(head != null && head.val == val)
        	//ListNode delNode = head;
            //head = head.next;
            //delNode.next = null;
            // 正规的写法应该像前面几行手动将待删除节点指向null，但是LeetCode中会自动清理
            head = head.next;
        if(head == null)
            return null;
        
        ListNode prev = head;
        while(prev.next != null)
            if(prev.next.val == val)
                prev.next = prev.next.next;
            else
                prev = prev.next;
        
        return head;
    }
}