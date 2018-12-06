import java.util.TreeMap;
// 使用了红黑树为底层的TreeMap，可改为HashMap
public class Trie {

    private class Node{

        public boolean isWord;
        public TreeMap<Character, Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    // 获得Trie中存储的单词数量
    public int getSize(){
        return size;
    }

    // 向Trie中添加一个新的单词word
    // 可进而使用递归法实现add
    public void add(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            // 若.next中没有当前字符对应的节点，put创建
            if(cur.next.get(c) == null)
                cur.next.put(c, new Node());
            cur = cur.next.get(c);
        }

        // 若cur.isWord已经为True，表明当前单词在字典树中已经正确标识了结尾标志
        if(!cur.isWord){
            cur.isWord = true;
            size ++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word){

        Node cur = root;
        for(int i = 0 ; i < word.length() ; i ++){
            char c = word.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }
        // 全部遍历完之后，不能return true
        // 若isWord为True才说明该单词存在于字典树中
        // 例如panda和pan的例子，若字典树中存在单词panda
        // 但是也能完整遍历pan，但是pan在n的位置没有标识isWord=True
        return cur.isWord;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix){
    	// 先指向root
        Node cur = root;
        for(int i = 0 ; i < prefix.length() ; i ++){
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null)
                return false;
            cur = cur.next.get(c);
        }

        return true;
    }
}
