package com.example.mylibrary.json;



import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wwb on 2018/5/18/018.
 * for:
 */

public class GosnUtils {
    private static Gson gson = null;

    static {
        if (gson == null) {
            gson = new Gson();
        }
    }

    private GosnUtils() {

    }

    /**
     * 转换成json
     */
    public static String GsonString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     */
    public static <T> T GsonToList(String gsonstring, Class<T> tClass) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonstring, tClass);
        }
        return t;
    }


    /**
     * 转成map的
     *
     * @param gsonString
     * @return
     */
    public static <T> Map<String, T> GsonToMaps(String gsonString) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

    public static <T> List<T> GsonToLists(String jsonString, Class<T> cls) {
        List<T> list = new ArrayList<T>();
        try {
            Gson gson = new Gson();
            JsonArray arry = new JsonParser().parse(jsonString).getAsJsonArray();
            for (JsonElement jsonElement : arry) {
                list.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static boolean getBoolean(String message) {

        try {
            JSONObject jsonObject = getJSon(message);
            boolean flag = jsonObject.getBoolean("flag");
            return flag;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static Integer getInt(String message) {

        try {
            JSONObject jsonObject = getJSon(message);
            Integer count = jsonObject.getInt("count");
            return count;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getMessage(String messages) {

        try {
            JSONObject jsonObject = new JSONObject(messages);
            String messgae = jsonObject.getString("message");
            return messgae;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static JSONObject getJSon(String message) {
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(message);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static String getSmscode(String messages, String value) {

        try {
            JSONObject jsonObject = new JSONObject(messages);
            String messgae = jsonObject.getString(value);
            return messgae;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static <T> T getSmscodes(String messages, String value) {
        T messgae = null;
        try {
            JSONObject jsonObject = new JSONObject(messages);
            messgae = (T) jsonObject.get(value);
            return messgae;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return messgae;
    }


}
