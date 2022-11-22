package djisachan.e.dotolist.ui.dialog

import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.view.Window
import djisachan.e.dotolist.R
import djisachan.e.dotolist.ui.dialog.MyBroadcastReceiver.Companion.MESSAGE_KEY

/**
 * @author Markova Ekaterina on 22-Nov-22
 */
class ReminderDialog : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_alert_dialog)

        val alertDialog = AlertDialog.Builder(this)
            .setTitle(R.string.reminder)
            .setMessage(intent.getStringExtra(MESSAGE_KEY))
            .setPositiveButton(R.string.close_note) { d, arg1 ->
                d.dismiss()
                finish()
            }
            .create()
        alertDialog.show()
    }
}