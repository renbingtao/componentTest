package test.jmeter;

import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CalStatistic {


    //里面的map key=登录 v=522
    //外面的map key=50(并发数) v=map
    private static Map<String, TreeMap<Integer, Integer>> statisticMap = new HashMap<>();

    // minResTime maxResTime meanResTime
    private static final String INDICATOR = "meanResTime";

    private static final String[] INDICATOR_ARR = new String[]{"minResTime", "maxResTime", "meanResTime"};

    public static void main(String[] args) {
        File rootDir = new File("D:\\apache-jmeter-5.6.3\\myshell\\0502\\05023分钟区间\\满线程jtl2html\\云签线下");
        File[] files = rootDir.listFiles();

        for (File file : files) {
            //并发50
            String name = file.getName();
            //50
            String conStr = name.split("并发")[1];
            int conInt = Integer.parseInt(conStr);

            File[] filesList = file.listFiles();
            for (File subFile : filesList) {
                if ("statistics.json".equals(subFile.getName())) {
                    parseJson(subFile.getPath(), conInt);
                    break;
                }
            }
        }

        TreeMap<Integer, Integer> next = statisticMap.values().iterator().next();

        System.out.print("3分钟" + INDICATOR + "\t");
        next.forEach((k1, v1) -> {
            System.out.print(k1 + "\t");
        });
        System.out.println();

        statisticMap.forEach((k1, v1) -> {
            System.out.print(k1);
            v1.forEach((k2, v2) -> {
                System.out.print("\t" + v2);
            });
            System.out.println();
        });

    }

    private static void parseJson(String pathToJson, int conInt) {
        try {
            // 读取文件内容到字符串
            String jsonContent = new String(Files.readAllBytes(Paths.get(pathToJson)));

            // 解析JSON对象
            JSONObject jsonObject = new JSONObject(jsonContent);

            //登录 刷新 total 打开单据 进入列表
            Set<String> indicatorList = jsonObject.keySet();

            for (String indicator : indicatorList) {
                if ("Total".equals(indicator)) {
                    continue;
                }

                TreeMap<Integer, Integer> map = statisticMap.get(indicator);
                if (map == null) {
                    map = new TreeMap(new Comparator<Integer>() {
                        public int compare(Integer obj1, Integer obj2) {
                            // 降序排序
                            return obj1.compareTo(obj2);
                        }
                    });
                    statisticMap.put(indicator, map);
                }

                JSONObject indicatorValues = jsonObject.getJSONObject(indicator);
                int min = indicatorValues.getInt(INDICATOR);
                map.put(conInt, min);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
