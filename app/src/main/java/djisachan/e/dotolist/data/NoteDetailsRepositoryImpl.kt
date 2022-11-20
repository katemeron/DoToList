package djisachan.e.dotolist.data

import djisachan.e.dotolist.data.DataNote
import djisachan.e.dotolist.data.NotesDatabase
import djisachan.e.dotolist.domain.NoteDetailsRepository
import djisachan.e.dotolist.models.domain.Note
import djisachan.e.dotolist.models.domain.toData
import io.reactivex.Completable

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsRepositoryImpl(
    private val notesDatabase: NotesDatabase
) : NoteDetailsRepository {
    override fun saveNote(currentNote: Note) : Completable {
       return notesDatabase.getNotesDao().insert(currentNote.toData())
    }

    override fun deleteNote(id: String) {
        notesDatabase.getNotesDao().delete(id)
    }

}