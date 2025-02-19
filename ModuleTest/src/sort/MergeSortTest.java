package sort;

import org.junit.Test;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/18 16:12
 * @content 归并排序
 * 实现方式1：二分递归
 * 2.步长2倍递增
 * merge核心：两边分别两个下标递增选出当前最小值并进行排序
 */

public class MergeSortTest {

    @Test
    public void testMergeSort(){
        int[] arr = {2,5,8,3,5,7,23,11,17};
        printArray(arr);
//        mergeSort(arr, 0, arr.length-1);
        mergeSort2(arr);
        printArray(arr);

    }

    private void printArray(int[] arr){
        if(arr == null || arr.length < 1){
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }


    /**
     * （非递归方式） 归并排序
     * 一小块一小快进行merge
     * 时间复杂度O(N*logN)
     * @param arr
     */
    private void mergeSort2(int[] arr){
        if(arr == null || arr.length<2){
            return;
        }
        //****定义步长****  2倍递增
        int step = 1;
        int N = arr.length;
        while(step < N){
            int L = 0;
            while(L < N){
                //考虑左组不够的情况
                //溢出判定
                //(N - 1) - L + 1 为个数
                if(N - L <= step){
                    break;
                }
                int  M = L + step -1;
                //溢出判定
                //(N-1) - (M + 1) + 1 为个数
                int R = N - 1 - M >= step? M + step : N - 1;
                //L....M, M+1......R
                merge(arr, L, M, R);
                //下一个左组
                //R边界判定
                if(R == N - 1){
                    break;
                } else {
                    L = R + 1;
                }
            }
            //考虑溢出问题 2N31次方
            //step = step << 1 , 注意/2向下取整
            if(step > (N / 2)){
                break;
            }
            step *= 2;
        }

    }

    /**
     * （递归方式） 进行 归并排序
     * 二分进行merge
     * @param arr
     * @param L
     * @param R
     */
    private void mergeSort(int[] arr, int L, int R) {
        //临界条件
        if(L == R){
            return;
        }
//        int mid = (L + R)/2;
        int mid = L + ((R - L) >> 1);//数组下标越界问题规避
        //左右两边分别进行排序，后进行merge，递归
        mergeSort(arr, L, mid);
        mergeSort(arr, mid + 1, R);
        merge(arr, L , mid, R);
    }

    /**
     * 数组两边有序合并
     * 两个下标分别在左右两边进行选小or选大
     * @param arr
     * @param L
     * @param M
     * @param R
     */
    private void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int x = L;
        int y = M + 1;
        //两个下标均在范围内
        while(x <= M && y <= R){
            //选出最小，下标右移
            help[i++] = arr[x] <= arr[y] ? arr[x++] : arr[y++];
        }
        //右边遍历完左边有剩余
        while(x <= M){
            help[i++] = arr[x++];
        }
        //左边遍历完右边有剩余
        while(y <= R){
            help[i++] = arr[y++];
        }
        //help拷贝
        for (i = 0;  i < help.length; i++) {
            arr[L + i] = help[i];
        }
    }

}
