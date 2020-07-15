package cn.com.eju.deal.common.util;

/**
 * User: 谢旭
 * Date: 13-12-9
 * Excel生成的时候使用的工具
 */
public class ExcelUtil {
    public static String getColumnChar(int nowColIndex) {
        int decimalNum = nowColIndex;
        String result = "";
        char c;
        while(decimalNum > 0){
            decimalNum -= 1;
            c = (char) (decimalNum % 26 + 'A');
            decimalNum /= 26;
            result = c + result;
        }
        return result;
    }

    /*public static void main(String[] args){
        long beginTime = System.currentTimeMillis();
        for(int i=0; i<1000000; i++)
        System.out.println(getColumnChar(i) + ":" + i);
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - beginTime);
        //10w times use 573ms
        //100w times use 5708ms
        //增长接近线性
    }*/
}
