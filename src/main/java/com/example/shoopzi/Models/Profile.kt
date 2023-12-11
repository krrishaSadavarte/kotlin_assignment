package com.example.shoopzi.Models

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "profile_table")
data class Profile(
    @PrimaryKey(autoGenerate = true)
    var U_id:Int? = null,
    @ColumnInfo(name = "NAME")
    var U_name:String,
    @ColumnInfo(name = "ABOUT YOURSELF")
    var U_bio:String?,
    var U_img:String = "",
    var createAt:Long = System.currentTimeMillis()
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readValue(Int::class.java.classLoader) as? Int,
        parcel.readString()!!,
        parcel.readString(),
        parcel.readString()!!,
        parcel.readLong()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(U_id)
        parcel.writeString(U_name)
        parcel.writeString(U_bio)
        parcel.writeString(U_img)
        parcel.writeLong(createAt)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Profile> {
        override fun createFromParcel(parcel: Parcel): Profile {
            return Profile(parcel)
        }

        override fun newArray(size: Int): Array<Profile?> {
            return arrayOfNulls(size)
        }
    }

}
