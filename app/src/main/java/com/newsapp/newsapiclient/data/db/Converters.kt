package com.newsapp.newsapiclient.data.db

import androidx.room.TypeConverter
import com.newsapp.newsapiclient.data.model.Source

class Converters {
    @TypeConverter
    fun fromSourcetosourcename(source: Source):String{
           return source.name
    }
    @TypeConverter
    fun fromsourcenametoSource(name:String):Source{
        return Source(name,name)
    }
}
