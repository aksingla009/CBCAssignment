package com.aman.cbcassignment.db

import androidx.room.TypeConverter
import com.aman.cbcassignment.model.Images

class ImageTypeConverter {

    @TypeConverter
    fun fromImageToString(image: Images) : String{
        return image.square_140
    }

    @TypeConverter
    fun fromStringToImage(string: String) : Images{
        return Images(string)
    }
}