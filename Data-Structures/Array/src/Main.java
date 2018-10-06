public class Main {

    public static void main(String[] args) {

        Array<Integer> arr = new Array(10);
        for(int i = 0 ; i < 10 ; i ++)
            arr.addLast(i);
        System.out.println(arr);

        // 一开始定义的数组空间只有10，当空间满了之后再进行添加的时候会自动进行扩容
        arr.add(1, 100);
        System.out.println(arr);

        arr.addFirst(-1);
        System.out.println(arr);
        // [-1, 0, 100, 1, 2, 3, 4, 5, 6, 7, 8, 9]

        // 查看删减元素时动态减小数组空间的效果
        arr.remove(1);
        System.out.println(arr);


        arr.remove(2);
        System.out.println(arr);


        arr.remove(3);
        System.out.println(arr);

/*        arr.remove(2);
        System.out.println(arr);

        arr.removeElement(4);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);*/

    }
}
