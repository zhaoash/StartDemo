import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class main {
    public static void main(String[] args) {

        applicationContext();

    }
    public static void applicationContext(){
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        System.out.println(context.getBean("user"));
        System.out.println("user2: "+context.getBean("user2"));
        System.out.println("user4: "+context.getBean("user4"));
    }
    public void beanFactory(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory);

        reader.loadBeanDefinitions("beans.xml");

        System.out.println(beanFactory.getBean("user"));
    }
}
