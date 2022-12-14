package djisachan.e.dotolist.domain

import djisachan.e.dotolist.models.domain.Note
import io.reactivex.Completable
import io.reactivex.Single

/**
 * @author Markova Ekaterina on 20-Nov-22
 */
interface ToDoListViewRepository {

    fun loadNotes(): Single<List<Note>>

    fun updateNote(note: Note): Completable
}