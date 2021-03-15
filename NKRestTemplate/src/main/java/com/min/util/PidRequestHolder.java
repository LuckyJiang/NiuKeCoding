package com.min.util;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;


/**
 * @author jxm
 */
public class PidRequestHolder {

    private final static ThreadLocal<Cookie> requestHolder = new ThreadLocal<>();

    public static void add(String name, String value) {
        requestHolder.set(new Cookie(name, value));
    }

    public static String getCurrentCookie() {
        return requestHolder.get().toString();
    }

    public static void remove() {
        requestHolder.remove();
    }
}

class Cookie implements Serializable {
    private String name;

    private String value;

    public Cookie(String name, String value){
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString(){
        if(StringUtils.isNoneEmpty(name) && StringUtils.isNoneEmpty(value))
            return name + "=" + value;
        return "";
    }
}
