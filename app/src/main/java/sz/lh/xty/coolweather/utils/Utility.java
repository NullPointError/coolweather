package sz.lh.xty.coolweather.utils;

import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import sz.lh.xty.coolweather.db.City;
import sz.lh.xty.coolweather.db.County;
import sz.lh.xty.coolweather.db.Province;

/**
 * Created by Administrator on 2017/10/21 0021.
 */

public class Utility {

    //解析和处理服务器返回的省级数据
    public static boolean handleProvinceResponse(String response) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray provinceJsonArray = new JSONArray(response);
                for (int i = 0; i < provinceJsonArray.length(); i++) {
                    JSONObject jsonObject = provinceJsonArray.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(jsonObject.getString("name"));
                    province.setProvinceCode(jsonObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    //解析和处理服务器返回的市级数据
    public static boolean handleCityResponse(String response, int provinceId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray cityJsonArray = new JSONArray(response);
                for (int i = 0; i < cityJsonArray.length(); i++) {
                    JSONObject jsonObject = cityJsonArray.getJSONObject(i);
                    City city = new City();
                    city.setCityName(jsonObject.getString("name"));
                    city.setCityCode(jsonObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                LogUtils.v("handleCityResponse","返回数据成功");
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        LogUtils.v("handleCityResponse","返回数据失败");
        return false;
    }

    //解析和处理服务器返回的县级数据
    public static boolean handleCountyResponse(String response, int cityId) {
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject countJsonArray = jsonArray.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countJsonArray.getString("name"));
                    county.setWeatherId(countJsonArray.getString("weather_id"));
                    county.setCityId(cityId);
                    county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

}
