package com.fabirt.roka.features.favorites.presentation.viewmodel


import androidx.lifecycle.*
import com.fabirt.roka.core.domain.repository.RecipeRepository
import com.fabirt.roka.features.favorites.domain.model.FavoriteRecipe
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _isSelecting = MutableLiveData(false)
    val isSelecting: LiveData<Boolean>
        get() = _isSelecting

    val recipes = repository.requestFavoriteRecipes().asLiveData()

    fun changeSelecting(value: Boolean) {
        _isSelecting.value = value
    }

    fun deleteFavorites(favorites: List<FavoriteRecipe>) {
        viewModelScope.launch {
            val recipes = favorites.map { it.data }
            repository.deleteMultipleFavorites(recipes)
        }
    }
}