package william.basic;

/**
 * @author ZhangShenao
 * @date 2023/8/14 2:04 PM
 * @description: 指数随机数
 * <p>实现一个随机函数,返回一个[0,1)范围内的随机数,使得该随机数落在[0,x)范围内的概率为 x^2,其中 0 <= x <= 1</p>
 * <p>通过Math.max函数,生成两次随机数,并取最大值。为了使得最终结果落在[0,x)范围内,必须保证两次生成的随机数都落在[0,x)范围内,最终的概率为x^2。</p>
 */
public class ExponentRandom {
    
    public static void main(String[] args) {
        double X = 0.18;
        double count = 0;
        int times = 10000000;
        for (int i = 0; i < times; i++) {
            double rand = exponentRandom();
            if (rand < X) {
                ++count;
            }
        }
        
        double prop = count / times;
        System.out.println("[," + X + ")范围内随机数出现的概率: " + prop);
        System.out.println("X^2 = " + Math.pow(X, 2));
    }
    
    private static double exponentRandom() {
        return Math.max(Math.random(), Math.random());
    }
}
