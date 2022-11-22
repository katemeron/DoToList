package djisachan.e.dotolist.ui.details

import com.arellomobile.mvp.MvpView

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
interface NoteDetailsView : MvpView {

    fun showNote(text: String)

    fun backToList()

    fun showToast(textRes: Int)

    fun setAlarm(text: String)
}