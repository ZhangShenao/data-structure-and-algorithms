# data-structure-and-algorithms
# 1. 位运算

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

# 2. 排序基础

## 算法概述

### 什么是算法

- 有具体的问题
- 针对这个问题，设计具体的解决流程
- 有评价处理流程的可量化指标

### 算法的分类

- 明确知道怎么算的流程
- 明确知道怎么尝试的流程（递归、分治...）

### 算法的时间复杂度

**大 O 复杂度分析法：描述随着数据规模的增长，算法执行耗时的增长趋势。**

典型的时间复杂度：

- O(1)：常数时间复杂度
- O(logN)：二分查找、二叉树相关操作。每次计算可以缩小一半的范围。
- O(N)：遍历
- O(N*logN)：快速排序、归并排序、堆排序
- O(N^N)：基本排序算法。
- O(N!)：穷举

## 算法：给定一个参数 N，返回 1!+2!+3!+4!+…+N! 的结果

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

## 算法：选择排序

**算法思想：每趟在 [start,N-1] 范围内找到最小元素，并与start位置的元素进行交换。**

代码实现：

```Java
private static void selectionSort(int[] arr) {
    //边界条件校验
    if (arr == null || arr.length < 2) {
        return;
    }
    
    int N = arr.length;
    //每趟在[start,N-1]范围内找到最小元素,并与start位置的元素进行交换
    for (int start = 0; start < N - 1; start++) {
        int minIdx = start;
        for (int next = start + 1; next < N; next++) {
            if (arr[next] < arr[minIdx]) {
                minIdx = next;
            }
        }
        
        ArrayUtils.swap(arr, start, minIdx);
    }
}
```

## 算法：冒泡排序

**算法思想：每趟在 [0,end] 范围内，对相邻元素两两比较，将较大的元素交换到后面，最后使得 end 位置的元素为[0,end] 范围内的最大值。**

代码实现：

```Java
private static void bubbleSort(int[] arr) {
    //边界条件校验
    if (arr == null || arr.length < 2) {
        return;
    }
    
    //每趟在[0,end]范围内,对相邻元素两两比较,将较大的交换到后面,最后使得end位置的元素为[0,end]范围内的最大值
    int N = arr.length;
    for (int end = N - 1; end >= 1; end--) {
        for (int i = 0; i < end; i++) {
            if (arr[i] > arr[i + 1]) {
                ArrayUtils.swap(arr, i, i + 1);
            }
        }
    }
}
```

## 算法：插入排序

**算法思想：假定 [0,end] 范围内的元素已经有序，对于新加入的元素 end+1，通过向前依次比较交换，最终插入到合适的位置，最终实现 [0,end+1] 范围内的元素有序。**

算法实现：

```Java
private static void insertionSort(int[] arr) {
    //边界条件校验
    if (arr == null || arr.length < 2) {
        return;
    }
    
    //假定[0,end]范围内的元素已经有序,对于新加入的元素cur=arr[end+1],通过向前依次比较交换,最终插入到合适的位置,实现[0,end+1]范围内的元素有序
    int N = arr.length;
    for (int end = 1; end < N - 1; end++) {
        int curIdx = end + 1;   //记录当前元素索引
        //用当前元素依次向前比较,如果前面还有元素,且当前元素比前一个元素小,则进行交换
        while (curIdx >= 1 && arr[curIdx] < arr[curIdx - 1]) {
            ArrayUtils.swap(arr, curIdx, curIdx - 1);
            --curIdx;
        }
    }
}
```

# 3. 随机数

## 什么是数据结构

- 数据结构是存储和组织数据的方式
- 精心设计的数据结构，可以带来更高的存储和运行效率
- 数据结构是很多算法得以运行的载体

## 最基本的数据结构

### 数组

**便于寻址，不便于增删数据**

### 链表

**便于增删数据，不便于寻址**

## 算法：计算数组 [L,R] 范围内的元素和

**算法思想：构造前缀和数组 preSum，使得 preSum[i] = 数组 [0,i] 范围内的元素和。**

**那么，[L,R] 范围内的元素和 = preSum[R] - preSum[L - 1]**

算法实现：

```Java
/**
 * 计算数字arr[L,R]范围内的元素和
 */
private static int rangeSum(int[] arr, int L, int R) {
    //边界条件校验
    if (L < 0 || R >= arr.length || L > R) {
        throw new IllegalArgumentException();
    }
    
    int[] preSum = buildPreSumArray(arr);
    if (L == 0) {
        return preSum[R];
    }
    
    return (preSum[R] - preSum[L - 1]);
}

/**
 * 构造前缀和数组
 */
private static int[] buildPreSumArray(int[] arr) {
    int[] preSum = new int[arr.length];
    preSum[0] = arr[0];
    for (int i = 1; i < arr.length; i++) {
        preSum[i] = preSum[i - 1] + arr[i];
    }
    return preSum;
}
```

## 算法：实现一个随机函数，返回一个 [0,1) 范围内的随机数，使得该随机数落在 [0,x) 范围内的概率为 x^2，其中 0 <= x < 1。

**算法思想：通过 Math.max 函数，生成两次随机数，并取最大值。为了使得最终结果落在 [0,x) 范围内，必须保证两次生成的随机数都落在 [0,x) 范围内，最终的概率为 x^2。**

算法实现：

```Java
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
```

## 算法：已知一个随机函数 f()，可以等概率生成 [a,b] 之间的随机整数。现在要求仅利用该函数，等概率生成 [c,d] 之间的随机整数。

**算法思路：**

1. 利用函数 f() 构造函数 f1()，创建等概率的 0/1 发生器。
2. 将区间 [c,d] 转换为区间 [0,d-c]，判断最大值 (d-c) 需要几位二进制表示。
3. 利用函数 f1()，构造函数 f2()，可以等概率生成[0,d-c] 区间内随机整数。
4. 最终的函数 g() = f2() + c。

算法实现：[1,5] -> [1,7]

```Java
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
```

## 算法：已知一个随机函数 f()，可以不等概率生成 0/1。现在要求仅利用该函数，等概率生成 0/1。

**算法思路**：利用函数 f() 生成两次随机数，如果为 {0,0} 或 {1,1} 则再次生成；如果为 {0,1} ，则返回1；否则返回0。

算法实现：

```Java
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
```

# 4. 二分思想

## 算法：在有序数组中查找指定元素所在的索引

**算法思想：经典二分查找法。**

算法实现：

```Java
/**
 * 二分查找:在数组arr中查找指定元素target所在的索引。如果未找到则返回-1
 */
private static int binarySearch(int[] arr, int target) {
    //边界条件校验
    if (arr == null || arr.length == 0) {
        return -1;
    }
    
    int start = 0;
    int end = arr.length - 1;
    
    while (start <= end) {
        int mid = start + ((end - start) >> 1); //避免直接相加导致结果溢出
        if (arr[mid] == target) {
            return mid;
        }
        if (arr[mid] > target) {
            end = mid - 1;
        } else {
            start = mid + 1;
        }
    }
    return -1;
    
}
```

## 算法：在有序数组中找到第一个 >= target 的索引

**算法思想：借助二分查找法，记录当前 >= target 的索引，并不断向左侧区间查找，尝试更新索引。**

算法实现：

```Java
/**
 * 有序数组中arr中,找到第一个>=target的索引。如果未找到则返回-1
 */
private static int findFirstLE(int[] arr, int target) {
    //边界条件校验
    if (arr == null || arr.length == 0) {
        return -1;
    }
    
    int start = 0;
    int end = arr.length - 1;
    int index = -1; //记录当前>=target的索引
    while (start <= end) {
        int mid = start + ((end - start) >> 1);
        if (arr[mid] >= target) {    //找到了>=target的区间,更新索引值,并继续向左侧区间查找
            index = mid;
            end = mid - 1;
        } else {    //该区间均<target,向右侧区间查找
            start = mid + 1;
        }
    }
    
    return index;
}
```

## 算法：在有序数组中找到最后一个 <= target 的索引

**算法思想：借助二分查找法，记录当前 <= target 的索引，并不断向右侧区间查找，尝试更新索引。**

算法实现：

```Java
/**
 * 在有序数组arr中找到最后一个<=target的索引。如果未找到则返回-1
 */
private static int findLastLessOrEquals(int[] arr, int target) {
    //边界条件校验
    if (arr == null || arr.length == 0) {
        return -1;
    }
    
    int start = 0;
    int end = arr.length - 1;
    int index = -1; //记录当前<=target的索引
    while (start <= end) {
        int mid = start + ((end - start) >> 1);
        if (arr[mid] <= target) {    //找到了<=target的区间,更新索引值,并继续向右侧区间查找
            index = mid;
            start = mid + 1;
        } else {  //当前区间均>target,继续向左侧区间查找
            end = mid - 1;
        }
    }
    
    return index;
}
```

## 算法：找到数组中的局部最小值

算法描述：给定一个长度为 N 的无序数组，已知数组中的相邻元素均不相等，要求找到数组中任意一个局部最小值所在的索引。

对于局部最小值的定义为：

- 如果 arr[0] < arr[1]，则 arr[0] 为局部最小值。
- 如果 arr[N-1] < arr[N-2]，则 arr[N-1] 为局部最小值。
- 对于任意 arr[i]，0 < i < N-1，如果 arr[i] < arr[i-1] 且 arr[i] < arr[i+1]，则 arr[i] 为局部最小值。

**算法思想**

- **因为数组无序，且相邻元素均不相等，则数组一定存在至少一个局部最小值。**
- **首先考虑边界情况：**
  - **如果 arr[0] < arr[1]，则 arr[0] 为局部最小值。**
  - **如果 arr[N-1] < arr[N-2]，则 arr[N-1] 为局部最小值。**
- **边界情况不成立，说明 arr[1] < arr[0]，且 arr[N-2] < arr[N-1]，数组元素的趋势为先下降后上升，则局部最小值一定存在于 [0,N-1] 范围内。**
- **采用二分查找思想，对于任意范围 arr[start,end]，取中间位置 mid：**
  - **如果 arr[mid] < arr[mid-1]，且 arr[mid] < arr[mid+1]，则 arr[mid] 就是局部最小值。**
  - **如果 arr[mid] > arr[mid - 1]，则数组在 [start,mid] 范围内先下降后上升，该范围内一定存在局部最小值，在 [start,mid-1] 范围内继续查找。**
  - **否则 arr[mid] > arr[mid + 1]，则数组在 [mid,end] 范围内先下降后上升，该范围内一定存在局部最小值，在 [mid+1,end] 范围内继续查找。**

算法实现：

```Java
/**
 * 找到数组arr中局部最小值所在的索引。如果未找到则返回-1
 */
private static int findLocalMinIndex(int[] arr) {
    //处理边界条件
    if (arr == null || arr.length < 1) {
        return -1;
    }
    int N = arr.length;
    //如果数组只有一个元素,则arr[0]就是局部最小值
    if (N == 1) {
        return 0;
    }
    //处理首、尾处的局部最小值
    if (arr[0] < arr[1]) {
        return 0;
    }
    if (arr[N - 1] < arr[N - 2]) {
        return N - 1;
    }
    
    //数组呈先下降、后上升的趋势，局部最小值一定在[0,N-1]范围内。
    //采用二分查找思想,在[start,mid,end]范围内判断局部最小值
    int start = 0;
    int end = N - 1;
    while (start < end - 1) {    //要求[start,mid,end]区间内至少存在三个元素
        int mid = start + ((end - start) >> 1);
        if (arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]) {    //找到局部最小值,直接返回
            return mid;
        }
        if (arr[mid] > arr[mid] - 1) {   //数组在[start,mid]范围内先下降后上升,该范围内一定存在局部最小值,在[start,mid-1]范围内继续查找
            end = mid - 1;
        } else {   //否则arr[mid]>arr[mid + 1],则数组在[mid,end]范围内先下降后上升,该范围内一定存在局部最小值,在[mid+1,end]范围内继续查找。
            start = mid + 1;
        }
    }
    
    //上述过程为找到局部最小值,说明start>=end-1,则[start,end]区间内最多只有2个元素,单独判断这两个元素即可
    return arr[start] <= arr[end] ? start : end;
}
```

**二分思想也可以作用于非有序的场景，只要每次查找可以缩小一半的范围，都可以使用二分。**
