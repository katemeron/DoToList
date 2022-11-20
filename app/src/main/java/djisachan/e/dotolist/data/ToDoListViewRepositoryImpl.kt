package djisachan.e.dotolist.data

import djisachan.e.dotolist.domain.ToDoListViewRepository
import djisachan.e.dotolist.models.domain.Note
import io.reactivex.Single

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class ToDoListViewRepositoryImpl(
    private val notesDatabase: NotesDatabase
) : ToDoListViewRepository {

    override fun loadNotes(): Single<List<Note>> {
        return notesDatabase.getNotesDao().getAll().map { list -> list.map { item -> item.toDomain() } }
    }

    private fun DataNote.toDomain() = Note(
        id = this.noteUuid,
        text = this.text,
        done = this.status
    )
}