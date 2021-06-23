package bawv.app.runtracker.db

import androidx.room.*
import dagger.Provides

@Database(
    entities = [Run::class],
    version = 1
)
@TypeConverters(Convertors::class)
abstract class RunningDatabase : RoomDatabase() {
    abstract fun getRunDao(): RunDAO
}