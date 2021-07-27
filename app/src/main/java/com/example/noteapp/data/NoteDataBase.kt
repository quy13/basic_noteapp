package com.example.noteapp.data

import android.content.Context
import androidx.room.*
import com.example.noteapp.model.Note

@Database(entities = [Note::class],version = 1,exportSchema = false)
abstract class NoteDataBase : RoomDatabase(){

    abstract fun noteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDataBase? = null

        fun getDatabase(context: Context):NoteDataBase{
            //  check if Instance exist if <YES>[then return it back] <NO>[then create a new Instance]
            // :Caution: Always have just 1 Instance of Room Database (It affect Performance)
            val tempInstance = INSTANCE
            if(tempInstance != null) {
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDataBase::class.java,
                    "note_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }


}