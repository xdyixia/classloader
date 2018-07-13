package classloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Demo {

    public static void main(String[] args) {
        //�����Զ���MyClassLoader����
        MyClassLoader myLoader = new MyClassLoader("D:\\javaee\\ClassLoaderTest");
        try {
            //����class�ļ�
            Class c = myLoader.loadClass("ClassLoaderTest");
            
            //��ӡ��������������Լ���������ĸ�������������
            ClassLoader cl = c.getClassLoader();
            System.out.println("�����ҵ����������:"+cl.toString());
            System.out.println("�����ҵ���������ĸ�������Ϊ:"+cl.getParent().toString());
            
            if(c != null){
                try {
                    Object obj = c.newInstance();
                    Method method = c.getDeclaredMethod("say",null);
            
                    //ͨ���������ClassLoaderTest���say����
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