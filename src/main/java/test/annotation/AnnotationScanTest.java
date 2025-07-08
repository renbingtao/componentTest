package test.annotation;

import org.junit.jupiter.api.Test;
import org.reflections.Reflections;

import java.util.Set;

public class AnnotationScanTest {

    @Test
    public void test() {
        // 要扫描的包
//        String packageName = "org.example.annotation";
        //能扫描
        String packageName = "org.*";
        //能扫描
//        String packageName = "org.example.*";
//        String packageName = "org.*.*";
        // 反射
        Reflections ref = new Reflections(packageName);
//        Reflections ref = new Reflections(new ConfigurationBuilder().forPackages("org.*.*"));
        // 获取扫描到的标记注解的集合
        Set<Class<?>> set = ref.getTypesAnnotatedWith(SimpleAnnotation.class);
        // 循环获取标记的注解
        for (Class clazz : set) {
            System.out.println(clazz.getName());
        }
    }

}
