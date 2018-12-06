/*
https://leetcode-cn.com/problems/range-sum-query-immutable/
给定一个整数数组  nums，求出数组从索引 i 到 j  (i ≤ j) 范围内元素的总和，包含 i,  j 两点。
给定 nums = [-2, 0, 3, -5, 2, -1]，求和函数为 sumRange()

sumRange(0, 2) -> 1
sumRange(2, 5) -> -1
sumRange(0, 5) -> -3
*/
// 因为数组是不可变的情况，虽然可以使用线段树来解决，但是效率并不高
// 使用数组和动态规划来解决
public class NumArray {

    private int[] sum; // sum[i]存储前i个元素和, sum[0] = 0
                       // 即sum[i]存储nums[0...i-1]的和
                       // sum(i, j) = sum[j + 1] - sum[i]
    public NumArray(int[] nums) {

        sum = new int[nums.length + 1];
        sum[0] = 0;
        for(int i = 1 ; i < sum.length ; i ++)
            sum[i] = sum[i - 1] + nums[i - 1];
    }

    public int sumRange(int i, int j) {
        return sum[j + 1] - sum[i];
    }
}