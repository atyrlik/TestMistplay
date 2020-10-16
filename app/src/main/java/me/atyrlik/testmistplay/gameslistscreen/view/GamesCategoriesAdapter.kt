package me.atyrlik.testmistplay.gameslistscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.atyrlik.testmistplay.databinding.GamesCategoryItemBinding
import me.atyrlik.testmistplay.models.GamesList

class GamesCategoriesAdapter(
    var gamesCategories: List<GamesList>
): RecyclerView.Adapter<GamesCategoriesAdapter.GamesCategoryViewHolder>() {

    // Allow nested recyclerviews to share views (improve performance).
    private val gamesRecycledViewPool = RecyclerView.RecycledViewPool()

    inner class GamesCategoryViewHolder(private val binding: GamesCategoryItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: GamesList) {
            binding.gameCategoryTitle.text = model.title
            binding.gamesList.apply {
                adapter = GamesAdapter(model.games)
                setRecycledViewPool(gamesRecycledViewPool)
            }
        }
    }

    override fun getItemCount() = gamesCategories.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GamesCategoryItemBinding.inflate(inflater)
        return GamesCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GamesCategoryViewHolder, position: Int) = holder.bind(gamesCategories[position])
}