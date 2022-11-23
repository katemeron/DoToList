package djisachan.e.dotolist

import android.app.Application
import djisachan.e.dotolist.di.DaggerToDoNotesComponent
import djisachan.e.dotolist.di.ToDoNotesComponent
import djisachan.e.dotolist.di.ToDoNotesModule


/**
 * @author Markova Ekaterina on 09-Aug-20
 */

class ToDoNotesApp : Application() {

    lateinit var appComponent: ToDoNotesComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerToDoNotesComponent.builder()
            .toDoNotesModule(ToDoNotesModule(this))
            .build()
    }
}
