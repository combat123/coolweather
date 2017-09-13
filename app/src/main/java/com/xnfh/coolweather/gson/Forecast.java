package com.xnfh.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by wangxuewei on 2017/9/13.
 */
public class Forecast {
    public String date;
    @SerializedName("tmp")
    public Temperature temperature;
    @SerializedName("cond")
    public Now.More more;

    public class Temperature {
        public String max;
        public String min;

        public class More {
            @SerializedName("txt_ d")
            public String info;

        }
    }
}