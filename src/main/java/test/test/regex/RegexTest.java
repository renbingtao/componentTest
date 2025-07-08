package test.test.regex;

import org.junit.jupiter.api.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {

    @Test
    public void test() {
        String text = "abcde(cd)hh";
        String regex = "\\((.*?)\\)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配器
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println(matcher.group(1));
        } else {
            System.out.println("no match");
        }
    }

    @Test
    public void test2() {
        doTest("abcde(cd)hh");
        doTest("abcdecd)hh");
        doTest("abcde(chh");
        doTest("abcde()hh");
    }

    private void doTest(String text) {
        String regex = "\\((.*?)\\)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配器
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println("match:" + matcher.group(1));
        } else {
            System.out.println("no match");
        }
    }

    @Test
    public void test3() {
        String text = "abcde(cd)hh";
        String regex = "(.*?)\\((.*?)\\)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配器
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println(matcher.group(2));
            System.out.println(matcher.group(1));
        } else {
            System.out.println("no match");
        }
    }

    @Test
    public void test4() {
        String fieldId = "date(substr[0|4])";
        Matcher strMatcher = Pattern.compile("(.*?)\\(substr\\[(.*?)\\|(.*?)\\]\\)").matcher(fieldId);
        if (strMatcher.find()) {
            String beginIndexStr = strMatcher.group(2);
            String endIndexStr = strMatcher.group(3);
            int beginIndex = Integer.parseInt(beginIndexStr);
            int endIndex = Integer.parseInt(endIndexStr);
            String fullStr = "2024-09";
            String substring = fullStr.substring(beginIndex, endIndex);
            System.out.println(substring);
        }
    }

    @Test
    public void test5() {
        String text = "select * from table where abc";
        String regex = "SELECT|select \\* FROM|from (.*?) (.*)";
        // 编译正则表达式
        Pattern pattern = Pattern.compile(regex);
        // 创建匹配器
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println(matcher.group(2));
        } else {
            System.out.println("no match");
        }
    }

}
