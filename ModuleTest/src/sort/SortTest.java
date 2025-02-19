package sort;

import org.junit.Test;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/6 23:49
 */
public class SortTest {

    private static int[] testArray = {5, 3, 1, 4, 2};

    @Test
    public void selectionSortTest(){
        printArray(testArray);
        selectionSort(testArray);
        printArray(testArray);
    }

    @Test
    public void bubbleSortTest(){
        printArray(testArray);
        bubbleSort(testArray);
        printArray(testArray);
    }

    @Test
    public void insertSortTest(){
        printArray(testArray);
        insertSort(testArray);
        printArray(testArray);
    }
    @Test
    public void insertSort2Test(){
        printArray(testArray);
        insertSort2(testArray);
        printArray(testArray);
    }

    /**
     * 插入排序
     * 0 -> 0 完成
     * 0 -> 1 范围有序
     * 0 -> 2 有序
     * 0 -> 3 有序
     * 循环一次范围+1，新加入数字与已排序好的部分逐个比较
     * 0 -> n-1 有序
     * @param arr
     */
    public static void insertSort(int[] arr) {
        if(null == arr || arr.length < 2){
            return;
        }
        //控制范围n
        int n = arr.length;
        for (int end = 1; end < n; end++) {
            int newNumIndex = end;
            //最左边有数 且 保证左边>右边
            while(newNumIndex - 1 >= 0 && arr[newNumIndex - 1] > arr[newNumIndex]){
                swap(arr ,newNumIndex - 1, newNumIndex);
                //继续往左看
                newNumIndex--;
            }
        }
    }

    private void insertSort2(int[] arr) {
        if(null == arr || arr.length < 2){
            return;
        }
        //控制范围n
        int n = arr.length;
        for (int end = 1; end < n; end++) {
            //最左边有数 且 保证左边>右边
           for(int pre = end -1; pre >= 0 && arr[pre] > arr[pre+1]; pre --){
               swap(arr, pre, pre + 1);
           }
        }
    }

    /**
     * 冒泡排序
     * 0 -> n-1
     * 0 -> n-2
     * 0 -> n-3
     * 0 -> end
     * 每循环一轮选出最大或最小值，通过相邻两位连续比较交换
     * @param arr
     */
    private void bubbleSort(int[] arr) {
        if(null == arr || arr.length < 2){
            return;
        }
        //范围控制
        int n = arr.length;
        for (int end = n-1; end >= 0; end--) {
            //0 -> end 之间
            for(int second = 1; second <= end; second++){
                if(arr[second - 1] > arr[second]){
                    swap(arr, second-1, second);
                }
            }
        }

    }

    private void printArray(int[] array){
        for (int i : array) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    /**
     * 数组位置交换
     * @param arr
     * @param i
     * @param j
     */
    public static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 选择排序
     * 0 -> n-1
     * 1 -> n-1
     * 2 -> n-1
     * 每一轮选出最大值或者最小值放在最前端，找到最小值的下标然后直接交换至最前端
     * @param array
     */
    private void selectionSort(int[] array) {
        if(null == array || array.length < 2){
            return;
        }
        //范围
        int n = array.length;
        for (int i = 0; i < n; i++) {
            //找到最小值下标
            int minValueIndex = i;
            for (int j = i+1; j < n; j++) {
                minValueIndex = array[j] < array[minValueIndex]? j : minValueIndex;
            }
            //最小值放到端上
            swap(array, i , minValueIndex);
        }
    }


}
