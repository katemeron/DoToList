package djisachan.e.dotolist.domain

import djisachan.e.dotolist.models.domain.Note
import io.reactivex.Completable

/**
 * @author Markova Ekaterina on 20-Nov-22
 */
interface NoteDetailsRepository {

    fun saveNote(currentNote: Note): Completable

    fun deleteNote(id: String): Completable
}