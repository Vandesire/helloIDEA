import java.util.*;

/**
 * @Topic 腾讯面试题
 * @author Nameisempty
 * 字符串简易压缩
 */
public class ZipTest {

    public static void main(String[] args) {

        String in = "aaabbccdddccccee";

        //顺序非重复字符list
        List<String> charList = new ArrayList<>();
        //list统计重叠出现次数,顺序同步
        List<Integer> countList = new ArrayList<>();

        //当前字符,初始化
        String current = "";
        //重叠次数统计
        int count = 0;
        for (int i = 0; i < in.length(); i++) {
            //顺序截取字符
            String s = in.substring(i, i + 1);
            //首字符特殊处理(首字符录入，count后面统计)
            if(i == 0){
                current = s;
                charList.add(current);
                break;
            }

            //当前字符与下一字符不一致，切换,count归0
            if(!current.equals(s)){
                charList.add(s);
                countList.add(count);
                current = s;
                count = 0;
            }
            count ++;

            //末字符特殊处理（字符已录入，count未add）
            if(i == in.length()-1){
                countList.add(count);
            }
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < charList.size(); i++) {
            String s = charList.get(i);
            Integer integer = countList.get(i);
            result.append(s);
            if(integer > 1){
                result.append(integer);
            }
        }
        System.out.println(result);
    }


}
