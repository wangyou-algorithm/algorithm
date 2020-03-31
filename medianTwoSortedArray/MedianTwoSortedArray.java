
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/17
 * 取两个有序数组的中位数
 * 公众号：算法之灵魂拷问
 */
public class MedianTwoSortedArray {
    public static void main(String[] args) {
//        System.out.println(getFirstK(new int[]{0 ,0}, new int[]{0 ,0}, 0, 1, 0, 1, 3));
//        System.out.println(getFirstK(new int[]{1, 3, 4}, new int[]{2}, 0, 2, 0, 0, 3));
        System.out.println(findMedianSortedArrays1(new int[]{1,2,4,7,9,10,18}, new int[]{2, 8,12,19,21,27}));
    }

/**
 * 常规法
 * @param nums1 数组1
 * @param nums2 数组2
 * @return 中位数
 * 公众号：算法之灵魂拷问
 */
public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
    int length1 = nums1.length;
    int length2 = nums2.length;
    //计算两个数组总长度
    int totalLength = length1 + length2;
    //标示中间数中比较小的一个数
    int middle1 = Integer.MIN_VALUE;
    //标示中间数中比较大的一个数
    int middle2 = Integer.MIN_VALUE;
    //数组1的索引，用于遍历数组1
    int index1 = 0;
    //数组2的索引，用于遍历数组2
    int index2 = 0;
    for (int i = 0; i <= totalLength / 2; i++) {
        //将稍大的中间值复制给较小的中间值，因为即将到来一个更大的
        middle1 = middle2;
        //使用数组1中的元素的前提是数组1没有越界并且数组1在该位置的元素小于数组2或者数组2已越界
        if (index2 >= length2 || (index1 < length1 && nums1[index1] < nums2[index2])) {
            //从数组1中取值，作为较大的中间值
            middle2 = nums1[index1++];
        } else {
            //从数组2中取值，作为较大的中间值
            middle2 = nums2[index2++];
        }
    }
    //如果总元素个数是偶数，那么取两数的平均值
    if ((totalLength % 2) == 0){
        return (middle1 + middle2) / 2.0;
    } else{
        //否则直接取较大的中间数即可
        return middle2;
    }
}

    /**
     * 二分法
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 中位数
     * 公众号：算法之灵魂拷问
     */
    public static double findMedianSortedArrays1(int[] nums1, int[] nums2){
        int length1 = nums1.length;
        int length2 = nums2.length;
        int totalLength = length1 + length2;

        int middel1 = (totalLength + 1) / 2;
        int middel2 = totalLength / 2 + 1;
        //经过以上两部计算后，总长度为基数的得到的middel1等于middel2

        //经过处理后能保证nums1始终是短的数组
        if(length1 > length2){
            return findMedianSortedArrays1(nums2, nums1);
        }
        //当nums1为空时，中位数就是数组2的中位数
        if(length1 == 0){
            return (nums2[middel1 - 1] + nums2[middel2 - 1]) / 2.0;
        }
        //如果nums1的最大值小于nums2的最小值，则可以将num1和num2想像成一个合并的数组进行求解
        if(nums1[length1 - 1] < nums2[0]){
            //如果两个数组长度相等，那么中位数一定是（num1的最大值+num2的最小值）/2
            if(length1 == length2){
                return (nums1[length1 - 1] + nums2[0]) / 2.0;
            }else{
                //如果不想等，即num2的长度大于nums1，那么中位数就是nums2当中（middel1 - nums1.length）位置和（middel2 - nums1.length）的元素的和/2
                return (nums2[middel1 - nums1.length - 1] + nums2[middel2 - nums1.length - 1]) / 2.0;
            }
        }
        return (getFirstK(nums1, nums2, 0, length1 - 1, 0, length2 - 1, middel1) + getFirstK(nums1, nums2, 0, length1 - 1,  0,length2 - 1, middel2)) / 2.0;
    }

    /**
     * 求两个有序数组中第k小的元素
     * @param nums1 数组1
     * @param nums2 数组2
     * @param left1 数组1参与计算的元素的左侧索引位置
     * @param right1 数组1参与计算的元素的右侧索引位置
     * @param left2 数组2参与计算的元素的左侧索引位置
     * @param right2 数组2参与计算的元素的右侧索引位置
     * @param k k
     * @return
     * 公众号：算法之灵魂拷问
     */
    private static int getFirstK(int[] nums1, int[] nums2, int left1, int right1, int left2, int right2, int k){
        //如果数组2中参与计算的元素为0，则直接返回数组1中第k个参与计算的元素
        if(right2 - left2 + 1 == 0){
            return nums1[left1 + k - 1];
        }
        //如果数组1中参与计算的元素为0，则直接返回数组2中第k个参与计算的元素
        if(right1 - left1 + 1 == 0){
            return nums2[left2 + k - 1];
        }
        //如果k为1，直接返回数组1和数组2的参与计算的元素中的最小值
        if(k == 1){
            return Math.min(nums1[left1], nums2[left2]);
        }
        //标记下次二分的中间位置，每次排除k/2个元素
        int middelIndex1 = Math.min((left1 + right1) / 2, left1 + k / 2 - 1);
        int middelIndex2 = Math.min((left2 + right2) / 2, left2 + k / 2 - 1);
        //递归，二分法
        if(nums1[middelIndex1] > nums2[middelIndex2]){
            return getFirstK(nums1, nums2, left1, right1, middelIndex2 + 1, right2, k - (middelIndex2 + 1 - left2));
        }else{
            return getFirstK(nums1, nums2, middelIndex1 + 1, right1, left2, right2, k - (middelIndex1 + 1 - left1));
        }
    }
}
