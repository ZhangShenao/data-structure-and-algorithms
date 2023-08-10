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
