/**
给定一个二叉树，返回它的 前序 遍历。
 示例:
输入: [1,null,2,3]  
   1
    \
     2
    /
   3 

输出: [1,2,3]

 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// 递归法，最终不是将树种的节点打印出来，而是返回一个列表。为方便对null进行处理，又定义了一个pre方法
class Solution1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        
        ArrayList<Integer> res = new ArrayList<Integer>();
        return pre(root, res);
    }
    
    List<Integer> pre(TreeNode root, ArrayList<Integer> res) {
        if (root == null)
            return res;

        res.add(root.val);
        pre(root.left, res);
        pre(root.right, res);
        return res;
    }    
}

// 迭代法，相比递归法要更复杂
// 迭代法一般使用栈来处理，可直接使用java内置的栈
// 因为栈是先进后出，先将右孩子压入栈，后将左孩子压入栈
// 可使用util包中的Stack，或双端队列Deque实现栈的功能
 class Solution2 {

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList();

        if (root == null) {
            return list;
        }
        
        
        Stack<TreeNode> stack = new Stack<> ();
        // Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        
        while(!stack.isEmpty()){
            TreeNode cur = stack.pop();
            list.add(cur.val);
            
            if(cur.right != null)
                stack.push(cur.right);
            if(cur.left != null)
                stack.push(cur.left);
        }
        return list;            
    }
 }


