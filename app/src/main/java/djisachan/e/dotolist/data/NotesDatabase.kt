package djisachan.e.dotolist.data

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@Database(entities = [DataNote::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {

    abstract fun getNotesDao(): NotesDao
}