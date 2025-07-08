package test.test.string;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;

public class TestCase {

    @Test
    public void test() {
        System.out.println(formatTest(1.1));
        System.out.println(formatTest(123.4512));
        System.out.println(formatTest(123.4592));

        System.out.println(formatTest2(123.4592));
    }

    public String formatTest(Object obj) {
        return String.format("%,.2f", obj);
    }

    public String formatTest2(Object obj) {
        return String.format("%,.0f", obj);
    }

    @Test
    public void test2() {
        StringBuilder stringBuilder = new StringBuilder("a;b;c;");
        String substring = stringBuilder.substring(0, stringBuilder.length() - 1);
        System.out.println(substring);
    }

    @Test
    public void test3() {
        String a = "amdgms_1";
        char c = a.charAt(7);
        System.out.println('1' == c);
        System.out.println('2' == c);
    }

    @Test
    public void test4() {
        String msg = String.format("abc%sdef", 99);
        System.out.println(msg);
    }

    @Test
    public void test5() {
        String msg = String.format("ecm-thread-%s-%s", "abc", new Long(444));
        System.out.println(msg);
    }

    @Test
    public void test6() {
        String msg = String.format("%02d", 12);
        System.out.println(msg);
    }

    @Test
    public void test7() {
        String str = "a=,b=";
        HashMap<String, String> companyAttachMap = new HashMap<>();
        String[] entryArr = str.split(",");
        for (int i = 0; i < entryArr.length; i++) {
            String[] entry = entryArr[i].split("=");
            companyAttachMap.put(entry[0], entry[1]);
        }
        System.out.println(companyAttachMap);
    }

    @Test
    public void test8() {
        HashSet<String> nameSet = new HashSet<>();
        doAdd(nameSet, "abc");
        doAdd(nameSet, "abc");
        doAdd(nameSet, "abc(1)");
        System.out.println(nameSet.toString());
    }

    private void doAdd(HashSet<String> nameSet, String name) {
        if (!nameSet.contains(name)) {
            nameSet.add(name);
        } else {
            int nameSuffix = 1;
            while (nameSet.contains(name + "(" + nameSuffix + ")")) {
                nameSuffix++;
            }
            name = name + "(" + nameSuffix + ")";
            nameSet.add(name);
        }
    }

    @Test
    public void test9() {
        String s = RandomStringUtils.randomAlphabetic(10);
        System.out.println(s);
    }

    @Test
    public void test10() {
        String msg = String.format("abc%s", "pp");
        System.out.println(msg);
    }

    @Test
    public void test11() {
        String response = "abc.tar.gz";
        String response2 = "abc";

        int fileNameIndex = response.indexOf(".");
        if (fileNameIndex == -1) {
            response = response + "(1)";
        } else {
            response = response.substring(0, fileNameIndex) + "(1)" + response.substring(fileNameIndex);
        }
        System.out.println(response);
    }

}
