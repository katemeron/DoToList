package djisachan.e.dotolist.ui.list.viewholders

import android.graphics.Paint
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import djisachan.e.dotolist.R
import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteItemViewHolder(itemView: View) : ToDoItemViewHolder(itemView) {

    private val titleTextView: TextView = itemView.findViewById(R.id.note_title_textview)
    private val checkBox: CheckBox = itemView.findViewById(R.id.note_status_checkbox)

    override fun bind(item: Item) {
        item as Item.NoteItem
        checkBox.isChecked = item.checked
        if (item.checked) {
            titleTextView.paintFlags = titleTextView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
        titleTextView.text = item.text
        checkBox.setOnClickListener(item.checkedListener)
        titleTextView.setOnClickListener(item.noteClickListener)
    }
}