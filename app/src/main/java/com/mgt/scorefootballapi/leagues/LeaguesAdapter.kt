package com.mgt.scorefootballapi.leagues

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mgt.domain.model.leagues.League
import com.mgt.scorefootballapi.R

class LeaguesAdapter(
    private var leagueList: List<League?> = listOf()
) : RecyclerView.Adapter<LeaguesAdapter.LeaguesViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): LeaguesAdapter.LeaguesViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recycler_item_leagues, parent, false)
        return LeaguesViewHolder(view)
    }

    override fun onBindViewHolder(holder: LeaguesAdapter.LeaguesViewHolder, position: Int) {
        holder.bind(leagueList[position])
    }

    override fun getItemCount(): Int = leagueList.size

    class LeaguesViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        private val imageLeague: ImageView = itemView.findViewById(R.id.imageViewLeague)
        private val nameLeague: TextView = itemView.findViewById(R.id.textViewLeagueName)
        private val typeLeague: TextView = itemview.findViewById(R.id.textViewTypeLeague)

        fun bind(league: League?) {
            league ?: return

            Glide
                .with(itemView.context)
                .load(league.logo)
                .into(imageLeague)
            nameLeague.text = league.name
            typeLeague.text = league.type
        }
    }
}