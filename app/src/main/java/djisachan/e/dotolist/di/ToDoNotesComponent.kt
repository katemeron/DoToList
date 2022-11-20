package djisachan.e.dotolist.di

import android.app.Application
import dagger.Component
import djisachan.e.dotolist.data.NotesDatabase
import djisachan.e.dotolist.domain.NoteDetailsRepository
import djisachan.e.dotolist.domain.ToDoListViewRepository
import djisachan.e.dotolist.ui.details.NoteDetailsFragment
import djisachan.e.dotolist.ui.list.ToDoListFragment
import djisachan.e.dotolist.ui.list.ToDoListPresenter
import javax.inject.Singleton

/**
 * @author Markova Ekaterina on 20-Nov-22
 */
@Singleton
@Component(modules = [ToDoNotesModule::class])
interface ToDoNotesComponent {

    val application: Application

    val notesDatabase: NotesDatabase

    val toDoListViewRepository: ToDoListViewRepository

    val noteDetailsRepository: NoteDetailsRepository

    val toDoListPresenter: ToDoListPresenter

    fun inject(toDoListFragment: ToDoListFragment)

    fun inject(noteDetailsFragment: NoteDetailsFragment)
}