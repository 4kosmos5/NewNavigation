package com.fabirt.roka.features.detail.presentation.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.fabirt.roka.R
import com.fabirt.roka.core.utils.applyTopWindowInsets
import com.fabirt.roka.core.utils.bindNetworkImage
import com.fabirt.roka.core.utils.configureStatusBar
import com.fabirt.roka.databinding.FragmentPhotoRecipeBinding

class PhotoRecipeFragment : Fragment() {
    private val args: PhotoRecipeFragmentArgs by navArgs()
    private val binding by lazy { FragmentPhotoRecipeBinding.inflate(layoutInflater) }

    companion object {
        const val SHARED_IMAGE = "recipeImage"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = TransitionInflater
            .from(binding.root.context)
            .inflateTransition(android.R.transition.move)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        configureStatusBar()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindNetworkImage(binding.ivRecipe, args.imageUrl)
        binding.btnBack.applyTopWindowInsets()
        binding.btnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}