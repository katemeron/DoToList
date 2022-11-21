package djisachan.e.dotolist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ui.list.viewholders.NoteItemViewHolder
import djisachan.e.dotolist.ui.list.viewholders.OpenCloseItemViewHolder
import djisachan.e.dotolist.ui.list.viewholders.ToDoItemViewHolder

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class ToDoListViewHolderFactory {

    fun create(parent: ViewGroup, inflater: LayoutInflater, type: Int): ToDoItemViewHolder {
        return when (type) {
            NoteItemViewHolder.TYPE -> NoteItemViewHolder(inflater.inflate(R.layout.note_list_item, parent, false))
            OpenCloseItemViewHolder.TYPE -> OpenCloseItemViewHolder(inflater.inflate(R.layout.open_close_item_layout, parent, false))
            else -> throw throw IllegalArgumentException("Некорректный тип запуска!")
        }
    }
}