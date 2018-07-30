package lucas.com.habittrainer.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.graphics.Bitmap
import lucas.com.habittrainer.Habit
import java.io.ByteArrayOutputStream

class HabitDbTable(context: Context) {

    private val dbHelper = HabitTrainerDb(context)

    fun store(habit: Habit): Long {
        val db = dbHelper.writableDatabase

        val values = ContentValues()
        with(values) {
            put(HabitEntry.TITLE_COL, habit.title)
            put(HabitEntry.DESC_COL, habit.desc)
            put(HabitEntry.IMAGE_COL, toByteArray(habit.image))
        }

        val id = db.transaction {
            insert(HabitEntry.TABLE_NAME, null, values)
        }

        return id
    }

    fun toByteArray(bitmap: Bitmap): ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream)
        return stream.toByteArray()
    }
}

private inline fun <T> SQLiteDatabase.transaction(function: SQLiteDatabase.() -> T): T {
    beginTransaction()
    val value = try {
        val returnValue = function()
        setTransactionSuccessful()

        returnValue
    } finally {
        endTransaction()
    }

    close()

    return value
}