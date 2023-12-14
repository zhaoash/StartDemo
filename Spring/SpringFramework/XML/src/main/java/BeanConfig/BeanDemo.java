package BeanConfig;

import org.springframework.beans.factory.InitializingBean;

public class BeanDemo implements InitializingBean {

    public String name;
    public int age;

    public BeanDemo() {
        System.out.println("none arg");
    }

    public BeanDemo(String name) {
        System.out.println("name arg");
        this.name = name;
    }

    public BeanDemo(int age) {
        System.out.println("age arg");
        this.age = age;
    }

    public BeanDemo(String name, int age) {
        System.out.println("name and age arg");
        this.name = name;
        this.age = age;
    }

    public void init() {
        System.out.println("init method");
    }

    public void destroy() {
        System.out.println("destroy method");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet method");
    }
}
