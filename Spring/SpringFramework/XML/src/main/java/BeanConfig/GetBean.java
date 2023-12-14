package BeanConfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class GetBean {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

        // 1、根据BeanName或者别名去获取，需要类型强转
        BeanDemo beanDemo1 = (BeanDemo)context.getBean("createBeanDemo");
        BeanDemo beanDemo2 = (BeanDemo)context.getBean("createBeanDemoAlias1");

        // 2、根据BeanType去获取，只能由1个CreateBeanDemo类型的bean，如果匹配到多个会报错
        BeanDemo beanDemo3 = context.getBean(BeanDemo.class);

        // 3、根据BeanName去获取，并指定BeanType
        BeanDemo beanDemo4 = context.getBean("createBeanDemo", BeanDemo.class);

        //关闭前会执行bean的destroy方法
        context.close();
    }
}
