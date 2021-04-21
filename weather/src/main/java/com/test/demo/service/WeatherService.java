package com.test.demo.service;

import java.util.Optional;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.bean.Weatherinfo;
import com.test.demo.response.Response;

public interface WeatherService {

	JSONObject getProvinces();
	
	JSONObject getCities(String province);
	
	JSONObject getCounties(String city);
	
	Weatherinfo getWeather(String county);
	
	Response<Optional<Double>> getTemperature(String province, String city, String county);
}
