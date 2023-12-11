package classloader;

import sun.security.ec.CurveDB;

public class ClassLoaderNameSpace {
    public static void main(String[] args){
        String s = new String("");
        Class clz0 = CurveDB.class;
        Class clz4 = String.class;
        Class clz1 = TestClass1.class;
        Class clz2 = TestClass2.class;
        Class clz3 = TestClass1.class;

    }
}
