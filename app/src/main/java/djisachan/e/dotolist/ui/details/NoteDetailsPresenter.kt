package djisachan.e.dotolist.ui.details

import android.os.SystemClock
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import djisachan.e.dotolist.domain.NoteDetailsRepository
import djisachan.e.dotolist.models.domain.Note
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@InjectViewState
class NoteDetailsPresenter(
    private val noteDetailsRepository: NoteDetailsRepository
) : MvpPresenter<NoteDetailsView>() {

    private val compositeDisposable = CompositeDisposable()

    var currentId = ""

    fun saveNote(text: String) {
        compositeDisposable.add(
            noteDetailsRepository
                .saveNote(
                    Note(
                        id = currentId.ifEmpty {
                            SystemClock.uptimeMillis().toString()
                        },
                        text = text
                    )
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.backToList()
                }, { throwable ->
                    Log.e("ToDoListPresenter", throwable.toString())
                })
        )
        noteDetailsRepository.saveNote(
            Note(
                id = currentId.ifEmpty { SystemClock.uptimeMillis().toString() },
                text = text
            )

        )
    }

    fun deleteNote() {
        compositeDisposable.add(
            noteDetailsRepository
                .deleteNote(currentId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    viewState.backToList()
                }, { throwable ->
                    Log.e("ToDoListPresenter", throwable.toString())
                })
        )
    }
}