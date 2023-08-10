package william.basic;

/**
 * @author ZhangShenao
 * @date 2023/8/10 1:50 PM
 * @description: 给定一个参数 N，返回 1!+2!+3!+4!+…+N! 的结果
 */
public class FactorialSum {
    
    public static void main(String[] args) {
        //1 + 2 + 6 + 24 + 120 = 153
        System.out.println(factorialSum(5));
        
        //1 + 2 + 6 + 24 + 120 + 720 = 873
        System.out.println(factorialSum(6));
    }
    
    public static int factorialSum(int num) {
        if (num <= 0) {
            return 0;
        }
        if (num == 1) {
            return 1;
        }
        int sum = 1;
        int cur = 1;
        for (int i = 2; i <= num; i++) {
            cur = cur * i;  //记录前一次阶乘的结果
            sum += cur;
        }
        
        return sum;
    }
}
