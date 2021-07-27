package com.example.noteapp.model

import android.os.Parcelable
import androidx.room.*
import kotlinx.android.parcel.Parcelize

@kotlinx.parcelize.Parcelize
@Entity(tableName = "note_data")
data class Note (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title:String,
    val body:String
//    val title: String,
//    val body: String
): Parcelable