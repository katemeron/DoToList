package djisachan.e.dotolist.data

import djisachan.e.dotolist.domain.NoteDetailsRepository
import djisachan.e.dotolist.models.domain.Note
import djisachan.e.dotolist.models.domain.toData
import djisachan.e.dotolist.models.domain.toDomain
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsRepositoryImpl(
    private val notesDatabase: NotesDatabase
) : NoteDetailsRepository {
    override fun loadNoteById(id: String): Single<Note> {
        return notesDatabase.getNotesDao().getNoteById(id).map { it.toDomain() }
    }

    override fun saveNote(currentNote: Note): Completable {
        return notesDatabase.getNotesDao().insert(currentNote.toData())
    }

    override fun deleteNote(id: String): Completable {
        return notesDatabase.getNotesDao().delete(id)
    }

}