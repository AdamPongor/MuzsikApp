package hu.bme.aut.android.muzsikapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Tuning::class], version = 1)
@TypeConverters(value = [Tuning.Category::class])
abstract class TuningDatabase : RoomDatabase() {
    abstract fun tuningDao(): TuningDao

    companion object {
        fun getDatabase(applicationContext: Context): TuningDatabase {
            return Room.databaseBuilder(
                applicationContext,
                TuningDatabase::class.java,
                "tuning"
            ).build();
        }
    }
}