package bawv.app.runtracker.ui.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import bawv.app.runtracker.db.Run
import bawv.app.runtracker.others.SortType
import bawv.app.runtracker.repositories.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val mainRepository: MainRepository
) : ViewModel() {


    private val runSortedByDate = mainRepository.getAllRunSortedByDate()
    private val runSortedByDistance = mainRepository.getAllRunSortedByDistance()
    private val runSortedByCaloriesBurned = mainRepository.getAlRunsSortedByCaloriesBurned()
    private val runSortedByTimeInMillis = mainRepository.getAlRunsSortedByTimeInMillis()
    private val runRunsSortedByAvgSpeed = mainRepository.getAlRunsSortedByAvgSpeed()

    val runs = MediatorLiveData<List<Run>>()

    var sortType = SortType.DATE

    init {
        runs.addSource(runSortedByDate) { result ->
            if (sortType == SortType.DATE) {
                result?.let { runs.value = it }
            }

        }
        runs.addSource(runSortedByCaloriesBurned) { result ->
            if (sortType == SortType.CALORIES_BURNED) {
                result?.let { runs.value = it }
            }

        }
        runs.addSource(runSortedByDistance) { result ->
            if (sortType == SortType.DISTANCE) {
                result?.let { runs.value = it }
            }

        }
        runs.addSource(runSortedByTimeInMillis) { result ->
            if (sortType == SortType.RUNNING_TIME) {
                result?.let { runs.value = it }
            }

        }
        runs.addSource(runRunsSortedByAvgSpeed) { result ->
            if (sortType == SortType.AVG_SPEED) {
                result?.let { runs.value = it }
            }

        }


    }


    fun sortRuns(sortType: SortType) = when(sortType){
        SortType.DATE -> runSortedByDate.value?.let { runs.value = it }
        SortType.RUNNING_TIME -> runSortedByTimeInMillis.value?.let { runs.value = it }
        SortType.DISTANCE -> runSortedByDistance.value?.let { runs.value = it }
        SortType.AVG_SPEED -> runRunsSortedByAvgSpeed.value?.let { runs.value = it }
        SortType.CALORIES_BURNED -> runSortedByCaloriesBurned.value?.let { runs.value = it }
    }.also {
        this.sortType = sortType
    }



    fun insertRun(run: Run) = viewModelScope.launch {
        mainRepository.insertRun(run)
    }
    fun deleteRun(run:Run) =viewModelScope.launch {
        mainRepository.deleteRun(run)
    }
}