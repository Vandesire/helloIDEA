package binary;

import org.junit.Test;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/6 23:48
 */
public class BinaryTest {

    /**
     * 位运算测试
     * 输出10进制数的2进制转化输出
     */
    @Test
    public void binaryTest(){
        int a = 1024;
//        print(a);
//        print(~a);
//        print(~a + 1);

        // >> 带符号右移，左侧补符号位
        // >>> 不带符号右移动，左侧补0
//        print(a >> 1);
//        print(a >>> 1);
//
//        System.out.println("================");
//
//        a = Integer.MIN_VALUE;
//        print(a);
//        print(a >> 1);
//        print(a >>> 1);

        a = -3;
        print(a);
        print(-a);
        print(~a);
        print(~a + 1);


    }

    private static void print(int num) {
        for(int i=31; i>=0; i--){
            System.out.print((num & (1<<i)) == 0? 0 : 1);
        }
        System.out.println();
    }

    //取反码
    public static int getOnesReverse(int num) {
        if (num >= 0) {
            // 正数的反码和原码相同
            return num;
        } else {
            // 负数的反码是原码符号位不变，其余位取反
            //**先获取绝对值的二进制表示**
            int absNum = Math.abs(num);
            // 得到绝对值的反码
            int absComplement = ~absNum;
            // 保留符号位（最高位为 1）
            int signBit = 1 << 31;
            return signBit | absComplement;
        }
    }

    public static void main(String[] args) {
        int positiveNum = 5;
        int negativeNum = -5;

        int positiveReverse = getOnesReverse(positiveNum);
        int negativeReverse = getOnesReverse(negativeNum);

        int posCom = positiveNum;
        int negCom = negativeReverse + 1;

        System.out.println("正数 " + positiveNum + " 的反码（十进制）: " + positiveReverse + " 补码（十进制）：" + posCom);
        System.out.println("负数 " + negativeNum + " 的反码（十进制）: " + negativeReverse + " 补码（十进制）：" + negCom);

        // 打印二进制表示
        System.out.println("正数 " + positiveNum + " 的反码（二进制）: " + Integer.toBinaryString(positiveReverse) + " 补码（二进制）：" + Integer.toBinaryString(posCom
        ));
        System.out.println("负数 " + negativeNum + " 的反码（二进制）: " + Integer.toBinaryString(negativeReverse) + " 补码（二进制）：" + Integer.toBinaryString(negCom));
    }

}
