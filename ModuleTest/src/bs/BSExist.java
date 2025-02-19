package bs;

import org.junit.Test;

import java.util.Arrays;

/**
 * 二分查找
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/7 19:45
 */
public class BSExist {

    public static int[] arr = {1, 2, 3, 3, 4, 5, 5};

    @Test
    public void testBSFind(){
        boolean b = find(arr, 5);
        System.out.println(b);
        boolean b1 = find(arr, 0);
        System.out.println(b1);
    }

    @Test
    public void testFindMostLeft(){
        int mostLeft = findMostLeftIndex(arr, 3);
        System.out.println(mostLeft);
    }

    @Test
    public void testOneMin(){
        int maxLen = 100;
        int maxValue = 20;
        int testTime = 100;
        System.out.println("测试开始");
        for (int i = 0; i < testTime; i++) {
            int[] arr = randomArray(maxLen, maxValue);
            int ans = getOneMinIndex(arr);
            boolean checkResult = check(arr, ans);
            if(!checkResult){
                printArray(arr);
                System.out.println(ans);
                break;
            }
        }
        System.out.println("测试结束");

    }

    /**
     * 验证局部最小
     * @param arr
     * @param minIndex
     * @return
     */
    private boolean check(int[] arr, int minIndex){
        if(arr == null || arr.length == 0){
            return false;
        }
        int left = minIndex - 1;
        int right = minIndex + 1;
        //越界情况符合
        boolean leftBigger = left >= 0 ? arr[left] > arr[minIndex] : true;
        boolean rightBigger = right < arr.length ? arr[right] > arr[minIndex] : true;
        return leftBigger && rightBigger;
    }

    private void printArray(int[] arr){
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 生成一个随机且相邻不等的数组
     * @param maxLen
     * @param maxValue
     * @return
     */
    private int[] randomArray(int maxLen, int maxValue){
        int len = (int)(Math.random() * maxLen) + 1;
        int[] arr = new int[len];
        if(len > 0){
            arr[0] = (int)(Math.random() * maxValue);
            for (int i = 1; i < arr.length; i++) {
                do{
                    arr[i] = (int)(Math.random() * maxValue);
                } while (arr[i] == arr[i - 1]);
            }
        }
        return arr;
    }

    /**
     * arr 整体无序， 且相邻的数不想等
     * 返回局部最小值
     * @param arr
     * @return
     */
    private int getOneMinIndex(int[] arr){
        if(arr == null || arr.length == 0){
            return -1;
        }
        int n = arr.length;
        if(n == 1){
            return 0;
        }
//        if(n == 2){
//            return arr[0] < arr[1]? 0 : 1;
//        }
        //两个端上的情况，包含n=2的情况
        if(arr[0] < arr[1]){
            return 0;
        }
        if(arr[n-1] < arr[n-2]){
            return n-1;
        }
        //开始二分
        int l = 0;
        int r = n - 1;
        while(l < r -1){
            int mid = (l + r) / 2;
            //直接找到局部最小
            if(arr[mid] < arr[mid - 1] && arr[mid] < arr[mid + 1]){
                return mid;
            }else {
                //不同时小，三种情况：
                //1.左>我>右
                //2.左<我<右
                //3.左<我 右<我
                //mid大于左边，右边砍掉去左边继续找
                if(arr[mid] > arr[mid - 1]){
                    r = mid - 1;
                }else {
                    //mid大于右边，左边砍掉去右边找
                    l = mid + 1;
                }
            }
        }

        return arr[l] < arr[r] ? l : r;
    }

    /**
     * 有序数组中找到num
     * 采用二分法
     */
    private boolean find(int[] arr, int num){
        if(null == arr || arr.length == 0){
            return false;
        }
        int l = 0;
        int r = arr.length - 1;
        //二分结束为左右结点相交
        while(l <= r){
            int mid = (l + r) / 2;
            if(num == arr[mid]){
                return true;
            }else if(num > arr[mid]){
                //在右边
                l = mid + 1;
            }else {
                //在左边
                r = mid - 1;
            }
        }
        return false;
    }

    /**
     * 有序数组数组中找到>=num最左侧的位置
     * 继续使用二分
     */
    private int findMostLeftIndex(int[] arr, int num){
        if(null == arr || arr.length == 0){
            return -1;
        }
        int l = 0;
        int r = arr.length - 1;
        int mostLeftIndex = -1;
        while(l <= r){
            //二分
            int mid = (l + r) / 2;
            if(arr[mid] >= num){
                //找到num，但可能存在重复，需要继续往左走，继续二分，直到所有值二分结束，留下的index标签即为结果
                mostLeftIndex = mid;
                r = mid -1;
            }else {
                l = mid + 1;
            }
        }
        return mostLeftIndex;
    }

    private int findMostLeftIndex1(int[] arr, int num){
        if(arr == null || arr.length < 0){
            return -1;
        }
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] >= num){
                return i;
            }
        }
        return -1;
    }


    /**
     * 对数器
     */
    @Test
    public void checkAns(){
        int maxSize = 50;
        int maxValue = 100;
        int testTime = 1000;
        int num = 30;
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int index1 = findMostLeftIndex(arr, num);
            int index2 = findMostLeftIndex1(arr, num);
            if(index1 != index2){
                System.out.print("arr: [");
                for (int j = 0; j < arr.length; j++) {
                    System.out.print(arr[j] + ",");
                }
                System.out.println("]");
                System.out.println("findMostLeftIndex: " + index1 + "  findMostLeftIndex1: " + index2);
            }
        }
        System.out.println("check right");
    }

    /**
     * 返回一个数组arr，arr长度[1, maxSize], arr中每个值[0, maxValue-1]
     * @param maxSize
     * @param maxValue
     * @return
     */
    private int[] generateRandomArray(int maxSize, int maxValue){
        //数组大小随机 [0, 1)
        int[] arr =  new int[(int)(Math.random() * (maxSize + 1))];
        for (int i = 0; i < arr.length; i++) {
            //数值随机
            arr[i] = (int)(Math.random() * (maxValue + 1));
        }
        //排序
        Arrays.sort(arr);
        return arr;
    }

}
