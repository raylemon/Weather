package com.github.raylemon.weather.data.domain

import android.os.Parcel
import android.os.Parcelable
import java.util.*

/**
 * Created by big04 on 06-03-16.
 */
data class ForecastList(val city: String, val country: String, val weather: List<Forecast>) : Parcelable {
    operator fun get(position: Int) = weather[position]
    val size = weather.size

    constructor(source: Parcel) : this(source.readString(), source.readString(), { val l = ArrayList<Forecast>(); source.readList(l, Forecast::class.java.classLoader); l }.invoke())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(city)
        dest?.writeString(country)
        dest?.writeList(weather)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<ForecastList> = object : Parcelable.Creator<ForecastList> {
            override fun createFromParcel(source: Parcel): ForecastList = ForecastList(source)

            override fun newArray(size: Int): Array<ForecastList?> = arrayOfNulls(size)
        }
    }
}

data class Forecast(val dt: Long, val desc: String, val icon: String, val temp: Temperatures, val pressure: Float, val humidity: Int) : Parcelable {
    constructor(source: Parcel) : this(source.readLong(), source.readString(), source.readString(), source.readTypedObject(Temperatures.CREATOR), source.readFloat(), source.readInt())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(dt)
        dest?.writeString(desc)
        dest?.writeString(icon)
        dest?.writeTypedObject(temp, flags)
        dest?.writeFloat(pressure)
        dest?.writeInt(humidity)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Forecast> = object : Parcelable.Creator<Forecast> {
            override fun createFromParcel(source: Parcel): Forecast = Forecast(source)

            override fun newArray(size: Int): Array<Forecast?> = arrayOfNulls(size)
        }
    }
}

data class Temperatures(val day: Float, val min: Float, val max: Float, val night: Float, val eve: Float, val morn: Float) : Parcelable {
    constructor(source: Parcel) : this(source.readFloat(), source.readFloat(), source.readFloat(), source.readFloat(), source.readFloat(), source.readFloat())

    override fun describeContents(): Int = 0

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeFloat(day)
        dest?.writeFloat(min)
        dest?.writeFloat(max)
        dest?.writeFloat(night)
        dest?.writeFloat(eve)
        dest?.writeFloat(morn)
    }

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<Temperatures> = object : Parcelable.Creator<Temperatures> {
            override fun createFromParcel(source: Parcel): Temperatures = Temperatures(source)

            override fun newArray(size: Int): Array<Temperatures?> = arrayOfNulls(size)
        }
    }
}
