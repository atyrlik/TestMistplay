package me.atyrlik.testmistplay.gameslistscreen.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}