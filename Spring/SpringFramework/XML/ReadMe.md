## spring的getBean方法
- 根据BeanName去获取
- 根据BeanType去获取
- 根据BeanName并指定BeanType去获取

## 创建Bean的方式
- 构造方式 
  - 有参构造
  - 无参构造
- 工厂方式
  - 静态工厂
  - 实例工厂
  - 实现FactoryBean


```xml

    <bean id="user1" class="User">
        <!--不是类的构造方法的参数，是构造bean的方法的参数，在静态工厂中理解-->
        <constructor-arg name="name" ref="name" ></constructor-arg>
    </bean>
    <bean id="name" class="Name"/>
    <!--静态工厂-->
    <bean id="user2" class="UserFactory" factory-method="getUser">
        <constructor-arg name="name" value="111"/>
        <property name="" value=""/>
        <property name="" ref=""/>
    </bean>


  
    <!--



    //自动注入，byType按照类型，找到多个类型重复的则会报错，byName按照名字
    <bean autowire=""/>

    bean的实例化方式
        构造方式实例化
            无参构造
            有参构造
        工厂方式实例化
            静态工厂方法
            实例工厂方法
            实现FactoryBean规范延迟实例化Bean
    <bean factory-bean="" factory-method=""/>
    -->
    <!--
    依赖的注入方式
    set注入

        <property name="set方法名" value="值注入"/>
        <property name="set方法名" ref="引用注入"/>
        集合注入
            list
            set
            map
            properties
    构造Bean的方法注入
        <constructor-arg name="参数名" value="值注入"/>
        <constructor-arg name="参数名" ref="引用注入"/>’
    自动装配
    -->

<!--    标签的分类
        默认标签：使用默认命名空间，不用额外引入其他命名空间约束的标签
            <bean/>
            <beans/>    可以配置多套环境变量，可以通过虚拟机参数-Dspring.profiles.active=dev或者环境变量来指定System.setProperty("spring.profiles.active","dev")
                    <beans profile="dev"/>
                    <beans profile="pro"/>
            <import/>
            <alias/>：指定Bean的别名的标签
        自定义标签：需要额外引入其他命名空间约束，并通过前缀引用的标签-->
```
