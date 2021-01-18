package com.example.quizapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PlayersAdapter(private val playersArray: Array<Player>) : RecyclerView.Adapter<PlayersAdapter.PlayersHolder>(){

        companion object {
        val TYPE_HEAD : Int = 0
        val TYPE_SCORE : Int = 1
        val TYPE_EXTENDEDSCORE : Int = 2
    }

    class PlayersHolder(val view: View) : RecyclerView.ViewHolder(view){
        var view_type : Int = 0
        lateinit var hname : TextView
        lateinit var hdate : TextView
        lateinit var hscore : TextView
        lateinit var hhint : TextView
        lateinit var name : TextView
        lateinit var date : TextView
        lateinit var score : TextView
        lateinit var hint : TextView

        constructor(rv: View, viewType: Int ): this(rv){ //se supone que es super(rv) pero no me deja ponerlo
            if(viewType == TYPE_HEAD){
                hname = rv.findViewById(R.id.hname_text) as TextView
                hdate = rv.findViewById(R.id.hdate_text) as TextView
                hscore = rv.findViewById(R.id.hscore_text) as TextView
                hhint = rv.findViewById(R.id.hhints_text) as TextView
                view_type = 0
            }
            else if(viewType == TYPE_SCORE){
                name = rv.findViewById(R.id.name_text) as TextView
                date = rv.findViewById(R.id.date_text) as TextView
                score = rv.findViewById(R.id.score_text) as TextView
                hint = rv.findViewById(R.id.hints_text) as TextView
                view_type = 1
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersAdapter.PlayersHolder {
        //este fue un intento pero marca error
        /*var rv: View
        var holder: PlayersHolder
        if (viewType == TYPE_HEAD) {
            rv = LayoutInflater.from(parent.context).inflate(R.layout.rv_header, parent, false)
            holder = PlayersHolder(rv, viewType)
            return holder
        } else if (viewType == TYPE_SCORE) {
            rv = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
            holder = PlayersHolder(rv, viewType)
            return holder
        }*/
        val view = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return PlayersHolder(view)

    }

    override fun onBindViewHolder(holder: PlayersHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.view.findViewById<TextView>(R.id.name_text).text = playersArray[position].name
        holder.view.findViewById<TextView>(R.id.date_text).text = playersArray[position].gamedate
        if(playersArray[position].hints == true){
            holder.view.findViewById<TextView>(R.id.hints_text).text = "Yes"
        }
        else{
            holder.view.findViewById<TextView>(R.id.hints_text).text = "No"
        }
        holder.view.findViewById<TextView>(R.id.score_text).text = playersArray[position].score.toString()
    }

    override fun getItemCount() = playersArray.size


}