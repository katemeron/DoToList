package djisachan.e.dotolist.ui.list

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import djisachan.e.dotolist.R
import djisachan.e.dotolist.models.domain.Note
import djisachan.e.dotolist.models.ui.Item


/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@InjectViewState
class ToDoListPresenter : MvpPresenter<ToDoListView>() {

    private var fullList: Boolean = false
    private var noteList: List<Note> = listOf(
        Note(id = "1", text = "Первая"),
        Note(id = "2", text = "Вторая"),
        Note(id = "3", text = "Третья", done = true)
    )

    fun loadList() {
        viewState.showList(createList())
    }

    private fun createList(): List<Item> {
        val count = noteList.count { it.done }
        return if (count > 0) {
            val finalList: MutableList<Item> = noteList.filter { !it.done }.map { it.toUI() }.toMutableList()
            if (fullList) {
                finalList.add(
                    Item.OpenCloseItem(
                        R.string.hide_done,
                        R.drawable.ic_baseline_keyboard_arrow_up_24
                    ) {
                        fullList = false
                        viewState.showList(createList())
                    }
                )
                finalList.addAll(noteList.filter { it.done }.map { it.toUI() })
            } else {
                finalList.add(
                    Item.OpenCloseItem(
                        R.string.show_done,
                        R.drawable.ic_baseline_keyboard_arrow_down_24
                    ) {
                        fullList = true
                        viewState.showList(createList())
                    }
                )
            }
            finalList
        } else {
            noteList.map { it.toUI() }
        }
    }

    private fun Note.toUI() = Item.NoteItem(
        text = this.text,
        checked = this.done,
        checkedListener = {
            viewState.showToast("УРА")
        },
        noteClickListener = {}
    )
}