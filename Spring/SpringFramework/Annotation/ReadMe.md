## Bean基本注解开发：注解替代xml配置文件中的标签，配置类替代配置文件

- 2.5时代，xml+注解
  - xml配置component注解的扫描路径
    - <context:component-scan base-package=""/>
  - Component注解：配置Bean的信息
    - @Component("{BeanName}") <==替换bean标签==> <bean id="" class=""/>
      - 不配置id，id默认为类名，首字母小写
    - 衍生注解
      - @Repository
      - @Service
      - @Controller
  - 依赖注入：可以用在字段上也可以用在set方法上
    - @Value
    - @Autowired
      - 根据数据类型注入，如果同一类型的有多个，再尝试根据名字匹配
      - 可以配合Qualifier注解来指定beanName
      - 写在方法上时，根据参数类型来注入，如果同一类型的有多个，再尝试根据名字匹配，如果参数是集合，根据集合元素类型来注入，找到多少个，注入多少个
  - 非自定义bean的注解
    - @Bean("{BeanName}"),不加{BeanName}，默认为方法名
    - 注入参数：
      - 普通参数：@Value用在参数上
      - 引用类型
        - @Autowired 按类型，可省略
        - @Qualifier 搭配Autowired，Autowired可省略
- 3.0时代，全注解
  - 配置类注解
    - @Configuration：标注当前类是一个配置类 + @Component
    - @ComponentScan
    - @PropertySource
    - @import导入其他配置类
@Primary用于存在多个相同类型的Bean时使用autowired注入时提高优先级
@Profile 等于beans中的profile,用来指定该bean在哪个环境中生效

注解整合第三方没看p79-p81


AOP
实现方案：动态代理技术
相关概念

