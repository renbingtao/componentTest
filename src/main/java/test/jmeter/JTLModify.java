package test.jmeter;

import org.apache.logging.log4j.util.Strings;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.charset.StandardCharsets;
import java.util.*;

//功能：把源jtl文件解析，只保留最大线程数时的数据，并保存到新的文件中
public class JTLModify {

    private static final String THREAD_KEY = "allThreads";

    private static final String JTL_PREFIX = "并发";

    private static final String KEY_SPLIT = ",";

    private String rootDirPath = null;
    private String destinationDirPath = null;

    private int THREAD_INDEX = -1;

    public void transfer(File source, File destination) throws Exception {
        boolean mkdir = destination.mkdir();
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

    //根据 C:\Users\rbt\Desktop\满线程jtl文件\一户通\并发100.jtl 生成 C:\Users\rbt\Desktop\满线程jtl文件\一户通文件夹
    private void parseFile(File file) throws Exception {
        String relativePath = createFolder(file);

        //C:\Users\rbt\Desktop\满线程jtl文件\一户通\并发100.jtl
        File tmp = new File(destinationDirPath + relativePath);

        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(tmp));

        String[] fileNameSplit = file.getName().split(JTL_PREFIX);
        //200.jtl
        String concurrentStr = fileNameSplit[1];
        int concurrentMaxNum = Integer.parseInt(concurrentStr.split("\\.")[0]);

        List<List<String>> rows = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(KEY_SPLIT);
                List<String> row = new ArrayList<>();
                for (String value : values) {
                    row.add(value);
                }
                rows.add(row);
            }
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0; i < rows.size(); i++) {
            List<String> line = rows.get(i);
            if (i == 0) {
                for (int j = 0; j < line.size(); j++) {
                    if (THREAD_KEY.equals(line.get(j))) {
                        THREAD_INDEX = j;
                        break;
                    }
                }
                String originLine = Strings.join(line, ',');
                bufferedWriter.write(originLine);
                bufferedWriter.newLine();
                continue;
            }
            String lineThreadsStr = line.get(THREAD_INDEX);
            int lineThreadsNum = Integer.parseInt(lineThreadsStr);
            if (lineThreadsNum == concurrentMaxNum) {
                String originLine = Strings.join(line, ',');
                bufferedWriter.write(originLine);
                bufferedWriter.newLine();
            }
        }
        bufferedWriter.flush();
        bufferedWriter.close();
        System.out.println("完成文件：" + tmp.getPath());
    }

    private String createFolder(File file) {
        String path = file.getPath();

        String pathEncode = Base64.getEncoder().encodeToString(path.getBytes(StandardCharsets.UTF_8));
        String rootDirPathEncode = Base64.getEncoder().encodeToString(this.rootDirPath.getBytes(StandardCharsets.UTF_8));

        String[] split = pathEncode.split(rootDirPathEncode);
        String relativePathEncode = split[1];

        //\一户通\并发100.jtl
        String relativePath = new String(Base64.getDecoder().decode(relativePathEncode));

        String[] dirSplit = relativePath.split("\\\\");
        String[] dirSuffixArr = Arrays.copyOf(dirSplit, dirSplit.length - 1);
        //\一户通
        String dirSuffix = String.join("\\", dirSuffixArr);

        new File(destinationDirPath + dirSuffix).mkdirs();
        return relativePath;
    }

}


