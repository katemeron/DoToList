package djisachan.e.dotolist.ui.details

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.arellomobile.mvp.MvpAppCompatFragment
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import djisachan.e.dotolist.MainActivity
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ToDoNotesApp
import djisachan.e.dotolist.databinding.NoteDetailsLayoutBinding
import djisachan.e.dotolist.ui.dialog.MyBroadcastReceiver

/**
 * @author Markova Ekaterina on 19-Nov-22
 */
class NoteDetailsFragment : MvpAppCompatFragment(), NoteDetailsView {

    private lateinit var binding: NoteDetailsLayoutBinding

    @InjectPresenter
    lateinit var presenter: NoteDetailsPresenter

    var alarmPendingIntent: PendingIntent? = null

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
        presenter.currentId = arguments?.getString(NOTE_ID_KEY) ?: ""
        presenter.currentNotification = arguments?.getBoolean(NOTIFICATION_KEY) ?: false
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        val noteText = arguments?.getString(NOTE_TEXT_KEY) ?: ""
        if (noteText.isNotEmpty()) {
            inflater.inflate(R.menu.edit_note_menu, menu)
        } else {
            inflater.inflate(R.menu.new_note_menu, menu)
        }
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                backToList()
                true
            }
            R.id.action_delete -> {
                presenter.deleteNote()
                true
            }
            R.id.action_notification_on -> {
                val invert = !presenter.currentNotification
                presenter.currentNotification = invert
                requireActivity().invalidateOptionsMenu()
                cancelAlarm()
                true
            }
            R.id.action_notification_off -> {
                val invert = !presenter.currentNotification
                presenter.currentNotification = invert
                requireActivity().invalidateOptionsMenu()
                setAlarm(binding.noteEditView.editableText.toString())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        if (presenter.currentNotification) {
            menu.findItem(R.id.action_notification_on).isVisible = true
            menu.findItem(R.id.action_notification_off).isVisible = false
        } else {
            menu.findItem(R.id.action_notification_on).isVisible = false
            menu.findItem(R.id.action_notification_off).isVisible = true
        }
    }

    private fun initView() {
        with(requireActivity() as AppCompatActivity) {
            setSupportActionBar(binding.toolbar)
            val noteText = arguments?.getString(NOTE_TEXT_KEY) ?: ""
            if (noteText.isNotEmpty()) {
                binding.noteEditView.setText(noteText)
                supportActionBar?.title = getString(R.string.note)
            } else {
                supportActionBar?.title = getString(R.string.new_note)
            }
            setHasOptionsMenu(true)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        binding.fab.setOnClickListener {
            presenter.saveNote(binding.noteEditView.editableText.toString())

        }
    }

    override fun showNote(text: String) {
        binding.noteEditView.setText(text)
    }

    override fun backToList() {
        findNavController().navigate(R.id.action_Details_to_List)
    }

    override fun showToast(textRes: Int) {
        Toast.makeText(
            activity as MainActivity,
            getString(textRes),
            Toast.LENGTH_SHORT
        ).show()
    }

    override fun setAlarm(text: String) {
        val intent = Intent(requireActivity(), MyBroadcastReceiver::class.java)
        intent.putExtra(MyBroadcastReceiver.MESSAGE_KEY, text)
        alarmPendingIntent = PendingIntent.getBroadcast(
            requireActivity().applicationContext, 234, intent, 0
        )
        ContextCompat.getSystemService(requireActivity(), AlarmManager::class.java)?.set(
            AlarmManager.RTC_WAKEUP,
            System.currentTimeMillis() + 3000,
            alarmPendingIntent
        )
    }

    private fun cancelAlarm() {
        alarmPendingIntent?.let {
            it.cancel()
            ContextCompat.getSystemService(requireActivity(), AlarmManager::class.java)?.cancel(it)
        }
    }

    companion object {
        private const val NOTE_ID_KEY = "noteId"
        private const val NOTE_TEXT_KEY = "noteText"
        private const val NOTIFICATION_KEY = "notificationKey"
    }

}