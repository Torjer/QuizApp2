package com.example.quizapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizapp2.db.Player

class PlayersAdapter(private val playersList: List<Player>) : RecyclerView.Adapter<PlayersAdapter.PlayersHolder>(){

    class PlayersHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersAdapter.PlayersHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return PlayersHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.findViewById<TextView>(R.id.name_text).text = playersList[position].name
        holder.view.findViewById<TextView>(R.id.date_text).text = playersList[position].gamedate
        if(playersList[position].hints == true){
            holder.view.findViewById<TextView>(R.id.hints_text).text = "Yes"
        }
        else{
            holder.view.findViewById<TextView>(R.id.hints_text).text = "No"
        }
        holder.view.findViewById<TextView>(R.id.score_text).text = playersList[position].score.toString()
    }

    override fun getItemCount() = playersList.size


}

class ScoresAdapter(private val scoresList: List<Player>) : RecyclerView.Adapter<ScoresAdapter.ScoresHolder>(){

    class ScoresHolder(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresAdapter.ScoresHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item2, parent, false)
        return ScoresHolder(view)
    }

    override fun onBindViewHolder(holder: ScoresHolder, position: Int) {
        holder.view.findViewById<TextView>(R.id.name2_text).text = scoresList[position].name
        holder.view.findViewById<TextView>(R.id.score2_text).text = scoresList[position].score.toString()
    }

    override fun getItemCount() = scoresList.size


}