package com.xnfh.coolweather.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.xnfh.coolweather.db.City;
import com.xnfh.coolweather.db.Country;
import com.xnfh.coolweather.db.Province;
import com.xnfh.coolweather.gson.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by wangxuewei on 2017/9/11.
 */
public class Utility {
    /**
     * 解析省份
     * @param response
     * @return
     */
    public static boolean handleProvinceResponse(String response) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allProvince = new JSONArray(response);
                for (int i=0;i<allProvince.length();i++) {
                    JSONObject povinceObject = allProvince.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(povinceObject.getString("name"));
                    province.setProvinceCode(povinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    /**
     * 解析市
     * @param response
     * @return
     */
    public static boolean handleCityResponse(String response,int provinceId) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCitys = new JSONArray(response);
                for (int i=0;i<allCitys.length();i++) {
                    JSONObject cityObject = allCitys.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    /**
     * 解析县
     * @param response
     * @return
     */
    public static boolean handleCountryResponse(String response,int cityId) {
        if(!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCountrys = new JSONArray(response);
                for (int i=0;i<allCountrys.length();i++) {
                    JSONObject countryObject = allCountrys.getJSONObject(i);
                    Country country = new Country();
                    country.setCountryName(countryObject.getString("name"));
                    country.setWeatherId(countryObject.getString("weather_id"));
                    country.setCityId(cityId);
                    country.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return false;
    }

    /**
     * 将json解析成weather实体类
     * @param response
     * @return
     */
    public static Weather handleWeatherResponse(String response) {
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
