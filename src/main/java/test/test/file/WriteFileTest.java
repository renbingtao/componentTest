package test.test.file;

import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFileTest {

    @Test
    public void test() {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("C:\\Users\\rbt\\Desktop\\abc.txt", true));
            bw.append("abc");
            bw.newLine();
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
            } catch (IOException e2) {
                e2.printStackTrace();
            }
        }
        System.out.println("abc");
    }

}
