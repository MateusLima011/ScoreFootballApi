package com.mgt.scorefootballapi.players

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgt.domain.model.players.Player
import com.mgt.scorefootballapi.R

class PlayersAdapter(
    private var playersList: List<Player?> = listOf()
) : RecyclerView.Adapter<PlayersAdapter.PlayersViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayersAdapter.PlayersViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_players, parent, false)
        return PlayersViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersAdapter.PlayersViewHolder, position: Int) {
        holder.bind(playersList[position])
    }

    override fun getItemCount(): Int = playersList.size

    inner class PlayersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imagePlayer: ImageView = itemView.findViewById(R.id.imageViewPlayer)
        private val namePlayer: TextView = itemView.findViewById(R.id.textViewPlayerName)

        fun bind(player: Player?) {
            player ?: return

            Glide
                .with(itemView.context)
                .load(player.photo)
                .into(imagePlayer)
            namePlayer.text = player.name
        }
    }

}