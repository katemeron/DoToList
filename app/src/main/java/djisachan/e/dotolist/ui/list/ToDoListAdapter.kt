package djisachan.e.dotolist.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import djisachan.e.dotolist.models.ui.Item
import djisachan.e.dotolist.models.ui.Item.NoteItem
import djisachan.e.dotolist.ui.list.viewholders.NoteItemViewHolder
import djisachan.e.dotolist.ui.list.viewholders.OpenCloseItemViewHolder
import djisachan.e.dotolist.ui.list.viewholders.ToDoItemViewHolder

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class ToDoListAdapter(private val viewHolderFactory: ToDoListViewHolderFactory) : RecyclerView.Adapter<ToDoItemViewHolder>() {

    var list: MutableList<Item> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoItemViewHolder {
        return viewHolderFactory.create(
            parent,
            LayoutInflater.from(parent.context),
            viewType
        )
    }

    override fun onBindViewHolder(holder: ToDoItemViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount() = list.size

    override fun getItemViewType(position: Int) = when (list[position]) {
        is NoteItem -> NoteItemViewHolder.TYPE
        is Item.OpenCloseItem -> OpenCloseItemViewHolder.TYPE
    }
}