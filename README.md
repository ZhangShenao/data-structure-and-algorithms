# data-structure-and-algorithms
# 1. 位运算与排序基础

## 二进制与位运算

### 算法：将一个 int 类型的整数按照32位二进制形式打印

代码实现：

```Java
private static void printIntIn32Bits(int num) {
    StringBuilder result = new StringBuilder();
    for (int i = 31; i >= 0; i--) {
        String bit = ((num & (1 << i)) == 0) ? "0" : "1";
        result.append(bit);
    }
    System.out.println(result);
}
```

### 补码表示法

整数补码：最高位符号位为0，其余位为绝对值对应的二进制

负数补码：最高位符号位为1，其余位为绝对值对应的二进制按位取反后+1

为什么如此设计？

**+-\*/ 四则运算，底层都是基于二进制的位运算（如与、或、非、同或、异或、移位等）实现的。采用补码表示法，可以将整数和负数的运算逻辑统一，无需单独实现两套逻辑，提升了整体的运算效率。**

## 算法概述

### 什么是算法

- 有具体的问题
- 针对这个问题，设计具体的解决流程
- 有评价处理流程的可量化指标

### 算法的分类

- 明确知道怎么算的流程
- 明确知道怎么尝试的流程（递归、分治...）

### 算法：给定一个参数 N，返回 1!+2!+3!+4!+…+N! 的结果

代码实现：

```Java
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
```
