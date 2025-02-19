package sort;

import org.junit.Test;

import java.util.Stack;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/18 23:58
 * @content 快速排序测试
 * 实现方式：选定数组最右端为参考值，数组往右进行遍历，<参考值放左边，>放右边，=不动，参考值移动一位继续，直至结束，返回=区边界，继续左右递归
 */
public class QuickSortTest {

    @Test
    public void testQuickSort(){
        int[] arr = new int[]{7,8,4,9,3,0,1,5,5};
        printArray(arr);
//        quickSort(arr);
        quickSort2(arr);
        printArray(arr);
    }

    /**
     * 非递归方式实现
     */
    private void quickSort2(int[] arr){
        Stack<Job> jobs = new Stack<>();
        jobs.push(new Job(0, arr.length - 1));
        while(!jobs.isEmpty()){
            Job job = jobs.pop();
            int[] equal = partition(arr, job.l, job.r);
            //边界判定有<区
            if(equal[0] > job.l){
                jobs.push(new Job(job.l, equal[0] -1));
            }
            //边界判定有>区
            if(equal[1] < job.r){
                jobs.push(new Job(equal[1] + 1, job.r));
            }
        }
    }

    private void quickSort(int[] arr) {
        if(arr == null || arr.length < 2){
            return;
        }
        process(arr, 0, arr.length - 1);
    }

    private class Job{
        public int l;
        public int r;
        public Job(int left, int right){
            l = left;
            r = right;
        }
    }

    /**
     * （递归实现）
     * @param arr
     * @param l
     * @param r
     */
    private void process(int[] arr, int l, int r){
        if(l >= r){
            return;
        }
        int[] equal = partition(arr, l, r);
        process(arr, l, equal[0] - 1);
        process(arr, equal[1] + 1, r);
    }


    /**
     * 以最右侧为参考值，划分三片区域 < =  >
     * @param arr
     * @param l
     * @param r
     * @return =区左右边界下标
     */
    private int[] partition(int[] arr, int l, int r){
        //<区边界
        int lessEqual = l - 1;
        //>区边界
        int moreEqual = r;
        int index = l;
        while (index < moreEqual){
            if(arr[index] < arr[r]){
                swap(arr, ++lessEqual, index++);
            }else if(arr[index] > arr[r]){
                swap(arr, --moreEqual, index);
            }else{
                index ++;
            }
        }
        //index = moreL时没有进行交换，此时最后进行交换
        swap(arr, moreEqual, r);
        //moreEqual上一步有交换，此时本来-1，这里+1抵消
        return new int[]{lessEqual + 1, moreEqual};
    }

    private void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private void swap(int[] arr, int l, int r){
        int temp = arr[l];
        arr[l] = arr[r];
        arr[r] = temp;
    }

}
