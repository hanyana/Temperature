package com.test.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.bean.Weatherinfo;
import com.test.demo.constant.ResponseCodeConst;
import com.test.demo.response.Response;

@Service
public class WeatherServiceImpl implements WeatherService {

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	@Retryable(value=Exception.class, maxAttempts=3)
	public JSONObject getProvinces() {
		String url = "http://www.weather.com.cn/data/city3jdata/china.html";
		String json = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}

	@Override
	@Retryable(value=Exception.class, maxAttempts=3)
	public JSONObject getCities(String province) {
		String url = "http://www.weather.com.cn/data/city3jdata/provshi/" + province + ".html";
		String json = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}

	@Override
	@Retryable(value=Exception.class, maxAttempts=3)
	public JSONObject getCounties(String city) {
		String url = "http://www.weather.com.cn/data/city3jdata/station/" + city + ".html";
		String json = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = JSONObject.parseObject(json);
		return jsonObject;
	}

	@Override
	@Retryable(value=Exception.class, maxAttempts=3)
	public Weatherinfo getWeather(String county) {
		String url = "http://www.weather.com.cn/data/sk/" + county + ".html";
		String json = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = JSONObject.parseObject(json);
		String weather = jsonObject.getString("weatherinfo");
		Weatherinfo weatherInfo = JSONObject.parseObject(weather, Weatherinfo.class);
		return weatherInfo;
	}

	@Override
	@Retryable(value=Exception.class, maxAttempts=3)
	public Response<Optional<Double>> getTemperature(String province, String city, String county) {
		String provinceCode = getProvinces().getString(province);
		if (provinceCode == null) {
			return Response.wrap(ResponseCodeConst.PROVINCE_ERROR);
		}

		String cityCode = getCities(province).getString(city);
		if (cityCode == null) {
			return Response.wrap(ResponseCodeConst.CITY_ERROR);
		}

		String countyCode = getCounties(province + city).getString(county);
		if (countyCode == null) {
			return Response.wrap(ResponseCodeConst.COUNTY_ERROR);
		}

		String url = "";
		if (province.equals("10101") || province.equals("10102") || province.equals("10103")
				|| province.equals("10104")) {
			url = "http://www.weather.com.cn/data/sk/" + (province + county + city) + ".html";
		} else {
			url = "http://www.weather.com.cn/data/sk/" + (province + city + county) + ".html";
		}
		String json = restTemplate.getForObject(url, String.class);
		JSONObject jsonObject = JSONObject.parseObject(json);
		String weather = jsonObject.getString("weatherinfo");
		Weatherinfo weatherInfo = JSONObject.parseObject(weather, Weatherinfo.class);
		Double temp = 0.0;
		if (weatherInfo != null) {
			temp = Double.parseDouble(weatherInfo.getTemp());
		}
		return Response.succData(Optional.ofNullable(temp));
	}
}
