package djisachan.e.dotolist.ui.list

import android.os.Handler
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import djisachan.e.dotolist.R
import djisachan.e.dotolist.domain.ToDoListViewRepository
import djisachan.e.dotolist.models.domain.Note
import djisachan.e.dotolist.models.ui.Item
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@InjectViewState
class ToDoListPresenter @Inject constructor(private val toDoListViewRepository: ToDoListViewRepository) : MvpPresenter<ToDoListView>() {

    private val compositeDisposable = CompositeDisposable()

    private var fullList: Boolean = false
    private var noteList: MutableList<Note> = mutableListOf()

    fun loadList() {
        viewState.showProgress()
        compositeDisposable.add(
            toDoListViewRepository.loadNotes().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({ result ->
                noteList = result.sortedBy { it.id }.toMutableList()
                Handler().postDelayed({
                    viewState.hideProgress()
                    viewState.showList(createList())
                }, 800)
            }, { throwable ->
                Log.e("ToDoListPresenter", throwable.toString())
            })
        )
    }

    private fun updateNote(index: Int, note: Note) {
        compositeDisposable.add(
            toDoListViewRepository.updateNote(note).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe({
                viewState.removeItem(index)
                Handler().postDelayed({
                    val currentIndex = noteList.indexOfFirst { it.id == note.id }
                    noteList[currentIndex] = note
                    viewState.showList(createList())
                }, 300)
            }, { throwable ->
                Log.e("ToDoListPresenter", throwable.toString())
            })
        )
    }

    private fun createList(): List<Item> {
        val count = noteList.count { it.done }
        return if (count > 0) {
            val finalList: MutableList<Item> = noteList.filter { !it.done }.mapIndexed { index, note -> note.toUI(index) }.toMutableList()
            if (fullList) {
                finalList.add(Item.OpenCloseItem(
                    R.string.hide_done, R.drawable.ic_baseline_keyboard_arrow_up_24
                ) {
                    fullList = false
                    viewState.showList(createList())
                })
                finalList.addAll(noteList.filter { it.done }.mapIndexed { index, note -> note.toUI(index) })
            } else {
                finalList.add(Item.OpenCloseItem(
                    R.string.show_done, R.drawable.ic_baseline_keyboard_arrow_down_24
                ) {
                    fullList = true
                    viewState.showList(createList())
                })
            }
            finalList
        } else {
            noteList.mapIndexed { index, note -> note.toUI(index) }
        }
    }

    private fun Note.toUI(index: Int) = Item.NoteItem(
        text = text,
        checked = done,
        checkedListener = {
            updateNote(index, Note(id, text, !done, notification))
        },
        noteClickListener = {
            viewState.editNote(id, text, notification)
        })
}