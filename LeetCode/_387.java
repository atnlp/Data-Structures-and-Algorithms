/*
387：字符串中的第一个唯一字符
给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
s = "leetcode"
返回 0.
s = "loveleetcode",
返回 2.
注意事项：您可以假定该字符串只包含小写字母。
*/

// 看到题目，想当然的想用Map来解决，分别放字符和对应的个数。立马写了个HashMap，发现结果错误才想起来HashSet是无序的，
// 改成LinkedHashMap运行通过，但是通过率很低，27%
// Java太不熟练了，各种基本方法都不知道，用的时候临时查了才知道，这个Map版权当熟悉Java用法了。
// 参见解法2，使用数组构造一个针对此问题的哈希表
import java.util.LinkedHashMap;
import java.util.HashMap;
class Solution {
    public int firstUniqChar(String s) {
        LinkedHashMap<Character, Integer> map = new LinkedHashMap<>();
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(!map.containsKey(c))
                map.put(c, 1);
            else
                map.put(c, map.get(c) + 1);
        }
    for (HashMap.Entry<Character, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1)
                return s.indexOf(entry.getKey());
        }
        return -1;
    }
}

// 解法2，使用数组构造一个针对此问题的哈希表
class Solution {
    public int firstUniqChar(String s) {

        int[] freq = new int[26];
        for(int i = 0 ; i < s.length() ; i ++)
            freq[s.charAt(i) - 'a'] ++;

        for(int i = 0 ; i < s.length() ; i ++)
            if(freq[s.charAt(i) - 'a'] == 1)
                return i;

        return -1;
    }
}