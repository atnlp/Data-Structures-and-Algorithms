public class LoopQueue<E> implements Queue<E> {

    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity){
        // 因为循环队列设计时浪费了一个空间，实际空间比capacity多一个空间
        data = (E[])new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    // 无参构造函数，内部调用有参构造函数，赋默认capacity为10
    public LoopQueue(){
        this(10);
    }

    // 因为有一个空间被浪费了
    public int getCapacity(){
        return data.length - 1;
    }

    // 重写接口中的方法
    @Override
    public boolean isEmpty(){
        return front == tail;
    }

    @Override
    public int getSize(){
        return size;
    }

    // 入队操作
    @Override
    public void enqueue(E e){
        // 判定循环队列是否已满，因为额外留了一个空间，所以此处有+1操作
        if((tail + 1) % data.length == front)
            // getCapacity * 2 , 而非data.length * 2
            resize(getCapacity() * 2);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size ++;
    }

    // 出队操作
    @Override
    public E dequeue(){
        if(isEmpty())
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");

        E ret = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size --;
        if(size == getCapacity() / 4 && getCapacity() / 2 != 0)
            resize(getCapacity() / 2);
        return ret;
    }

    // 查看队首元素
    @Override
    public E getFront(){
        if(isEmpty())
            throw new IllegalArgumentException("Queue is empty.");
        return data[front];
    }

    private void resize(int newCapacity){

        E[] newData = (E[])new Object[newCapacity + 1];
        for(int i = 0 ; i < size ; i ++)
            // 队列的resize和动态数组的有些不同，data的索引和newData的索引有个front的偏移
            // 因为是循环队列，需要对data.length求余
            newData[i] = data[(i + front) % data.length];

        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public String toString(){

        StringBuilder res = new StringBuilder();
        res.append(String.format("Queue: size = %d , capacity = %d\n", size, getCapacity()));
        res.append("front [");
        // 循环队列元素输出时，遍历的写法，因为tail的索引可能比front还小，因此不能用传统的(i < tail, i++)判断方法。
        for(int i = front ; i != tail ; i = (i + 1) % data.length){
            res.append(data[i]);
            // 判断当前索引不是队列中的最后一个元素，在非循环队列中可用(i != size - 1)方式。
            if((i + 1) % data.length != tail)
                res.append(", ");
        }
        res.append("] tail");
        return res.toString();
    }

    public static void main(String[] args){

        LoopQueue<Integer> queue = new LoopQueue<>();
        for(int i = 0 ; i < 10 ; i ++){
            queue.enqueue(i);
            System.out.println(queue);

            if(i % 3 == 2){
                queue.dequeue();
                System.out.println(queue);
            }
        }
    }
}
