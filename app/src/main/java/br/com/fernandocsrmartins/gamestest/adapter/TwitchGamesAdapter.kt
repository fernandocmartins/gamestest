package br.com.fernandocsrmartins.gamestest.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import br.com.fernandocsrmartins.gameslist.R
import br.com.fernandocsrmartins.gamestest.view.MainActivity
import br.com.fernandocsrmartins.gamestest.helper.HelperGames
import br.com.fernandocsrmartins.gamestest.model.GameContents
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.list_item.view.*

class TwitchGamesAdapter(var gameContentsList: List<GameContents>, var activity: MainActivity) : RecyclerView.Adapter<TwitchGamesAdapter.ViewHolder>(){

    override fun onBindViewHolder(holder: ViewHolder, position: Int)  = holder.bind(gameContentsList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(parent)

    override fun getItemCount(): Int = gameContentsList.size

    private fun loadImage(gameContents: GameContents, img: ImageView) {
        Picasso.with(img.context)
            .load(HelperGames.getLinkBox(gameContents.game?.box!!.template, HelperGames.getDensity(img.context)))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(img)
    }

    private fun loadText(gameContents: GameContents, txt: TextView) {
        txt.text = gameContents.game?.name
    }

    inner class ViewHolder(itemView: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(itemView.context).inflate(R.layout.list_item, itemView, false)) {

        fun bind(item: GameContents) = with(itemView) {
            item.let {
                loadImage(it, game_image)
                loadText(it, game_title)

                itemView.list_item_main_layout.setOnClickListener {
                    activity.getGameDetails(item)
                }
            }
        }
    }
}