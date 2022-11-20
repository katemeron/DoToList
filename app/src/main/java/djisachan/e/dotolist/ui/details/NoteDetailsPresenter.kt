package djisachan.e.dotolist.ui.details

import android.os.SystemClock
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import djisachan.e.dotolist.domain.NoteDetailsInteractor
import djisachan.e.dotolist.models.domain.Note

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@InjectViewState
class NoteDetailsPresenter(
    private val noteDetailsInteractor: NoteDetailsInteractor
) : MvpPresenter<NoteDetailsView>() {

    var currentNote = Note(
        "",
        "",
        false
    )

    fun saveNote(text: String) {
        noteDetailsInteractor.saveNote(
            Note(
                id = currentNote.id.ifEmpty { SystemClock.uptimeMillis().toString() },
                text = text
            )

        )
    }
}