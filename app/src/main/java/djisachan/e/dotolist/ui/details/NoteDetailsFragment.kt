package djisachan.e.dotolist.ui.details

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ToDoNotesApp
import djisachan.e.dotolist.databinding.NoteDetailsLayoutBinding

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsFragment : MvpAppCompatFragment(), NoteDetailsView {

    private lateinit var binding: NoteDetailsLayoutBinding

    @InjectPresenter
    lateinit var presenter: NoteDetailsPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): NoteDetailsPresenter {
        val appComponent = (activity?.application as ToDoNotesApp).appComponent
        appComponent.inject(this)
        return NoteDetailsPresenter(appComponent.noteDetailsRepository)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = NoteDetailsLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.edit_note_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                activity?.onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun initView() {
        with(requireActivity() as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            supportActionBar?.title = getString(R.string.new_note)
            setHasOptionsMenu(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.fab.setOnClickListener {
            presenter.saveNote(binding.noteEditView.editableText.toString())

        }
    }

    override fun backToList() {
        findNavController().navigate(R.id.action_Details_to_List)
    }

}