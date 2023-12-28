package BeanConfig;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.annotation.PostConstruct;

public class CreateBean {
    @PostConstruct
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active","beanConfig");
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        context.getBean("beanDemo");
        try {

        }finally {
            
        }
    }
}
