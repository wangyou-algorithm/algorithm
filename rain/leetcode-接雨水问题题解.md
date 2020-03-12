>雨纷纷，旧故里草木深。  
我听闻，你始终一个人。

今天忘忧跟大家一起探索一个跟雨水有关的算法，题目来源leetcode。

### 问题描述
>给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。</br></br>下面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。

![](https://mmbiz.qpic.cn/mmbiz_jpg/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppiciaj7J3fRspB42LX0fyxaDMs1VXTuWJwbJs9ia9n5Uziborh9SNRnQJTFA/0?wx_fmt=jpeg)
示例：
- 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
- 输出: 6

### 问题抽象
> 这个问题可以抽象成一个网格，共有m（最高的柱子） * n（柱子数量）个格子，求哪些格子可以装水。如下图所示：
![](https://mmbiz.qpic.cn/mmbiz_jpg/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppicSRpg30SJYicbf8qrOKtrBOnGE6EvLLZEOrVzZwntf9wUhwk3aA8JkSA/0?wx_fmt=jpeg)

### 问题关键点分析
> - 本题的关键点在于，具备什么条件的格子可以存水？
> - 图中3 * 12个格子共被分为了三类。</br>黑色部分已被柱子所占据，故不能存水。</br>白色部分的左侧或者右侧，至少有一侧没被柱子挡住，最终水会流失掉。</br>蓝色部分，未被柱子占据且左右侧均有柱子可以挡住水的流失，故能存水。</br>由此得出结论，能存水的位置必须具备两个条件：(1) 空间未被柱子占据；(2) 左右两侧均有比当前位置高的柱子。

### 思路一
> 1. 求出最高的柱子和最低的柱子；
> 2. 从最低的柱子的高度开始遍历，遍历当前高低下所有的位置（方格），判断当前方格是否没有柱子且左右均有大于等于当前位置高度的柱子。
> 3. 层级加1，循环2的操作，直至层级大于最高柱子的层级结束。

代码实现：
```java
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
        //遍历当前层级的所有位置（方格）
        //省略掉第一个位置和最后一个位置的判断，因为第一个位置和最后一个位置不会存水
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
```

运行结果：

![](https://mmbiz.qpic.cn/mmbiz_jpg/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppic1qNMu02AVGJgN5P6Igc8J8mXOUHIT9iaZ6DsJjkaQvB6lkWPpz181Cg/0?wx_fmt=jpeg)

ps:搞定！but，提交leetcode后，发现代码欠佳呀～

![](https://mmbiz.qpic.cn/mmbiz_png/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppicqqVHyHfcj0T6F0TYKpu8aMIp7qGvUh5iahxUKcNn1mtw9LWAuNxkVQA/0?wx_fmt=png)

死在了最后一个测试用例上。

### 思路二
> 1. 找出最高的柱子的高度和位置。
> 2. 对最高柱子左侧的所有空格做判断，此时只需要判断左侧有没有比当前位置高的即可，右侧同理；此时的时间复杂度仅仅为O(n)，远小于思路一的O(n^2);

代码实现：
```java
public static int trap(int[] height){
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
```
运行结果：

![](https://mmbiz.qpic.cn/mmbiz_jpg/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppic1qNMu02AVGJgN5P6Igc8J8mXOUHIT9iaZ6DsJjkaQvB6lkWPpz181Cg/0?wx_fmt=jpeg)

提交结果：

![](https://mmbiz.qpic.cn/mmbiz_png/ap0KQVQrVrZHOOJkHrwS0mMgRG212ppicyXpNxtboC9PBN1ic5vw7icYjRRYZZ79gZ8FpywrvlGRuSFzGpmzTqIibw/0?wx_fmt=png)

搞定！当然，这个思路不一定是最优解，这个算法本身也存在这一些可以优化的地方，如果大家感兴趣，可以深入研究，在此忘忧就不再继续深入了。

**PS：**忘忧很期待与大家的交流，大家有任何问题可以通过公众号给忘忧留言，忘忧都会认真回复的，期望与大家在交流中一起成长！

>>结束语：长风破浪会有时，直挂云帆济沧海！

完整代码请参考：<a href="https://github.com/wangyou-algorithm/algorithm/blob/master/rain/Rain.java">Rain.java</a>
