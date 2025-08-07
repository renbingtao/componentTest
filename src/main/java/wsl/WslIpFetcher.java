package wsl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WslIpFetcher {

    public static void main(String[] args) {
        try {
            // 获取WSL的IP地址
            String wslIp = getWslIpAddress();
            if (wslIp != null && !wslIp.isEmpty()) {
                System.out.println("WSL的IP地址: " + wslIp);
            } else {
                System.out.println("未找到WSL的IP地址，请检查WSL是否已启动");
            }
        } catch (IOException e) {
            System.err.println("获取WSL IP时发生错误: " + e.getMessage());
        } catch (InterruptedException e) {
            System.err.println("操作被中断: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /**
     * 获取WSL的IP地址
     *
     * @return WSL的IP地址，若获取失败则返回null
     * @throws IOException          执行命令时发生IO错误
     * @throws InterruptedException 线程被中断
     */
    public static String getWslIpAddress() throws IOException, InterruptedException {
        // 执行WSL命令获取eth0接口的IP地址
        // 命令解释: wsl -- ip addr show eth0 显示WSL中eth0接口的信息
        // grep -oP '(?<=inet\s)\d+(\.\d+){3}' 提取IP地址部分
        List<String> command = new ArrayList<>();
        command.add("wsl");
        command.add("--");
        command.add("bash");
        command.add("-c");
        command.add("ip addr show eth0 | grep -oP '(?<=inet\\s)\\d+(\\.\\d+){3}' | head -n 1");

        // 构建进程并执行命令
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.redirectErrorStream(true); // 合并错误流到输入流
        Process process = processBuilder.start();

        // 读取命令输出
        StringBuilder output = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream(), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line.trim());
            }
        }

        // 等待进程完成并获取退出码
        int exitCode = process.waitFor();
        if (exitCode != 0) {
            System.err.println("命令执行失败，退出码: " + exitCode);
            return null;
        }

        return output.toString();
    }
}

