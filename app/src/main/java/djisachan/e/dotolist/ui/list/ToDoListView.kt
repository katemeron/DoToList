package djisachan.e.dotolist.ui.list

import com.arellomobile.mvp.MvpView
import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
interface ToDoListView : MvpView {

    fun showList(list: List<Item>)

    fun showToast(string: String)
}