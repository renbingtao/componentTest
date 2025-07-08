package test.jmeter;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class JTest {

    @Test
    public void test() {
        try {
            String command = "D:\\apache-jmeter-5.6.3\\bin\\jmeter.bat -g C:\\Users\\rbt\\Desktop\\jtl文件\\一户通\\并发100.jtl -o C:\\Users\\rbt\\Desktop\\jtl2html\\一户通\\并发100";

            Runtime runtime = Runtime.getRuntime();
            Process process = runtime.exec(command);
            // 读取命令执行结果
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            // 等待命令执行完成
            process.waitFor();
            // 获取退出值
            int exitValue = process.exitValue();
            System.out.println("Exit value: " + exitValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
