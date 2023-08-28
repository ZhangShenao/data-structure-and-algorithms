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



# 5. 链表

## 算法：单链表反转

**算法思想：从头开始遍历链表，针对每个节点，修改其 next 指针的指向。注意：每次遍历到一个节点时，需要先记录 next 节点，否则当修改指针后， next 节点就丢失了。**

算法实现：

```Java
/**
 * 反转单链表,返回反转后的链表头结点
 */
private static SingleListNode<Integer> reverseSingleList(SingleListNode<Integer> head) {
    //边界条件校验
    if (head == null || head.next == null) {
        return head;
    }
    
    SingleListNode<Integer> prev = null;
    SingleListNode<Integer> next = null;
    SingleListNode<Integer> cur = head;
    
    while (cur != null) {
        next = cur.next;    //首先记录next节点
        cur.next = prev;
        prev = cur;
        cur = next;
    }
    
    return prev;
}
```

## 算法：使用单链表实现队列

**算法思想：分别使用头指针 head、尾指针 tail ，记录头、尾节点位置。当元素入队时，从尾节点出入队，并修改 tail 指针指向；当元素出队时，从头结点出队，并修改 head 指针指向。**

算法实现：

```Java
/**
 * @author ZhangShenao
 * @date 2023/8/22 9:33 AM
 * @description: 使用单链表实现队列
 */
public class SingleListQueue<T> {
    
    /**
     * 头指针,指向队头
     */
    private SingleListNode<T> head;
    
    /**
     * 尾指针,指向队尾
     */
    private SingleListNode<T> tail;
    
    /**
     * 记录当前队列中元素数量
     */
    private int size;
    
    /**
     * 判断队列是否为空
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * 获取当前队列中元素数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 元素入队
     */
    public void offer(T ele) {
        SingleListNode<T> cur = new SingleListNode<>(ele, null);
        if (tail == null) { //如果当前队列为空,则初始化队列
            head = tail = cur;
        } else { //队列不为空,在尾部入队
            tail.next = cur;
            tail = cur;
        }
        ++size;
    }
    
    /**
     * 元素出队
     */
    public T poll() {
        //处理空队列
        if (head == null) {
            return null;
        }
        
        //队列不为空,从队头出队
        SingleListNode<T> oldHead = head;
        head = head.next;
        oldHead.next = null;
        if (head == null) {  //队列已为空,则修改tail指针
            tail = null;
        }
        
        --size;
        return oldHead.value;
    }
    
    /**
     * 查看队头元素,并不出队
     */
    public T peek() {
        if (head == null) {
            return null;
        }
        return head.value;
    }
}
```

## 算法：使用单链表实现栈

**算法思想：使用 head 指针指向队头，每次压栈、弹栈操作，都在队头进行，并修改 head 指针。**

算法实现：

```Java
public class SingleListStack<T> {
    
    /**
     * 头指针,指向栈顶
     */
    private SingleListNode<T> head;
    
    /**
     * 记录当前栈中元素数量
     */
    private int size;
    
    /**
     * 判断栈是否为空
     */
    public boolean isEmpty() {
        return (size == 0);
    }
    
    /**
     * 获取当前栈中元素数量
     */
    public int size() {
        return size;
    }
    
    /**
     * 将元素压栈
     */
    public void push(T ele) {
        head = new SingleListNode<>(ele, head);
        ++size;
    }
    
    /**
     * 弹出栈顶元素
     */
    public T pop() {
        if (head == null) {
            return null;
        }
        SingleListNode<T> oldHead = head;
        head = oldHead.next;
        oldHead.next = null;
        --size;
        return oldHead.value;
    }
    
    /**
     * 获取栈顶元素,并不弹栈
     */
    public T top() {
        if (head == null) {
            return null;
        }
        return head.value;
    }
}
```

## 算法：K 个一组翻转链表

Leetcode 地址：https://leetcode.cn/problems/reverse-nodes-in-k-group/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

**算法思想：从头开始遍历链表，每次找到 K 个一组的节点范围 [start,end]，在组内进行节点指针的翻转。**

算法实现：

```Java
/**
 * K个一组翻转链表
 */
public ListNode reverseKGroup(ListNode head, int k) {
    //边界条件校验
    if (head == null || k <= 0) {
        return head;
    }
    
    //先找到第一个分组
    ListNode start = head;
    ListNode end = findKGroupEnd(start, k);
    if (end == null) {
        //节点数量不足k个,无需翻转,直接返回原始头结点
        return head;
    }
    
    //新的头结点一定是end
    head = end;
    
    //先反转第一组
    reverse(start, end);
    
    //继续遍历链表后续节点,依次处理后面的组
    ListNode lastGroupEnd = start;
    while (lastGroupEnd.next != null) {
        start = lastGroupEnd.next;
        end = findKGroupEnd(start, k);
        if (end == null) {
            return head;
        }
        reverse(start, end);
        
        //将上一组的尾节点指向当前组的头结点
        lastGroupEnd.next = end;
        
        //继续处理下一组
        lastGroupEnd = start;
    }
    
    return head;
}

/**
 * 找到组内的尾节点end,形成[start,end]区间内的K个节点组
 */
private ListNode findKGroupEnd(ListNode start, int k) {
    while (--k > 0 && start != null) {
        start = start.next;
    }
    return start;
}

/**
 * 在[start,end]节点区间内翻转链表
 */
private void reverse(ListNode start, ListNode end) {
    //首先记录下一组的头结点
    end = end.next;
    
    ListNode cur = start;
    ListNode prev = null;
    ListNode next = null;
    
    while (cur != end) {
        next = cur.next;
        cur.next = prev;
        prev = cur;
        cur = next;
    }
    
    //将当前组的尾节点指向下一组的头结点
    start.next = end;
}
```

## 算法：两数相加

LeetCode 地址：https://leetcode.cn/problems/add-two-numbers/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

**算法思想：按从低位到高位的顺序，同时遍历两个链表，计算相加的结果，并保存进位。最终遍历到两个链表都为空时，需要根据是否有进位，判断是否需要新增进位节点。**

算法实现：

```Java
public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    //边界条件校验
    if (l1 == null) {
        return l2;
    }
    if (l2 == null) {
        return l1;
    }
    
    int carry = 0;  //暂存当前进位
    ListNode dummy = new ListNode();    //新链表的哑头结点
    ListNode cur = dummy;
    
    //按从低位到高位的顺序,同时遍历两个链表,计算相加的结果,并保存进位
    while (l1 != null && l2 != null) {
        int sum = l1.val + l2.val + carry;
        int curVal = sum % 10;
        carry = sum / 10;
        cur.next = new ListNode(curVal);
        l1 = l1.next;
        l2 = l2.next;
        cur = cur.next;
    }
    
    //处理其中一个链表已经遍历结束的情况
    while (l1 != null) {
        int sum = l1.val + carry;
        int curVal = sum % 10;
        carry = sum / 10;
        cur.next = new ListNode(curVal);
        l1 = l1.next;
        cur = cur.next;
    }
    while (l2 != null) {
        int sum = l2.val + carry;
        int curVal = sum % 10;
        carry = sum / 10;
        cur.next = new ListNode(curVal);
        l2 = l2.next;
        cur = cur.next;
    }
    
    //两个链表都遍历结束,考虑进位
    if (carry != 0) {
        cur.next = new ListNode(carry);
    }
    
    return dummy.next;
}
```

## 算法：合并两个有序链表

LeetCode 地址：https://leetcode.cn/problems/merge-two-sorted-lists/

**算法思想：从头开始，同时遍历两个链表，选择数值较小的节点，添加到新链表末尾，并将该节点向后移动。如果其中一条链表遍历完成，则直接添加另一条链表。**

算法实现：

```Java
public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    //边界条件校验
    if (list1 == null) {
        return list2;
    }
    if (list2 == null) {
        return list1;
    }
    
    ListNode dummy = new ListNode();    //新链表的哑头结点
    ListNode cur = dummy;
    
    //从头开始,同时遍历两个链表,选择数值较小的节点,添加到新链表末尾,并将该节点向后移动
    while (list1 != null && list2 != null) {
        if (list1.val <= list2.val) {
            cur.next = new ListNode(list1.val);
            list1 = list1.next;
        } else {
            cur.next = new ListNode(list2.val);
            list2 = list2.next;
        }
        
        cur = cur.next;
    }
    
    //处理其中一条链表已经遍历完的情况
    if (list1 == null) {
        cur.next = list2;
    } else {
        cur.next = list1;
    }
    return dummy.next;
}
```

## 算法：合并 K 个升序链表

**算法思想：借助一个优先级队列（小顶堆）。首先将所有列表的头结点都放入优先级队列中，然后每次从优先级队列中取出一个最小的节点，连接到新链表的末尾。最后将取出的最小节点的后继节点再放入优先级队列中，继续上述过程，直到队列为空。**

算法实现：

```Java
public ListNode mergeKLists(ListNode[] lists) {
    //边界条件校验
    if (lists == null || lists.length == 0) {
        return null;
    }
    
    //借助一个优先级队列(小顶堆)
    PriorityQueue<ListNode> minHeap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
    
    //首先将所有链表的头结点都放入堆中
    for (int i = 0; i < lists.length; i++) {
        ListNode n = lists[i];
        if (n != null) {
            minHeap.offer(n);
        }
    }
    
    if (minHeap.isEmpty()) {
        return null;
    }
    
    //先从堆中取出最小节点,作为新链表的头结点
    ListNode head = minHeap.poll();
    if (head.next != null) {
        minHeap.offer(head.next);
    }
    
    //依次从堆中取出最小节点,最为新链表的下一个节点,并将最小节点的后继节点放入堆中
    ListNode cur = head;
    while (!minHeap.isEmpty()) {
        ListNode min = minHeap.poll();
        cur.next = min;
        if (min.next != null) {
            minHeap.offer(min.next);
        }
        cur = cur.next;
    }
    
    return head;
}
```

# 6. 二叉树

**二叉树相关的算法，经常采用递归思想来解决问题。**

## 算法：相同的树

LeetCode 地址：https://leetcode.cn/problems/same-tree/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

**算法思想：按照递归分别先序遍历两棵二叉树。如果当前的根节点相同，且左、右子树也分别相同，则说明两棵二叉树相同。**

算法实现：

```Java
public boolean isSameTree(TreeNode p, TreeNode q) {
    //边界条件校验
    if ((p == null) ^ (q == null)) { //两棵树结构不同
        return false;
    }
    
    if (p == null && q == null) {
        return true;
    }
    
    //递归进行
    //两棵树相等的条件:当前根节点的值相同,且左、右子树也相同
    return (p.val == q.val) && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```

## 算法：对称二叉树

LeetCode 地址：https://leetcode.cn/problems/symmetric-tree/

**算法思想：采用递归思想，给定一个根节点 root，首先判断其 left、right 子节点是否相同，然后再判断 left.left = right.right，且 left.right = right.left。**

算法实现：

```Java
public boolean isSymmetric(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return true;
    }
    
    //采用递归算法,依次判断root的左、右子树是否对称
    return isSymmetric(root.left, root.right);
}

private boolean isSymmetric(TreeNode left, TreeNode right) {
    if ((left == null) ^ (right == null)) {  //两棵树结构不同
        return false;
    }
    
    if (left == null && right == null) {
        return true;
    }
    
    //两棵子树对称的条件:两个节点值相同,且left和right的子树对称
    return (left.val == right.val) && (isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left));
}
```

## 算法：二叉树的最大深度

LeetCode 地址：https://leetcode.cn/problems/maximum-depth-of-binary-tree/

**算法思想：以 root 为根的二叉树的深度 = 左、右子树的最大深度的最大值 + 1。**

算法实现：

```Java
public int maxDepth(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return 0;
    }
    
    //采用递归算法
    //以root为根的二叉树的深度=左、右子树二叉树深度的最大值+1
    return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```

## 算法：从前序与中序遍历序列构造二叉树

LeetCode 地址：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

**算法思想：**

- **采用递归思想**
- **每次在前序数组区间 preOrder[preStart,preEnd] 和中序数组区间 inOrder[inStart,inEnd] 内，找到当前根节点，并找到左子树范围和右子树范围**
- **将当前根节点的 left 和 right 分别指向左、右子树的根节点，并递归执行上述过程。**

算法实现：

```Java
public TreeNode buildTree(int[] preorder, int[] inorder) {
    //边界条件校验
    if (preorder == null || inorder == null || preorder.length == 0 || inorder.length == 0
            || preorder.length != inorder.length) {
        return null;
    }
    
    //使用一个Hash表,维护中序数组中的节点值索引,便于查找节点
    HashMap<Integer, Integer> inOrderValIdx = new HashMap<>(inorder.length);
    for (int i = 0; i < inorder.length; i++) {
        inOrderValIdx.put(inorder[i], i);
    }
    
    return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, inOrderValIdx);
    
}

/**
 * 递归函数 根据前序遍历结果preorder[preStart,preEnd]和中序遍历结果inorder[inStart,inEnd],构造二叉树,并返回根节点
 */
private TreeNode build(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd,
        HashMap<Integer, Integer> inOrderValIdx) {
    //递归终止条件
    if (preStart > preEnd) { //空树
        return null;
    }
    
    int rootVal = preorder[preStart];
    TreeNode root = new TreeNode(rootVal);
    
    //区间内只要一个节点,直接返回根节点
    if (preStart == preEnd) {
        return root;
    }
    
    //在中序数组中,找到根节点所在的索引
    int rootIdx = inOrderValIdx.get(rootVal);
    
    //中序数组中左子树的范围:[inStart,rootIdx-1]
    //前序数组中左子树的范围:[preStart+1,preStart+1 + rootIdx-1-inStart]=[preStart+1,preStart+rootIdx-inStart]
    root.left = build(preorder, preStart + 1, preStart + rootIdx - inStart, inorder, inStart, rootIdx - 1,
            inOrderValIdx);
    
    //中序数组中右子树的范围:[rootIdx+1,inEnd]
    //前序数组中右子树的范围:[preStart+rootIdx-inStart+1,preEnd]
    root.right = build(preorder, preStart + rootIdx - inStart + 1, preEnd, inorder, rootIdx + 1, inEnd,
            inOrderValIdx);
    
    //返回根节点
    return root;
}
```

## 算法：二叉树的层序遍历 II

LeetCode 地址：https://leetcode.cn/problems/binary-tree-level-order-traversal-ii/?utm_source=LCUS&utm_medium=ip_redirect&utm_campaign=transfer2china

**算法思想：借助一个队列，保存每层的节点。遍历每层节点时，首先获取当前层节点的数量，并且取出当前层所有节点。**

算法实现：

```Java
public List<List<Integer>> levelOrderBottom(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return Collections.emptyList();
    }
    
    //借助一个队列,按层保存节点
    Queue<TreeNode> queue = new LinkedList<>();
    
    //使用链表保存最终结果
    LinkedList<List<Integer>> result = new LinkedList<>();
    
    //首先将根节点入队
    queue.offer(root);
    
    //遍历队列
    while (!queue.isEmpty()) {
        //首先记录当前层的节点数量
        int size = queue.size();
        List<Integer> curLevel = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            //依次将当前层的节点出队,并按顺序将其左、右子节点依次入队
            TreeNode node = queue.poll();
            curLevel.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        //保存当前层结果,插入结果链表头部
        result.addFirst(curLevel);
    }
    
    //返回结果
    return result;
}
```

## 算法：二叉树的层序遍历

LeetCode 地址：https://leetcode.cn/problems/binary-tree-level-order-traversal/

算法实现：

```Java
public List<List<Integer>> levelOrder(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return Collections.emptyList();
    }
    
    //借助一个队列,依次保存每层的节点
    Queue<TreeNode> queue = new LinkedList<>();
    List<List<Integer>> result = new ArrayList<>();
    
    //首先将根节点入队
    queue.offer(root);
    
    //遍历队列
    while (!queue.isEmpty()) {
        //首先记录当前层节点的数量,并取出指定数量的节点
        int curLevelSize = queue.size();
        List<Integer> curLevel = new ArrayList<>(curLevelSize);
        
        for (int i = 0; i < curLevelSize; i++) {
            TreeNode node = queue.poll();
            curLevel.add(node.val);
            
            //依次将节点的左、右子节点入队
            if (node.left != null) {
                queue.offer(node.left);
            }
            
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        
        result.add(curLevel);
    }
    
    return result;
}
```

## 算法：验证平衡二叉树

LeetCode 地址：https://leetcode.cn/problems/balanced-binary-tree/

**算法思想：利用递归思想。根据平衡二叉树的定义，需要满足左、右子树均为平衡二叉树，且左、右子树的高度差不超过1。递归遍历二叉树，记录以当前节点为根的二叉树的高度和是否为平衡二叉树。**

算法实现：

```Java
/**
 * 二叉树节点信息
 */
private static class TreeNodeInfo {
    
    private boolean balanced;   //以当前节点为根的二叉树是否为平衡二叉树
    
    private int height; //以当前节点为根的二叉树的高度
    
    public TreeNodeInfo(boolean balanced, int height) {
        this.balanced = balanced;
        this.height = height;
    }
}

public boolean isBalanced(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return true;
    }
    
    //递归
    return traversal(root).balanced;
}

/**
 * 递归遍历二叉树,并保存每个节点的信息
 */
private TreeNodeInfo traversal(TreeNode root) {
    //递归终止条件——空树满足平衡树
    if (root == null) {
        return new TreeNodeInfo(true, 0);
    }
    
    //依次遍历左、右子树
    TreeNodeInfo left = traversal(root.left);
    TreeNodeInfo right = traversal(root.right);
    
    //当前数的高度=左、右子树的最大高度+1
    int height = Math.max(left.height, right.height) + 1;
    
    //当前树满足平衡二叉树的条件:左、右子树均为平衡二叉树 && 左、右子树高度相差不超过1
    boolean balanced = left.balanced && right.balanced && Math.abs(left.height - right.height) <= 1;
    
    return new TreeNodeInfo(balanced, height);
}
```

## 算法：验证二叉搜索树

LeetCode 地址：https://leetcode.cn/problems/validate-binary-search-tree/

**算法思想1：对二叉树进行中序遍历，判断得到的结果是否为有序数组。**

**算法思想2：采用递归方式，遍历二叉树，并记录每个节点的信息，包括以当前节点为根的二叉树是否为二叉搜索树、当前二叉树的最小值、当前二叉树的最大值。**

**根据二叉搜索树的定义，判断是否满足条件：**

- **当前根节点的左、右子树均为二叉搜索树。**
- **左子树的最大值 < 根节点的值。**
- **右子树的最小值 > 根节点的值。**

算法实现：

```Java
/**
 * 记录节点信息
 */
private static class TreeNodeInfo {
    
    private boolean bst;    //以当前节点为根的二叉树是否为二叉搜索树
    
    private int max;     //以当前节点为根的二叉树的最大值
    
    private int min;     //以当前节点为根的二叉树的最小值
    
    public TreeNodeInfo(boolean bst, int max, int min) {
        this.bst = bst;
        this.max = max;
        this.min = min;
    }
}

public boolean isValidBST(TreeNode root) {
    //边界条件校验
    if (root == null) {
        return true;
    }
    
    //递归
    return recursive(root).bst;
}

/**
 * 递归遍历二叉树,并记录每个节点的信息
 */
private TreeNodeInfo recursive(TreeNode root) {
    //递归终止条件
    if (root == null) {
        return null;
    }
    
    //依次遍历左、右子树
    TreeNodeInfo left = recursive(root.left);
    TreeNodeInfo right = recursive(root.right);
    
    //更新当前树的最大、最小值
    int max = root.val;
    int min = root.val;
    if (left != null) {
        max = Math.max(max, left.max);
        min = Math.min(min, left.min);
    }
    if (right != null) {
        max = Math.max(max, right.max);
        min = Math.min(min, right.min);
    }
    
    //当前树满足二叉搜索树的条件:
    //1.左、右子树均为二叉搜索树
    //2.左子树的最大值 < 当前节点
    //3.右子树的最小值 > 当前节点
    boolean leftIsBst = (left == null || left.bst);
    boolean rightIsBst = (right == null || right.bst);
    boolean leftMaxLessThanRoot = (left == null || left.max < root.val);
    boolean rightMinMoreThanRoot = (right == null || right.min > root.val);
    boolean bst = (leftIsBst && rightIsBst && leftMaxLessThanRoot && rightMinMoreThanRoot);
    
    //返回当前节点信息
    return new TreeNodeInfo(bst, max, min);
}
```
