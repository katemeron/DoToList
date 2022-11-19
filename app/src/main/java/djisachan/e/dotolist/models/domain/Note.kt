package djisachan.e.dotolist.models.domain

import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
data class Note(
    val id: String,
    val text: String,
    val done: Boolean = false
)