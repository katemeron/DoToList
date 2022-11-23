package djisachan.e.dotolist.ui.dialog

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent


/**
 * @author Markova Ekaterina on 22-Nov-22
 */
class MyBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val dialogIntent = Intent(context.applicationContext, ReminderDialog::class.java)
        dialogIntent.putExtra(MESSAGE_KEY, intent.getStringExtra(MESSAGE_KEY))
        dialogIntent.putExtra(ID_KEY, intent.getStringExtra(ID_KEY))
        dialogIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(dialogIntent)
    }

    companion object {
        const val ID_KEY = "id-key"
        const val MESSAGE_KEY = "message-key"
    }
}