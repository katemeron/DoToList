package djisachan.e.dotolist.ui.details

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
@InjectViewState
class NoteDetailsPresenter : MvpPresenter<NoteDetailsView>() {

    fun loadList(){
        viewState.saveNote()
    }
}