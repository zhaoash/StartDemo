## XML配置文件
- 标签分类
  - 默认标签：使用默认命名空间，不用额外引入其他命名空间约束的标签
    - beans 可以配置多套环境变量，可以通过虚拟机参数-Dspring.profiles.active=dev或者环境变量来指定System.setProperty("spring.profiles.active","dev")
    - bean
    - import
    - alias
  - 自定义标签：需要额外引入其他命名空间约束，并通过前缀引用的标签

## 创建Bean的方式
### 实例化方式
- 构造方式
  - 无参构造
  - 有参构造
- 工厂方式
  - 静态工厂
  - 实例工厂
  - 实现FactoryBean

### 实例化流程
xml的bean标签 --> beanDefinition --> beanFactory:beanDefinitionMap --> bean实例化 --> postProcessBeforeInitialization --> afterPropertiesSet --> bean初始化方法 --> postProcessAfterInitialization --> beanFactory:singletonObjects

### Spring的后处理器
- BeanFactoryPostProcessor：bean工厂后处理器，在BeanDefinitionMap填充完毕(因为是完毕，所以只执行一次)，Bean实例化之前执行
  - 子接口：BeanDefinitionRegistryPostProcessor：专门用来注册BeanDefinition的
  - ？？多个BeanFactoryPostProcessor怎么执行，顺序如何
  - ？？验证执行几次
- BeanPostProcessor：bean后处理器，在bean实例化之后（每个bean实例化都会执行），填充到singletonObjects之前执行
  - 方法：postProcessBeforeInitialization
  - 方法：postProcessAfterInitialization
  - ？？多个BeanPostProcessor怎么执行，顺序如何
  - ？？验证执行几次

### Bean的生命周期
- Bean的实例化阶段
- Bean的初始化阶段
  bean实例的属性填充 --> Aware接口属性注入 --> postProcessBeforeInitialization --> afterPropertiesSet --> bean初始化方法 --> postProcessAfterInitialization
  - bean实例的属性填充
    - 普通属性：直接通过set方法的反射设置进去
    - 单向对象引用：从容器中获取后再通过set方法反射设置进去，容器中没有，则先创建被注入的bean（完成整个生命周期）后，再进行注入操作；实例化的先后与配置文件中bean标签的先后顺序有关
    - 双向对象引用（循环依赖）：三级缓存
- Bean的完成阶段

## Bean属性注入方式
- set注入
  - 一般值注入
  - 引用值注入
  - 集合注入
    - list
    - set
    - map
    - properties
  - 空注入
- 构造注入
  - 构造方式
    - 有参构造
  - 工厂方式
    - 静态工厂
    - 实例工厂
- 自动注入
  - 按照名字
  - 按照类型

## 获取Bean的方式
- 根据BeanName去获取
- 根据BeanType去获取
- 根据BeanName并指定BeanType去获取

## 整合第三方框架（看完MyBatis后再看回BV1rt4y1u7q5 p58-p65）
### 整合第三方框架的两种模式
- 不需要自定义命名空间：如MyBatis
- 需要引入第三方框架命名空间：如Dubbo