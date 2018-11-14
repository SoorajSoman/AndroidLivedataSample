package com.project.androidlivedatasample.model.converters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

/**
 * @author Sooraj Soman on 11/14/2018
 */
public class DateConverter {
    @TypeConverter
    public static Date toDate(Long timestamp) {
        return timestamp == null ? null : new Date(timestamp);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }
}