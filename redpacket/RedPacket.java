
/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/10
 * 红包算法（公众号：算法之灵魂探索）
 */
public class RedPacket {

    /**
     * 实现M元（M是两位小数位数）红包的完全随机划分，最小分割单位为0.01元，分割成N分,没人最少可领取到0.01元
     * 数据约定（0 < M < 10000; 0 < N <= 100)
     */
    public static void main(String[] args) {
        final int[] N = new int[]{2, 5, 10};
        final int M = 100;
        for (int n : N) {
            final int[] result = new int[n];
            System.out.printf("%s个人瓜分%s元红包!\n", n, M);
            randomRedPacket3(M, n, result);
            for (int i : result) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    /**
     * 思路一：假设当前还有n个人未领取，红包剩余金额为m元
     * n 个人排队按顺序领取，每个人可领取[1 ～（m - (n - 1))]区间的随机金额
     */
    private static void randomRedPacket(int m, int n, int[] result){
        int index = result.length - n;
        if(index == result.length - 1){
            result[index] = m;
            return;
        }
        int randomValue = (int) (Math.random() * (m - (n - 1)) + 1);
        result[index] = randomValue;
        randomRedPacket(m - randomValue, n - 1, result);
    }

    /**
     * 思路二：N个人，每人先领取1元。剩余的(M - N)元分成（M - N）份，依次随机分发给N个人当中的一个
     */
    private static void randomRedPacket2(int m, int n, int[] result){
        for (int i = 0; i < result.length; i++) {
            result[i] = 1;
        }
        for (int i = 0; i < m - n; i++) {
            result[(int) (Math.random() * n)] += 1;
        }
    }

    /**
     * 思路三：N个人，每人先领取1元。
     * 剩余的(M - N)元分成(M - N)份，在[0, (M - N)]中生成(N - 1)个随机数作为随机位置。
     * 用这(N - 1)个随机位置将剩余的（M - N）元分成N份，一次分发给每个人
     */
    private static void randomRedPacket3(int m, int n, int[] result){
        Arrays.fill(result, 1);
        int[] indexArr = new int[n - 1];
        int lastMoney = m - n;
        for (int i = 0; i < n - 1; i++) {
            int index = (int) (Math.random() * lastMoney);
            indexArr[i] = index;
        }
        Arrays.sort(indexArr);
        for (int i = 0; i < result.length; i++) {
            if(i == 0){
                result[i] += indexArr[i];
            }else if(i == n - 1){
                result[i] += lastMoney - indexArr[i - 1];
            }else{
                result[i] += indexArr[i] - indexArr[i - 1];
            }
        }
    }
}
