package william.basic.random;

/**
 * @author ZhangShenao
 * @date 2023/8/15 9:50 AM
 * @description: 等概率0/1发生器
 * <p>已知一个随机函数f(),可以不等概率生成0/1。现在要求仅利用该函数,等概率生成0/1。</p>
 */
public class EquallyProbableZeroOneGenerator {
    
    public static void main(String[] args) {
        int[] counts = new int[2];
        int times = 10000000;
        for (int i = 0; i < times; i++) {
            int rand = g();
            counts[rand]++;
        }
        for (int i = 0; i < counts.length; i++) {
            double p = ((double) counts[i]) / times;
            System.out.printf("生成%d的概率为%f\n", i, p);
        }
    }
    
    /**
     * 已知随机函数f(),可以不等概率生成0/1 生成0的概率为0.75,生成1的概率为0.25
     */
    private static int f() {
        return (Math.random() >= 0.75 ? 1 : 0);
    }
    
    /**
     * 利用函数f()构造等概率0/1发生器。 对f()生成两次随机数,如果为{0,0}或{1,1}则再次生成;如果为{0,1},则返回1;否则返回0。
     */
    private static int g() {
        int res = -1;
        do {
            res = f();
        } while (f() == res);
        return res;
    }
}
