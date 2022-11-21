package djisachan.e.dotolist.models.domain

import djisachan.e.dotolist.data.DataNote

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
data class Note(
    val id: String,
    val text: String,
    val done: Boolean = false
)

fun Note.toData() = DataNote(
    noteUuid = this.id,
    text = this.text,
    status = this.done
)

fun DataNote.toDomain() = Note(
    id = this.noteUuid,
    text = this.text,
    done = this.status
)