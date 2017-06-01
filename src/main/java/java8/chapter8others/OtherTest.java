package java8.chapter8others;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.stream.Stream;

import org.junit.Test;

public class OtherTest {
    @Test
    public void demo(){
        //String.join
        String[] names = {"a","b","c","d"};
        String s = String.join(" ,", names);
        System.out.println(s);
        System.out.println(String.join(" ,", "e","f","g"));
    }
    @Test
    public void demo2(){
        //文件读取
        //try-with-resources
        try(Stream<String> lines = Files.lines(Paths.get("d:/test.txt"))){//流会自动关闭
            lines.filter(x->x.contains("password")).findFirst().ifPresent(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void demo3(){
        //base64
        byte[] buf = Base64.getEncoder().encode("123456".getBytes(StandardCharsets.UTF_8));
        System.out.println(new String(buf));
        String s = Base64.getEncoder().encodeToString("123456".getBytes());
        System.out.println(s);
        //原理简介
        /*每3个字节(24位)分为base64的4组(每组6个字节)，base64的每一组的值范围为0-63（共64个），将其对应到ascii中的64个可见字符。
         * （可见字符不止64个，选64个比较好用的字符，比如在url中不需要转义的字符，大小写字母，数字）
         * 得到的结果就是这些字符的ascii编码。
         * 源字节流字节个数可能不是3的倍数，不足补'='号。所以base64编码得到的字符串，最后可能有0、1、2个等号。
         * base64
         * 
         * 
         */
    }
}
