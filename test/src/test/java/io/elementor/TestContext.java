package io.elementor;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

public class TestContext {


    private HashMap<String, Object> context = new HashMap<>();

    public <T> T getResource(Class clz){
        if(get(clz.getName())==null){
            try {
                Object service = clz.getConstructor(null).newInstance();
                put(clz.getName(),service);

            } catch (InstantiationException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new RuntimeException(e);
            }

        }
        return (T)get(clz.getName());
    }

    public <T> T get(String key){
        Object o = context.get(key);
        if(o==null){
            return null;
        }else{
            return (T)o;
        }
    }

    public void put(String key, Object obj){
        context.put(key,obj);
    }



}
