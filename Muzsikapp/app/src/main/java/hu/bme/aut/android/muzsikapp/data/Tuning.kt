package hu.bme.aut.android.muzsikapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter

@Entity(tableName = "tuning")
data class Tuning(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long? = null,
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "strings") var strings: String,
    @ColumnInfo(name = "category") var category: Category
) {
    enum class Category {
        Guitar, Bass, Piano;
        companion object {
            @JvmStatic
            @TypeConverter
            fun getByOrdinal(ordinal: Int): Category? {
                var ret: Category? = null
                for (cat in values()) {
                    if (cat.ordinal == ordinal) {
                        ret = cat
                        break
                    }
                }
                return ret
            }

            @JvmStatic
            @TypeConverter
            fun toInt(category: Category): Int {
                return category.ordinal
            }
        }
    }
}
