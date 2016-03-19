package com.github.raylemon.weather.data

import com.github.raylemon.weather.data.domain.ForecastList
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import java.util.*

/**
 * Created by big04 on 17-03-16.
 */
object ForecastProvider : ForecastSource, AnkoLogger {
    val sources = arrayOf(DBServer(), JsonServer())
    override fun getForecast(cnt: Int, city: String): ForecastList {
        for (source in sources) {
            val fore = source.getForecast(cnt, city)
            if (fore != null) {
                info("data sent from $source")
                return fore
            }
        }
        throw NoSuchElementException("No element found !")
    }
}