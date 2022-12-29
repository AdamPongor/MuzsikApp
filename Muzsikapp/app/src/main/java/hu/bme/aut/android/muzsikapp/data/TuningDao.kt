package hu.bme.aut.android.muzsikapp.data

import androidx.room.*

@Dao
interface TuningDao {
    @Query("SELECT * FROM tuning")
    fun getAll(): List<Tuning>

    @Insert
    fun insert(tunings: Tuning): Long

    @Update
    fun update(tuning: Tuning)

    @Delete
    fun deleteItem(tuning: Tuning)
}