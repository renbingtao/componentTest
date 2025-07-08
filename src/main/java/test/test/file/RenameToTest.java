package test.test.file;

import org.junit.Test;

import java.io.File;

public class RenameToTest {

    @Test
    public void test() {
        File file = new File("C:\\Users\\rbt\\Desktop\\abc.txt");
        File file2 = new File("C:\\Users\\rbt\\Desktop\\test\\abc.txt");
        boolean b = file.renameTo(file2);
        System.out.println(b);
    }

}
