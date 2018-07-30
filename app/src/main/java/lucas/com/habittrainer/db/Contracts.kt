package lucas.com.habittrainer.db

import android.provider.BaseColumns

val DATABASE_NAME = "habittrainer.db"
val DATABASE_VERSION = 10

object HabitEntry : BaseColumns {
    val ID_COL = "id"
    val TABLE_NAME = "habit"
    val TITLE_COL = "title"
    val DESC_COL = "description"
    val IMAGE_COL = "image"

}

