public class Array<E> {
    /**
     * 关于动态数组部分，通过resize部分，指定新数组的长度来设定。
     * resize方法定义成了private类型，用户无法手动扩容，
     * 当调用相应方法时，如add方法时数组空间不够了，会自动调用resieze方法进行数组空间的动态变化
     */
    private E[] data;
    // size很重要，即表示了数组中有多少个元素，同时也指向了第一个没有元素的位置
    private int size;

    /**
     * 构造函数，传入数组的容量capacity构造Array
     * @param capacity
     */
    public Array(int capacity){
        data = (E[]) new Object[capacity];
        size = 0;
    }

    // 无参数的构造函数，默认素组的容量capacity=10
    public Array(){
        // 括号中的capacity是IDE自动提示的功能，而非Java语法
        this(10);
    }

    // 获取数组的容量
    public int getCapacity(){
        return data.length;
    }

    // 获取数组中的元素个数
    public int getSize(){
        return size;
    }

    // 返回数组是否为空
    public boolean isEmpty(){
        return size == 0;
    }

    // 向所有元素后添加一个新元素
    // 虽然在数组的末尾添加元素的时间复杂度为O(1)，但是计算时间复杂度通常按照最坏的情况(添加到数组开头的位置)来计算，
    // 所以数组的添加元素的时间复杂度为O(n)
    public void addLast(E e){

//      同样的功能已经由add方法实现
//      if(size == data.length)
//          throw new IllegalArgumentException("AddLast failed. Array is full.");
//       data[size] = e;
//       size ++;
        add(size, e);
    }

    // 在所有元素前添加一个新元素
    // 同理也可实现在指定位置之前添加一个新元素
    public void addFirst(E e){
        add(0, e);
    }

    // 在index索引的位置插入一个新元素e
    public void add(int index, E e){
        if(index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Require index >= 0 and index <= size.");

        if(size == data.length)
            // 通过resize将data的引用指向了新的resize后的地址
            resize(2 * data.length);

        for(int i = size - 1; i >= index ; i --)
            data[i + 1] = data[i];

        data[index] = e;

        size ++;
    }

    // 在数组中查询元素和修改元素

    // 获取index索引位置的元素
    // 在知道索引的情况下get查找的时间复杂度为O(1)
    public E get(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. Index is illegal.");
        return data[index];
    }

    // 修改index索引位置的元素为e
    // 修改操作的时间复杂度为O(1),体现了数组随机访问的特性
    public void set(int index, E e){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Set failed. Index is illegal.");
        data[index] = e;
    }

    public E getLast(){
        return get(size - 1);
    }

    public E getFirst(){
        return get(0);
    }
    //数组中的包含，搜索和删除元素

    // 查找数组中是否有元素e
    // 时间复杂度O(n)
    public boolean contains(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i] == e)
                return true;
        }
        return false;
    }

    // 查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    // 时间复杂度O(n)
    // 该find方法只能找到数组中有没有该元素，可以从新设计一个方法，查找数组中存在的所有该方法
    public int find(E e){
        for(int i = 0 ; i < size ; i ++){
            if(data[i] == e)
                return i;
        }
        return -1;
    }

    // 从数组中删除index位置的元素, 返回删除的元素
    // 与add的操作类似，所以综合来看删除操作的时间复杂度也为O(n)
    public E remove(int index){
        if(index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. Index is illegal.");

        E ret = data[index];
        // 删除元素操作：该元素后面的元素都需要进行左移操作
        for(int i = index + 1 ; i < size ; i ++)
            data[i - 1] = data[i];
        // 此时size指向了原本的data[i]对应的值(左移操作之前，左移之后data[i-1]==data[i]
        // 该操作没有问题，因为对于外部的使用数组的用户来说，无法拿到data[size]处的值，可以把该值想象成数组初始化时的默认值
        size --;
        data[size] = null;  // loitering objects != memory leak
        // 当数组长度变成数组长度的四分之一的时候再进行缩容，并且是缩成长度的二分之一
        // 这种操作可以防止resize操作中复杂度震荡的现象
        // 复杂度震荡：连续反复进行addLast和removeLast操作以至于连续进行resize操作
        if(size == data.length / 4 && data.length /2  != 0)
            resize(data.length / 2);

        return ret;
    }

    // 从数组中删除第一个元素, 返回删除的元素
    public E removeFirst(){
        return remove(0);
    }

    // 从数组中删除最后一个元素, 返回删除的元素
    public E removeLast(){
        return remove(size - 1);
    }

    // 从数组中删除元素e
    // 该方法并不严谨，数组定义的可以存放重复值，该方法只能删掉找到的第一个e值
    public void removeElement(E e){
        // 先找到index，转化为删除数组中指定索引对应的元素
        int index = find(e);
        if(index != -1)
            remove(index);
    }


    // 将数组空间的容量变成newCapacity大小
    // resize的时间复杂度为O(n),因为需要把原始的数据完全复制一遍
    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity];
        for(int i = 0 ; i < size ; i ++)
            newData[i] = data[i];
        data = newData;
    }


    @Override
    // 定义类在打印输出的时候的打印信息
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        // 使用size而非data.length，因为size中是已经添加的元素，而length长度内可能有很多是垃圾数据
        for(int i = 0 ; i < size ; i ++){
            res.append(data[i]);
            // 判断是否为最后一个元素
            if(i != size - 1)
                res.append(", ");
        }
        res.append(']');
        // 直接打印res也会得到和res.toString相同的结果，但是return的时候StringBuilder必须返回toSring之后的对象
        //System.out.println(res);
        return res.toString();
    }
}
