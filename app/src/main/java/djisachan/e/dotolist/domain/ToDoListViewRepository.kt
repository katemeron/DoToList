package djisachan.e.dotolist.domain

import djisachan.e.dotolist.data.DataNote
import djisachan.e.dotolist.models.domain.Note
import io.reactivex.Single

/**
 * @author Markova Ekaterina on 20-Nov-22
 */
interface ToDoListViewRepository {

    fun loadNotes(): Single<List<Note>>
}