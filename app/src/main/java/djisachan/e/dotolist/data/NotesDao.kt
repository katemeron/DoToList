package djisachan.e.dotolist.data

import androidx.room.*
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@Dao
interface NotesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(note: DataNote): Completable

    @Query("SELECT * FROM datanote")
    fun getAll(): Single<List<DataNote>>

    @Query("SELECT * FROM datanote WHERE noteUuid = :id")
    fun getNoteById(id: String): Single<DataNote>

    @Query("DELETE FROM datanote WHERE noteUuid = :id")
    fun delete(id: String): Completable
}