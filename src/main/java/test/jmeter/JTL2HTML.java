package test.jmeter;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Base64;

public class JTL2HTML {

    private static final String JMETER_PATH = "D:\\apache-jmeter-5.6.3\\bin\\jmeter.bat";

    private static final String COMMAND = JMETER_PATH + " -g %s -o %s";

    private String rootDirPath = null;

    private String destinationDirPath = null;

    public void jtl2html(File source, File destination) throws Exception {
        destination.mkdir();
        this.rootDirPath = source.getPath();
        this.destinationDirPath = destination.getPath();
        parse(source);
    }

    private void parse(File file) throws Exception {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File subFile : files) {
                    parse(subFile);
                }
            }
        } else {
            if (file.getName().endsWith(".jtl")) {
                parseFile(file);
            }
        }
    }

    private void parseFile(File file) throws Exception {
        String folerPath = createFolder(file);
        exec(file, folerPath);
    }

    private String createFolder(File file) {
        String path = file.getPath();

        //\一户通\并发100.jtl
        String relativePath = path.replace(this.rootDirPath, "");

        String[] dirSplit = relativePath.split("\\.");
        String[] dirSuffixArr = Arrays.copyOf(dirSplit, dirSplit.length - 1);
        //\一户通\并发100
        String dirSuffix = String.join("\\", dirSuffixArr);

        String createFolderPath = destinationDirPath + dirSuffix;

        new File(createFolderPath).mkdirs();
        return createFolderPath;
    }

    private void exec(File jtl, String dir) {
        try {
            String command = String.format(COMMAND, jtl.getPath(), dir);

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
