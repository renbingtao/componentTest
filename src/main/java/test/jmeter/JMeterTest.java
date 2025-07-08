package test.jmeter;

import org.junit.jupiter.api.Test;

import java.io.File;

public class JMeterTest {

    @Test
    public void modify() throws Exception {
        File sourceDir = new File("C:\\Users\\rbt\\Desktop\\jtl文件");
        File desDir = new File("C:\\Users\\rbt\\Desktop\\满线程jtl文件");
        JTLModify jtlModify = new JTLModify();
        jtlModify.transfer(sourceDir, desDir);
    }

    @Test
    public void jtl2html() throws Exception {
        File sourceDir = new File("C:\\Users\\rbt\\Desktop\\0511全流程单激活2010有思考结果");
        File desDir = new File("C:\\Users\\rbt\\Desktop\\0511全流程单激活2010有思考结果html");
        JTL2HTML jtl2HTML = new JTL2HTML();
        jtl2HTML.jtl2html(sourceDir, desDir);
    }

}
