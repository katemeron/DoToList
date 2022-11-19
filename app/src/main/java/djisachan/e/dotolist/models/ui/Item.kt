package djisachan.e.dotolist.models.ui

import android.view.View.OnClickListener

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
sealed class Item {

    data class NoteItem(
        val text: String,
        val checked: Boolean = false,
        val checkedListener: OnClickListener,
        val noteClickListener: OnClickListener? = null
    ) : Item()

    data class OpenCloseItem(
        val text: Int,
        val drawable: Int,
        val clickListener: OnClickListener
    ) : Item()
}