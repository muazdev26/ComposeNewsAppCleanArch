package com.muazdev26.composenewsapp.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.muazdev26.composenewsapp.domain.models.Source

@ProvidedTypeConverter
class SourceTypeConvertor {
    @TypeConverter
    fun sourceToString(source: Source): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun sourceStringToSource(string: String): Source {
        return string.split(",").let {
            Source(it[0], it[1])
        }
    }
}