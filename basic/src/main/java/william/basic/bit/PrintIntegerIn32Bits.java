package william.basic.bit;

/**
 * @author ZhangShenao
 * @date 2023/8/10 9:58 AM
 * @description: 将整数按照32位二进制格式打印
 */
public class PrintIntegerIn32Bits {
    
    public static void main(String[] args) {
        printIntIn32Bits(5);
        printIntIn32Bits(1024);
        printIntIn32Bits(0);
        printIntIn32Bits(Integer.MAX_VALUE);
        printIntIn32Bits(Integer.MIN_VALUE);
    }
    
    private static void printIntIn32Bits(int num) {
        StringBuilder result = new StringBuilder();
        for (int i = 31; i >= 0; i--) {
            String bit = ((num & (1 << i)) == 0) ? "0" : "1";
            result.append(bit);
        }
        System.out.println(result);
    }
}
