import java.util.Stack;
class Solution {

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        for(int i = 0; i < s.length(); i++){
            // charAt()方法返回指定索引位置的char值。
            char c = s.charAt(i);
            if (c == '(' || c =='[' || c == '{')
                stack.push(c);
            else{
                if(stack.isEmpty())
                    return false;
                char topChar = stack.pop();
                if(c == ')' && topChar != '(')
                    return false;
                if(c == ']' && topChar != '[')
                    return false;
                if(c == '}' && topChar != '{')
                    return false;
            }
        }
        // 如果所有字符匹配完之后，栈中还有剩余的左括号，说明匹配失败
        return stack.isEmpty();
    }

    public static void main(String[] args){
        System.out.println((new Solution()).isValid("{[)}"));
    }
}
