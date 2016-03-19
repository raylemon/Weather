package com.github.raylemon.weather.data.db

import com.orm.SugarRecord
import com.orm.dsl.Unique


/**
 * Created by big04 on 17-03-16.
 */

class DbForecast(
        @Unique var dt: Long = 0L,
        var city: String = "",
        var country: String = "",
        var desc: String = "",
        var icon: String = "",
        var pressure: Float = 0f,
        var humidity: Int = 0,
        var day: Float = 0f,
        var min: Float = 0f,
        var max: Float = 0f,
        var night: Float = 0f,
        var morn: Float = 0f,
        var eve: Float = 0f) : SugarRecord()