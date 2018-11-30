/*
350 两个数组的交集
给定两个数组，编写一个函数来计算它们的交集。
返回非unique的结果，即输出结果中每个元素出现的次数与元素在两个数组中出现的次数一致
采用Map来实现， 暂时使用TreeMap，也可使用速度更快的HashMap
*/
import java.util.ArrayList;
import java.util.TreeMap;

public class Solution {

    public int[] intersect(int[] nums1, int[] nums2) {

        TreeMap<Integer, Integer> map = new TreeMap<>();
        // 先将第一个数组中的值存入映射map中， value为元素出现的次数
        for(int num: nums1){
            if(!map.containsKey(num))
                map.put(num, 1);
            else
                map.put(num, map.get(num) + 1);
        }
        
        // 不知道有多少可以匹配的，所以构建动态数组保存交集元素
        ArrayList<Integer> res = new ArrayList<>();
        for(int num: nums2){
            if(map.containsKey(num)){
                res.add(num);
                map.put(num, map.get(num) - 1);
                if(map.get(num) == 0)
                    map.remove(num);
            }
        }

        // 构建题目要求的返回类型
        int[] ret = new int[res.size()];
        for(int i = 0 ; i < res.size() ; i ++)
            ret[i] = res.get(i);

        return ret;
    }
}
