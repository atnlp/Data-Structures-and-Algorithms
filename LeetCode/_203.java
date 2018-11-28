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

    // 在有ListNode
    // 在LeetCode上提交的代码不应包含如下部分。
    /*
    public static void main(String[] args) {

        int[] nums = {1, 2, 6, 3, 4, 5, 6};
        ListNode head = new ListNode(nums);
        System.out.println(head);

        ListNode res = (new Solution4()).removeElements(head, 6);
        System.out.println(res);
    }
    */
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

// 方案3：使用递归
class Solution {

    public ListNode removeElements(ListNode head, int val) {

        if(head == null)
            return head;

        ListNode res = removeElements(head.next, val);
        // 如果当前节点即为要删除的节点，那么直接返回当前节点的下一个节点即可。
        if(head.val == val)
            return res;
        else{
            head.next = res;
            return head;
        }

        // 上面几行的更简略写法
        // head.next = removeElements(head.next, val);
        // return head.val == val ? head.next : head;
    }
}

/*
// 针对链表问题的辅助类，创建和toString
// 根据题目描述的链表建立对应的结构，方便本地进行调试，新加了一个根据传入的数组实现链表的构造函数。
//Definition for singly-linked list.
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode(int x) {
        val = x;
    }

    // 链表节点的构造函数
    // 使用arr为参数，创建一个链表，当前的ListNode为链表头结点
    // 实现通过传入数组进行链表创建的构造函数
    public ListNode(int[] arr){

        if(arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr can not be empty");

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1 ; i < arr.length ; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    // 以当前节点为头结点的链表信息字符串
    @Override
    public String toString(){

        StringBuilder s = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            s.append(cur.val + "->");
            cur = cur.next;
        }
        s.append("NULL");
        return s.toString();
    }
}
*/
