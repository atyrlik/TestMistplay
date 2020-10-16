package me.atyrlik.testmistplay.gameslistscreen.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import me.atyrlik.testmistplay.databinding.GamesListFragmentBinding
import me.atyrlik.testmistplay.gameslistscreen.viewmodel.GamesListViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class GamesListFragment : Fragment() {
    private var _binding: GamesListFragmentBinding? = null
    private val binding get() = _binding!! // this is from the Android developer guideline

    companion object {
        fun newInstance() =
            GamesListFragment()
    }

    private val viewModel: GamesListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GamesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gamesCategoriesList.adapter = GamesCategoriesAdapter(gamesCategories = emptyList())

        viewModel.games.observe(viewLifecycleOwner, Observer { gamesCategories ->
            with (binding.gamesCategoriesList.adapter as GamesCategoriesAdapter) {
                this.gamesCategories = gamesCategories
                notifyDataSetChanged()
            }
        })

        viewModel.loadingState.observe(viewLifecycleOwner, Observer { state ->
            when (state) {
                GamesListViewModel.LoadingState.LOADING -> {
                    binding.gamesCategoriesList.isVisible = false
                    binding.loadingSpinner.isVisible = true
                    binding.errorMessage.isVisible = false
                }
                GamesListViewModel.LoadingState.READY -> {
                    binding.gamesCategoriesList.isVisible = true
                    binding.loadingSpinner.isVisible = false
                    binding.errorMessage.isVisible = false
                }
                GamesListViewModel.LoadingState.ERROR, null -> {
                    // In a real app, we should give an option to the user to retry.
                    binding.gamesCategoriesList.isVisible = false
                    binding.loadingSpinner.isVisible = false
                    binding.errorMessage.isVisible = true
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}