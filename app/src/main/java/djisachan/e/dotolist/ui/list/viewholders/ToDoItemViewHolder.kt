package djisachan.e.dotolist.ui.list.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
abstract class ToDoItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item:Item)
}