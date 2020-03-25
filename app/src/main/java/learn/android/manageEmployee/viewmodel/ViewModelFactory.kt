package learn.android.manageEmployee.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Aswathy on 3/25/2020.
 */
 class ViewModelFactory <T: ViewModel>(private val viewModelInstance: T) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return viewModelInstance as T
    }
}