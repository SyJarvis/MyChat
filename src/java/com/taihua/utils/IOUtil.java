package com.taihua.utils;

import java.io.*;

public class IOUtil {
    /* 写对象 */
    public static void writeObject(Object obj, OutputStream os){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(obj);
            oos.flush();
        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    /* 读对象 */
    public static Object readObject(InputStream is){
        Object obj = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(is);
            obj = ois.readObject();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return obj;
    }
}
