package djisachan.e.dotolist.ui.list.viewholders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import djisachan.e.dotolist.R
import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class OpenCloseItemViewHolder(itemView: View) : ToDoItemViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.text_view)
    private val icon: ImageView = itemView.findViewById(R.id.icon_view)

    override fun bind(item: Item) {
        item as Item.OpenCloseItem
        textView.text = itemView.context.getString(item.text)
        val res = itemView.context.getDrawable(item.drawable)
        icon.setImageDrawable(res)
        itemView.setOnClickListener(item.clickListener)
    }
}