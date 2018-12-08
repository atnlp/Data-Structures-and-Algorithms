// 我们的第二版Union-Find
public class UnionFind2 implements UF {

    // 我们的第二版Union-Find, 使用一个数组构建一棵指向父节点的树
    // parent[i]表示第一个元素所指向的父节点
    private int[] parent;

    // 构造函数
    public UnionFind2(int size){

        parent = new int[size];

        // 初始化, 每一个parent[i]指向自己, 表示每一个元素自己自成一个集合
        for( int i = 0 ; i < size ; i ++ )
            parent[i] = i;
    }

    @Override
    public int getSize(){
        return parent.length;
    }

    // 查找过程, 查找元素p所对应的集合编号
    // O(h)复杂度, h为树的高度
    private int find(int p){
        if(p < 0 || p >= parent.length)
            throw new IllegalArgumentException("p is out of bound.");

        // 不断去查询自己的父亲节点, 直到到达根节点，即p=parent[p]
        // 根节点的特点: parent[p] == p
        while(p != parent[p])
            p = parent[p];
        return p;
    }

    // 查看元素p和元素q是否所属一个集合
    // O(h)复杂度, h为树的高度
    @Override
    public boolean isConnected( int p , int q ){
        // 判断两个节点是否属于同一个集合，通过find找到其对应的根节点，判断根节点是否相同
        // 所以将查询操作的时间复杂度由O(n)提升为O(h)
        return find(p) == find(q);
    }

    // 合并元素p和元素q所属的集合
    // O(h)复杂度, h为树的高度
    @Override
    public void unionElements(int p, int q){

        int pRoot = find(p);
        int qRoot = find(q);
        // 已经属于同一个集合
        if( pRoot == qRoot )
            return;
        // 或parent[qRoot] = pRoot, 作用一样
        parent[pRoot] = qRoot;
    }
}