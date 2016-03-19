package com.github.raylemon.weather.data

import com.github.raylemon.weather.data.domain.ForecastList
import com.github.raylemon.weather.data.json.ForecastResult
import com.github.raylemon.weather.data.mapper.JsonMapper
import com.google.gson.Gson
import java.net.URL

/**
 * Created by big04 on 06-03-16.
 */
class JsonServer : ForecastSource {
    val APP_ID = "f9d37616d477b96758d88a16d3a7c347"
    val DAILY_URL = "http://api.openweathermap.org/data/2.5/forecast/daily?lang=fr&units=metric&cnt=%d&APPID=$APP_ID&q=%s"

    override fun getForecast(cnt: Int, city: String): ForecastList {
        val jsonStr = URL(DAILY_URL.format(cnt, city)).readText()
        val result = Gson().fromJson(jsonStr, ForecastResult::class.java)
        val domain = JsonMapper.convertToDomain(result)
        DBServer().save(domain)
        return domain
    }
}