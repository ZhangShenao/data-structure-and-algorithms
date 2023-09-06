package william.basic.xor;

/**
 * @author ZhangShenao
 * @date
 * @description 给定一个int类型的数, 提取出其最右侧的1
 * 举例:给定一个数字10,其二进制表示为1010,要求提取出其最右侧的1,返回:0010。
 * 最终结果为: 将数字与上其按位取反后+1，即 res = n & ((~n) + 1)。
 */
public class 提取最右侧的1 {
    public static void main(String[] args) {
        int n = 1024000;
        System.out.printf("目标数字为: %d\t, 二进制表示为: %s\n", n, Integer.toBinaryString(n));
        int res = extractRightOne(n);
        System.out.printf("提取最右侧的1, 结果为: %s\n", Integer.toBinaryString(res));
    }

    public static int extractRightOne(int n) {
        return n & ((~n + 1));
    }
}
