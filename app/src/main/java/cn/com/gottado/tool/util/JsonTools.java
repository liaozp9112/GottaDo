package cn.com.gottado.tool.util;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * json 工具类
 *
 * @author liaozp 2015
 */
public class JsonTools {

    /**
     * 生成JSONObject
     *
     * @param jsonStr
     * @return
     */
    public static JSONObject generateJsonObj(String jsonStr) {
        JSONObject jsonObject = null;
        if (StringTools.strNotEmpty(jsonStr)) {
            try {
                int start = jsonStr.indexOf("{");
                int end = jsonStr.lastIndexOf("}");
                jsonStr = jsonStr.substring(start, end + 1);
                jsonObject = new JSONObject(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }

    /**
     * 生成JSONArray
     *
     * @param jsonStr
     * @return
     */
    public static JSONArray generateJsonArr(String jsonStr) {
        JSONArray jsonObject = new JSONArray();
        if (StringTools.strNotEmpty(jsonStr)) {
            try {
                int start = jsonStr.indexOf("[");
                int end = jsonStr.lastIndexOf("]");
                jsonStr = jsonStr.substring(start, end + 1);
                jsonObject = new JSONArray(jsonStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }


    /**
     * 获取Json的String属性
     *
     * @param object
     * @param key
     * @return
     */
    public static String getJsonString(JSONObject object, String key) {
        String value = "";
        if (contain(object, key)) {
            try {
                value = object.getString(key);
            } catch (Exception e) {
            }
        }
        return value;
    }

    /**
     * 判断JSON包含指定KEY
     *
     * @param json
     * @param key
     * @return
     */
    public static boolean contain(JSONObject json, String key) {
        return json.has(key);
    }

    /**
     * 将Map集合转换成JSONOject
     *
     * @param map
     * @return JSONObject
     */
    public static JSONObject swapMapToJSONObject(Map<String, Object> map) {
        JSONObject obj = new JSONObject();
        Iterator<String> iter = map.keySet().iterator();
        while (iter.hasNext()) {
            try {
                String key = iter.next();
                obj.put(key, map.get(key) != null ? map.get(key).toString() : "");
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return obj;

    }

    /**
     * 将一个JSONObject对象转换为一个HashMap
     *
     * @param jsonObj
     * @return Map
     */
    public static Map<String, Object> swapJsonToMap(JSONObject jsonObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (jsonObj != null) {
            Iterator<String> keys = jsonObj.keys();
            while (keys.hasNext()) {
                String key = keys.next();
                map.put(key, getJsonString(jsonObj, key));
            }
        }
        return map;
    }

    /**
     * 将一个value放进JSONObject中
     *
     * @param
     * @return
     */
    public static JSONObject put(JSONObject jsonObj, String key, Object value) {
        try {
            jsonObj.put(key, value);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObj;
    }

    /**
     *
     * @param jsonObj
     * @param key
     * @return
     */
    public static JSONObject getJsonObject(JSONObject jsonObj,String key){
        JSONObject json=null;
        try {
            json=jsonObj.getJSONObject(key);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }


    /**
     * 将一个JSONArray对象转换为一个ArrayList<HashMap<String, Object>>集合
     * @param jsonArr
     * @return
     */
    public static List<Map<String, Object>> parserJsonArrToMapList(JSONArray jsonArr) {
        List<Map<String, Object>> arrayList = new ArrayList<Map<String, Object>>();
        try {
            for (int i = 0; i < jsonArr.length(); i++) {
                arrayList.add(parserJsonToMap(jsonArr.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    /**
     *  将一个jsonArrStr字符串转换为一个ArrayList<HashMap<String, Object>>集合
     * @param jsonArrStr
     * @return
     */
    public static List<Map<String, Object>> parserJsonArrToMapList(String jsonArrStr) {
        List<Map<String, Object>> arrayList = null;
        try {
            arrayList = parserJsonArrToMapList(parseJsonArray(jsonArrStr));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
    /**
     * 将Map集合转换成JSONOject
     * @param  map
     * @return JSONObject
     */
    public static JSONObject parserMapToJSONObject(Map<String,Object> map){
        JSONObject obj = new JSONObject();
        Iterator<String> iter = map.keySet().iterator();
        while(iter.hasNext()){
            try {
                String key = iter.next();
                obj.put(key, map.get(key)!=null?map.get(key).toString():"");
            } catch (JSONException e) {

                e.printStackTrace();
            }
        }
        return obj;

    }
    /**
     * 生成JSONArray
     * @param jsonStr
     * @return
     */
    public static JSONArray parseJsonArray(String jsonStr){
        JSONArray jsonObject = new JSONArray();
        if(!StringTools.strEmpty(jsonStr)){
            try{
                int index = jsonStr.indexOf("[");
                int end   = jsonStr.lastIndexOf("]");
                jsonStr =jsonStr.substring(index,end+1);
                jsonObject = new JSONArray(jsonStr);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return jsonObject;
    }
    /**
     * 将List 转换成JSONArray
     * @param  listMap
     * @return JSONArray
     */
    public static JSONArray parseListToJSONArray(List<Map<String, Object>> listMap) {
        JSONArray array = new JSONArray();
        for(int i = 0; i < listMap.size(); i++){
            Map<String, Object> map = listMap.get(i);
            JSONObject jsonObject = parserMapToJSONObject(map);
            array.put(jsonObject);
        }
        return array;
    }


    /**
     * 将一个JSONObject对象转换为一个HashMap
     * @param jsonObj
     * @return
     */
    public static Map<String, Object> parserJsonToMap(JSONObject jsonObj){
        Map<String,Object> params = new HashMap<String, Object>();
        if(jsonObj!=null){
            Iterator<?> keys = jsonObj.keys();
            while(keys.hasNext()){
                String keyt = (String)keys.next();
                params.put(keyt,getJsonString(jsonObj, keyt));
            }
        }
        return params;
    }
}