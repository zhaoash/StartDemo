# Spring Framework

## IOC

### 定义Bean
Bean标签

### Bean的生命周期
- Bean的实例化阶段
- Bean的初始化阶段
  bean实例的属性填充 --> Aware接口属性注入 --> postProcessBeforeInitialization --> afterPropertiesSet --> bean初始化方法 --> postProcessAfterInitialization
    - bean实例的属性填充
        - 普通属性：直接通过set方法的反射设置进去
        - 单向对象引用：从容器中获取后再通过set方法反射设置进去，容器中没有，则先创建被注入的bean（完成整个生命周期）后，再进行注入操作；实例化的先后与配置文件中bean标签的先后顺序有关
        - 双向对象引用（循环依赖）：三级缓存
- Bean的完成阶段

### 创建Bean

#### XML时代
通过指定xml文件来读取Bean定义
- 构造方式
  ```xml
  <bean id = "BeanName" class = "全类名"/> 
  ```
- 工厂方式
  - 静态工厂
  ```xml
  <bean id = "BeanName" class = "工厂类全类名" factory-method="方法名"/> 
  ```
  - 实例工厂
  - 实现FactoryBean

#### XML+注解时代

#### 全注解时代