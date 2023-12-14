public class UserFactory {
    public static User getUser(String name){
        System.out.println("getUser"+name);
        return new User();
    }
    public  User getUser2(){
        System.out.println("getUser2");
        return new User();
    }
}
