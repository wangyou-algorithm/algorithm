import java.util.HashSet;
import java.util.Set;

/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/19
 * 无重复字符的最大子串
 * 公众号：算法之灵魂拷问
 */
public class LongestSubstring {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring1("pwwkew"));
    }

    /**
     * 暴力解法，获取最长的无重复的子串的长度
     * @param s 输入的字符串
     * @return 最大子串长度
     * 公众号：算法之灵魂拷问
     */
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        int maxSubStrLength = 0;
        //i标记起始位置
        for (int i = 0; i < length; i++) {
            //j标记当前判断的位置
            for (int j = i; j < length; j++) {
                boolean flog = false;
                //判断字符串的[i, j - 1]的区间内有无跟第i个字符相同的字符
                for (int k = i; k < j; k++) {
                    if(s.charAt(k) == s.charAt(j)){
                        flog = true;
                        break;
                    }
                }
                //如果字符串的[i, j - 1]的区间内有跟第i个字符相同的字符
                if(flog){
                    //当前所遍历的最大长度为之前的最大长度或者(j - i)中的最大值
                    maxSubStrLength = Math.max(maxSubStrLength, j - i);
                    break;
                }else{
                    //如果不存在，当前所遍历的最大长度为之前的最大长度或者(j - i + 1)中的最大值
                    //其中，加的1即为第i个元素
                    maxSubStrLength = Math.max(maxSubStrLength, j - i + 1);
                }
            }
        }
        return maxSubStrLength;
    }

    /**
     * 滑动窗口法
     * @param s 输入的字符串
     * @return 字符串最长无重复字串的长度
     * 公众号：算法之灵魂拷问
     */
    public static int lengthOfLongestSubstring1(String s) {
        int length = s.length();
        if(length <= 1){
            return length;
        }
        //滑动窗口的右侧位置
        int right = 0;
        //滑动窗口的左侧位置
        int left = 0;
        //当前匹配到的最大子串长度
        int maxSubStrLength = 0;
        //窗口
        Set<Character> set = new HashSet<>(length);
        //遍历
        while(right < length){
            //如果窗口中包含即将划入窗口的元素
            if(set.contains(s.charAt(right))){
                //将窗口最左侧的元素移除
                set.remove(s.charAt(left++));
            }else{
                //如果窗口中不包含即将划入窗口的元素，那么将元素划入窗口
                set.add(s.charAt(right));
                //更新最大字串长度
                maxSubStrLength = Math.max(maxSubStrLength, set.size());
                //窗口右侧标记加一
                right++;
            }
        }
        return maxSubStrLength;
    }
}
