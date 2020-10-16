package me.atyrlik.testmistplay.gameslistscreen.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import me.atyrlik.testmistplay.databinding.GameItemBinding
import me.atyrlik.testmistplay.models.Game

class GamesAdapter(
    var games: List<Game>
): RecyclerView.Adapter<GamesAdapter.GameViewHolder>() {

    class GameViewHolder(private val binding: GameItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: Game) {
            binding.gameTitle.text = model.title
        }
    }

    override fun getItemCount() = games.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = GameItemBinding.inflate(inflater)
        return GameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) = holder.bind(games[position])
}