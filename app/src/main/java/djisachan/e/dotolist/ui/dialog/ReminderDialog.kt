package djisachan.e.dotolist.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Window
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ToDoNotesApp
import djisachan.e.dotolist.models.domain.Note
import djisachan.e.dotolist.ui.dialog.MyBroadcastReceiver.Companion.ID_KEY
import djisachan.e.dotolist.ui.dialog.MyBroadcastReceiver.Companion.MESSAGE_KEY
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers


/**
 * @author Markova Ekaterina on 22-Nov-22
 */
class ReminderDialog : Activity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_alert_dialog)

        val id = intent.getStringExtra(ID_KEY)
        val message = intent.getStringExtra(MESSAGE_KEY)
        if (id != null && message != null && message.isNotEmpty()) {
            val alertDialog = AlertDialog.Builder(this)
                .setTitle(R.string.reminder)
                .setMessage(if (message.length > MAX_LENGTH) message.substring(0, MAX_LENGTH) else message)
                .setPositiveButton(R.string.close_note) { d, arg1 ->
                    d.dismiss()
                    updateNote(id, message)
                }
                .create()
            alertDialog.show()
        } else {
            finish()
        }
    }

    private fun updateNote(id: String, message: String) {
        val appComponent = (application as ToDoNotesApp).appComponent
        appComponent.inject(this)
        val repository = appComponent.noteDetailsRepository
        compositeDisposable.add(
            repository
                .saveNote(
                    Note(
                        id = id,
                        text = message,
                        done = true,
                        notification = false
                    )
                )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    finish()
                }, { throwable ->
                    Log.e("ToDoListPresenter", throwable.toString())
                })
        )
    }

    companion object {
        private const val MAX_LENGTH = 20
    }
}