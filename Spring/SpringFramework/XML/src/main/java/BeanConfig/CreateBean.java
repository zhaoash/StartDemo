package BeanConfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class CreateBean {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","createBean");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.getBean("beanDemo9");
    }
}
