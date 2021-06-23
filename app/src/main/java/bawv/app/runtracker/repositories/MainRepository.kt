package bawv.app.runtracker.repositories

import bawv.app.runtracker.db.Run
import bawv.app.runtracker.db.RunDAO
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDAO: RunDAO
) {
    suspend fun insertRun(run : Run) = runDAO.insertRun(run)
    suspend fun deleteRun(run : Run) = runDAO.deleteRun(run)

    fun getAllRunSortedByDate() = runDAO.getAlRunsSortedByDate()

    fun getAllRunSortedByDistance() = runDAO.getAlRunsSortedByDistance()

    fun getAlRunsSortedByTimeInMillis() = runDAO.getAlRunsSortedByTimeInMillis()

    fun getAlRunsSortedByAvgSpeed() = runDAO.getAlRunsSortedByAvgSpeed()

    fun getAlRunsSortedByCaloriesBurned() = runDAO.getAlRunsSortedByCaloriesBurned()

    fun getTotalAvgSpeed() = runDAO.getTotalAvgSpeed()

    fun getTotalDistance() = runDAO.getTotalDistance()

    fun getTotalTimeInMillis() = runDAO.getTotalTimeInMillis()

    fun getTotalCaloriesBurned() = runDAO.getTotalCaloriesBurned()
}