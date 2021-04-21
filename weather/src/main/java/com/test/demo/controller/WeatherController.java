package com.test.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.test.demo.bean.Weatherinfo;
import com.test.demo.response.Response;
import com.test.demo.service.WeatherService;

@Controller
public class WeatherController {

	@Autowired
	WeatherService weatherService;

	@RequestMapping(value = "weather/getProvinces", method = RequestMethod.GET)
	@ResponseBody
	public Response<JSONObject> getProvinces() {

		return Response.succData(weatherService.getProvinces());
	}

	@RequestMapping(value = "weather/getCities", method = RequestMethod.POST)
	@ResponseBody
	public Response<JSONObject> getCities(@RequestParam(value = "province") String province) {

		return Response.succData(weatherService.getCities(province));
	}

	@RequestMapping(value = "weather/getCounties", method = RequestMethod.POST)
	@ResponseBody
	public Response<JSONObject> getCounties(@RequestParam(value = "city") String city) {

		return Response.succData(weatherService.getCounties(city));
	}

	@RequestMapping(value = "weather/getWeather", method = RequestMethod.POST)
	@ResponseBody
	public Response<Weatherinfo> getWeather(@RequestParam(value = "county") String county) {

		return Response.succData(weatherService.getWeather(county));
	}

	@RequestMapping(value = "weather/getTemperature", method = RequestMethod.POST)
	@ResponseBody
	public Response<Optional<Double>> getTemperature(@RequestParam(value = "province") String province,
			@RequestParam(value = "city") String city, @RequestParam(value = "county") String county) {

		return weatherService.getTemperature(province, city, county);
	}
}
