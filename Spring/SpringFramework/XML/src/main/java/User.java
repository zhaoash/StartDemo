import org.springframework.beans.factory.InitializingBean;

public class User implements InitializingBean {

    public Name name;

    public User() {
    }

    public User(Name name) {
        System.out.println("cc"+name);
        this.name = name;
    }

    public void setName(Name name) {
        System.out.println("setName");
        this.name = name;
    }
    public void init(){
        System.out.println("init");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("after");
    }
}
