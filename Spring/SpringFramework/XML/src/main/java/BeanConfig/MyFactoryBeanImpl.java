package BeanConfig;

import org.springframework.beans.factory.FactoryBean;

public class MyFactoryBeanImpl implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        System.out.println("implements FactoryBean");
        return new BeanDemo();
    }

    @Override
    public Class<?> getObjectType() {
        return BeanDemo.class;
    }
}
