package djisachan.e.dotolist.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import djisachan.e.dotolist.R
import djisachan.e.dotolist.databinding.NoteDetailsLayoutBinding

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsFragment : MvpAppCompatFragment(), NoteDetailsView {

    private lateinit var binding: NoteDetailsLayoutBinding

    @InjectPresenter
    lateinit var presenter: NoteDetailsPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NoteDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }


    private fun initView() {
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_Details_to_List)
        }
    }

    override fun saveNote() {
        //to do
    }

}