package djisachan.e.dotolist.ui.list

import com.arellomobile.mvp.MvpView
import djisachan.e.dotolist.models.ui.Item

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
interface ToDoListView : MvpView {

    fun showProgress()

    fun hideProgress()

    fun showList(list: List<Item>)

    fun editNote(id: String, text: String, notification: Boolean)

    fun removeItem(index: Int)

    fun showToast(string: String)
}