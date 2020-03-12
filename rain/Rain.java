
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/11
 * 接雨水问题（公众号：算法之灵魂探索）
 */
public class Rain {
    /**
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
     * @param args
     */
    public static void main(String[] args) {
        int[] height = new int[]{0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.print("原始数据为：[");
        for (int i = 0; i < height.length; i++) {
            System.out.print(height[i]);
            if(i != height.length - 1){
                System.out.print(",");
            }
        }
        System.out.println("]");
        System.out.println("结果为：" + trap(height));
    }

    /**
     * 思路一：时间复杂度较高，超时
     * @param height
     * @return
     */
    public static int trap(int[] height) {
        //1. 求出最高的和最低的柱子
        if(height.length == 0){
            return 0;
        }
        int min = height[0];
        int max = height[0];
        for (int i : height) {
            if(i > max) {
                max = i;
            }
            if(i < min){
                min = i;
            }
        }
        //记述，表示总计可以接住的雨水数量
        int sum = 0;
        //从最底层开始遍历，遍历到大于最高层结束
        for (int i = min; i <= max; i++){
            //遍历当前层级的所有位置（方格）（省略掉第一个位置和最后一个位置的判断，因为第一个位置和最后一个位置不会存水）
            for(int j = 1; j < height.length - 1; j++){
                //判断当前位置是否被柱子占据
                if(height[j] < i){
                    //标示左侧是否有大于等于当前位置的柱子
                    boolean left = false;
                    boolean right = false;
                    for (int k = 0; k < height.length; k++){
                        //判断左侧是否有柱子大于等于当前位置
                        if(k < j && height[k] >= i){
                            left = true;
                        }
                        //判断右侧是否有柱子大于等于当前位置
                        if(k > j && height[k] >= i){
                            right = true;
                        }
                    }
                    //如果左右均有柱子遮挡，总数量加一
                    if(left && right){
                        sum ++;
                    }
                }
            }
        }
        return sum;
    }

    public static int trap2(int[] height){
        if(height.length == 0){
            return 0;
        }
        //1. 遍历一遍，找出最高柱子的坐标
        int maxIndex = 0;
        for (int i = 0; i < height.length; i++) {
            if(height[i] > height[maxIndex]){
                maxIndex = i;
            }
        }
        int sum= 0;
        //遍历最高柱左侧
        if(maxIndex > 1){
            int leftMax = 0;
            for (int i = 1; i < maxIndex; i++){
                //当前柱子高度小于左侧最高柱
                if (height[i] < height[leftMax]) {
                    //该位置可以存放height[leftMax] - height[i]的水
                    sum += height[leftMax] - height[i];
                }else{
                    //否则标记当前坐标为左侧最高柱
                    leftMax = i;
                }
            }
        }
        //遍历最高柱左侧
        if(maxIndex < height.length - 2){
            int rightMax = height.length - 1;
            //从最右侧开始，每次往左移动一个位置，遍历到最高柱
            for (int i = height.length - 2; i > maxIndex; i--){
                if (height[i] < height[rightMax]) {
                    sum += height[rightMax] - height[i];
                }else{
                    rightMax = i;
                }
            }
        }
        return sum;
    }
}
