package com.fabirt.roka.features.categories.presentation.viewmodel


import androidx.lifecycle.*
import androidx.paging.*
import com.fabirt.roka.core.constants.K
import com.fabirt.roka.core.data.network.services.RecipeService
import com.fabirt.roka.core.domain.model.Recipe
import com.fabirt.roka.features.categories.domain.model.CategoryItem
import com.fabirt.roka.features.categories.domain.repository.FilteredRecipesPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class CategoryDetailViewModel @Inject constructor(
    private val service: RecipeService
) : ViewModel() {

    var recipesFlow: Flow<PagingData<Recipe>>? = null

    fun requestRecipesForCategory(categoryItem: CategoryItem) {
        recipesFlow = Pager(PagingConfig(K.RECIPES_PER_PAGE)) {
            val options = mapOf(categoryItem.type to categoryItem.name)
            FilteredRecipesPagingSource(service, options)
        }.flow
            .cachedIn(viewModelScope)
    }
}