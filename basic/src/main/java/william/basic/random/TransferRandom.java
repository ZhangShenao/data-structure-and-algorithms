package william.basic.random;


/**
 * @author ZhangShenao
 * @date 2023/8/15 9:23 AM
 * @description: 随机数转移
 * <p>已知一个随机函数f(),可以等概率生成[1,5]之间的随机整数。现在要求仅利用该函数,等概率生成[1,7]之间的随机整数。</p>
 */
public class TransferRandom {
    
    public static void main(String[] args) {
        int[] counts = new int[8];
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
     * 已知一个随机函数f(),可以等概率生成[1,5]之间的随机整数
     */
    private static int f() {
        return (int) (Math.random() * 5) + 1;
    }
    
    /**
     * 利用函数f()构造函数f1(),创建等概率的0/1发生器
     */
    private static int f1() {
        int res = 0;
        do {
            res = f();
        } while (res == 3);
        return (res < 3) ? 0 : 1;
    }
    
    /**
     * 利用函数f1(),构造函数f2(),可以等概率生成[0,6]区间内随机整数 最大值6需要3位二进制表示,因此需要生成三次随机数
     */
    private static int f2() {
        int res = 0;
        do {
            res = (f1() << 2) + (f1() << 1) + f1();
        } while (res == 7);
        return res;
    }
    
    /**
     * 最终函数g()=f2()+1,可以等概率生成[1,7]区间内随机整数
     */
    private static int g() {
        return f2() + 1;
    }
}
