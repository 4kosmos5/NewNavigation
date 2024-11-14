package com.fabirt.roka.core.presentation.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fabirt.roka.core.data.providers.DataStoreProvider
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DataStoreViewModel @Inject constructor(
    private val dataStoreProvider: DataStoreProvider
) : ViewModel() {

    suspend fun readOnboardingDidShow() = dataStoreProvider.readOnboardingDidShow()

    fun writeOnboardingDidShow() {
        viewModelScope.launch {
            dataStoreProvider.writeOnboardingDidShow()
        }
    }
}