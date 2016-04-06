package com.lihu.androidreporter.core.entity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by lihu on 2016-4-5.
 */
public class Report {
    private String tag;
    private long timeStamp;
    private int mMethod;
    private String mUrl;
    private Map<String, Object> content;
    private int type = Type.FILE;
    private Object extra;
    public final String DIVIDER = "&";

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public int getmMethod() {
        return mMethod;
    }

    public void setmMethod(int mMethod) {
        this.mMethod = mMethod;
    }

    public String getmUrl() {
        return mUrl;
    }

    public void setmUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public Map<String, Object> getContent() {
        return content;
    }

    public void setContent(Map<String, Object> content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public Object getExtra() {
        return extra;
    }

    public void setExtra(Object extra) {
        this.extra = extra;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String build() {
        return null;
    }


    @Override
    public String toString() {
        JSONObject pushObject = new JSONObject();
        try {
            pushObject.put("extra", getParamObject(getExtra()));
            pushObject.put("tag", getParamString(getTag()));
            pushObject.put("timeStamp", getTimeStamp());
            pushObject.put("mMethod", getmMethod());
            pushObject.put("mUrl", getParamString(getmUrl()));
            pushObject.put("type", getType());
            if (getContent() != null) {
                JSONObject content = new JSONObject();
                for (String key : getContent().keySet()) {
                    content.put(key, getContent().get(key));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return pushObject.toString();
    }

    private String getParamString(String param) {
        return param == null ? "" : param;
    }

    private String getParamObject(Object param) {
        return param == null ? "" : param.toString();
    }

    public interface Type {
        int FILE = 1;
        int HTTP = 2;
    }

    public interface Method {
        int GET = 1;
        int POST = 2;
    }
}
