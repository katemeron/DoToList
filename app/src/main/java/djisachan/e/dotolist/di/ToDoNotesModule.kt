package djisachan.e.dotolist.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import djisachan.e.dotolist.data.NoteDetailsRepositoryImpl
import djisachan.e.dotolist.data.NotesDatabase
import djisachan.e.dotolist.data.ToDoListViewRepositoryImpl
import djisachan.e.dotolist.domain.NoteDetailsRepository
import djisachan.e.dotolist.domain.ToDoListViewRepository
import javax.inject.Singleton

/**
 * @author Markova Ekaterina on 20-Nov-22
 */
@Module
class ToDoNotesModule(private val application: Application) {

    @Provides
    @Singleton
    fun providesApplication(): Application = application

    @Provides
    @Singleton
    fun providesApplicationContext(): Context = application

    @Provides
    @Singleton
    fun provideNotesDatabase(context: Context): NotesDatabase {
        return Room.databaseBuilder(
            context,
            NotesDatabase::class.java,
            "history_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideToDoListViewRepository(notesDatabase: NotesDatabase): ToDoListViewRepository {
        return ToDoListViewRepositoryImpl(notesDatabase)
    }

    @Provides
    @Singleton
    fun provideNoteDetailsRepository(notesDatabase: NotesDatabase): NoteDetailsRepository {
        return NoteDetailsRepositoryImpl(notesDatabase)
    }
}