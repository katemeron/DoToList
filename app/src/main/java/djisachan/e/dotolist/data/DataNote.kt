package djisachan.e.dotolist.data

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@Entity
data class DataNote(
    @PrimaryKey
    var noteUuid: String,
    var text: String,
    var status: Boolean,
    var notification: Boolean
)