/*
349 两个数组的交集
返回去重的结果，即使用Set来实现
*/
import java.util.ArrayList;
import java.util.TreeSet;

class Solution349 {
    public int[] intersection(int[] nums1, int[] nums2) {

        TreeSet<Integer> set = new TreeSet<>();
        // 将第一个数组中的指传入Set中
        for(int num: nums1)
            set.add(num);

        // 不知道有多少可以匹配的，所以构建动态数组保存交集元素
        ArrayList<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(set.contains(num)){
                list.add(num);
                // 将Set中匹配后的元素删除，以防数组2中有多个相同元素匹配
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            res[i] = list.get(i);
        return res;
    }
}