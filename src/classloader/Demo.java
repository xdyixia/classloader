package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) {
        //创建自定义MyClassLoader对象。
        MyClassLoader myLoader = new MyClassLoader("D:\\javaee\\ClassLoaderTest");
        try {
            //加载class文件
            Class c = myLoader.loadClass("ClassLoaderTest");
            
            //打印类加载器的名字以及类加载器的父加载器的名字
            ClassLoader cl = c.getClassLoader();
            System.out.println("加载我的类加载器是:"+cl.toString());
            System.out.println("加载我的类加载器的父加载器为:"+cl.getParent().toString());
            
            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
            
                    //通过反射调用ClassLoaderTest类的say方法
                    method.invoke(obj, null);
                } catch (InstantiationException | IllegalAccessException 
                        | NoSuchMethodException
                        | SecurityException | 
                        IllegalArgumentException | 
                        InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}