package me.atyrlik.testmistplay.gameslist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import me.atyrlik.testmistplay.R

class GamesListFragment : Fragment() {

    companion object {
        fun newInstance() = GamesListFragment()
    }

    private val viewModel: GamesListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.games_list_fragment, container, false)
    }

}