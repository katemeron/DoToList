package djisachan.e.dotolist.domain

import djisachan.e.dotolist.data.DataNote
import djisachan.e.dotolist.data.NotesDatabase
import djisachan.e.dotolist.models.domain.Note

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsInteractor(
    private val notesDatabase: NotesDatabase
) {
    fun saveNote(currentNote: Note) {
        notesDatabase.getNotesDao().insert(currentNote.toData())
    }

    fun deleteNote(id: String) {
        notesDatabase.getNotesDao().delete(id)
    }

    private fun Note.toData() = DataNote(
        noteUuid = this.id,
        text = this.text,
        status = this.done
    )

}