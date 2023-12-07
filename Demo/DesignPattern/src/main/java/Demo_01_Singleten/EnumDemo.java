package Demo_01_Singleten;

import jdk.nashorn.internal.runtime.JSONFunctions;

public enum EnumDemo {
    JAVA{
        public void get(){
            System.out.println("java");
        }
    },
    JSON{
        public int a;
        public void outputA(){
            System.out.println("json: "+a);
        }
    };

    public void get() {
    }
    public void outputA(){

    }
}
