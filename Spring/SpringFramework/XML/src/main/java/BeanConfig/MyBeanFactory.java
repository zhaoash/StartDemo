package BeanConfig;

import org.springframework.beans.factory.FactoryBean;

public class MyBeanFactory {

    public static BeanDemo getBeanByStaticNoArg(){
        System.out.println("getBeanByStaticNoArg");
        return new BeanDemo();
    }

    public static BeanDemo getBeanByStaticHasArg(String arg){
        System.out.println("getBeanByStaticHasArg:" + arg);
        return new BeanDemo();
    }

    public BeanDemo getBeanByInstanceNoArg(){
        System.out.println("getBeanByInstanceNoArg");
        return new BeanDemo();
    }

    public BeanDemo getBeanByInstanceHasArg(String arg){
        System.out.println("getBeanByInstanceHasArg:" + arg);
        return new BeanDemo();
    }
}
