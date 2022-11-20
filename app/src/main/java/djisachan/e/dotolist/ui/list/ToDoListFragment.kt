package djisachan.e.dotolist.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import djisachan.e.dotolist.MainActivity
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ToDoNotesApp
import djisachan.e.dotolist.databinding.ToDoFragmentLayoutBinding
import djisachan.e.dotolist.di.ToDoNotesComponent
import djisachan.e.dotolist.models.ui.Item


/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class ToDoListFragment : MvpAppCompatFragment(), ToDoListView {

    private lateinit var appComponent: ToDoNotesComponent
    private lateinit var binding: ToDoFragmentLayoutBinding
    private val adapter = ToDoListAdapter(ToDoListViewHolderFactory())

    @InjectPresenter
    lateinit var presenter: ToDoListPresenter

    @ProvidePresenter
    fun provideDetailsPresenter(): ToDoListPresenter {
        appComponent = (activity?.application as ToDoNotesApp).appComponent
        appComponent.inject(this)
        return ToDoListPresenter(appComponent.toDoListViewRepository)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = ToDoFragmentLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initView()
        presenter.loadList()
    }


    private fun initView() {
        (activity as MainActivity).setSupportActionBar(binding.toolbar)
        (activity as MainActivity).supportActionBar?.title = getString(R.string.notes_list)
        binding.fab.setOnClickListener {
            findNavController().navigate(R.id.action_List_to_Details)
        }
        binding.recyclerView.adapter = adapter
    }

    override fun showList(list: List<Item>) {
        if (list.isNotEmpty()) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.centeredWarning.visibility = View.GONE
            adapter.list = list
            adapter.notifyDataSetChanged()
        } else {
            binding.recyclerView.visibility = View.GONE
            binding.centeredWarning.visibility = View.VISIBLE
        }
    }

    override fun showToast(string: String) {
        Toast.makeText(
            activity as MainActivity,
            string,
            Toast.LENGTH_SHORT
        ).show()
    }
}