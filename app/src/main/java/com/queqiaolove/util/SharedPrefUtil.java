package com.queqiaolove.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;

/**
 * SP 工具类
 */
public class SharedPrefUtil{
    private static SharedPreferences mSp;

    private static SharedPreferences getSharedPreferences(Context context) {
        if (mSp == null) {
            mSp = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        }
        return mSp;
    }

    public static void putBoolean(Context context, String key, boolean value) {
        getSharedPreferences(context).edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        return getSharedPreferences(context).getBoolean(key, defValue);
    }

    public static void putString(Context context, String key, String value) {
        getSharedPreferences(context).edit().putString(key, value).commit();
    }

    public static String getString(Context context, String key, String defValue) {
        return getSharedPreferences(context).getString(key, defValue);
    }

    public static void remove(Context context, String key) {

        getSharedPreferences(context).edit().remove(key).commit();
    }

    public static void putInt(Context context, String key, int value) {
        getSharedPreferences(context).edit().putInt(key, value).commit();
    }

    public static int getInt(Context context, String key, int defValue) {
        return getSharedPreferences(context).getInt(key, defValue);
    }

    public static void putSet(Context context, String key, Set<String> value) {
        getSharedPreferences(context).edit().putStringSet(key, value).commit();
    }

    public static HashSet<String> getSet(Context context, String key, Set<String> value) {

        return (HashSet<String>) getSharedPreferences(context).getStringSet(key, value);
    }

    public static void clear(Context context) {
        getSharedPreferences(context).edit().clear();
    }

    public static void clearCartSp(Context context, String spName){
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        preferences.edit().clear().commit();
    }
    /**
     * 保存对象到sp
     *
     * @param context
     * @param keyName key
     * @param t       对象
     */
    public static <T> void saveObj2SP(Context context, String spName, String keyName, T t) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        ByteArrayOutputStream bos;
        ObjectOutputStream oos = null;
        try {
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            byte[] bytes = bos.toByteArray();

            // String ObjStr = new String(bytes,0,bytes.length);
            String ObjStr = Base64.encodeToString(bytes, Base64.DEFAULT);
            preferences.edit().putString(keyName, ObjStr).commit();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭流
            if (oos != null) {
                try {
                    oos.flush();
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    /**
     *从sp中读取对象
     * @param context
     * @param spName sp文件名
     * @param keyNme 字段名
     * @param <T>    泛型参数
     * @return
     */
    public static <T extends Object> T getObjFromSp(Context context, String spName, String keyNme) {
        SharedPreferences preferences = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        byte[] bytes = Base64.decode(preferences.getString(keyNme, ""), Base64.DEFAULT);
        ByteArrayInputStream bis;
        ObjectInputStream ois = null;
        T obj = null;
        try {
            bis = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bis);
            obj = (T) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return obj;
    }

}
