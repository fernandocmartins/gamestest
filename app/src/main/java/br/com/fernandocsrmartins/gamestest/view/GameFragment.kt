package br.com.fernandocsrmartins.gamestest.view

import android.os.Bundle
import android.support.v7.app.AppCompatDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import br.com.fernandocsrmartins.gameslist.R
import br.com.fernandocsrmartins.gamestest.helper.HelperGames
import br.com.fernandocsrmartins.gamestest.model.GameContents
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_game.*

class GameFragment : AppCompatDialogFragment(){

    private lateinit var gameContents: GameContents

    companion object {
        @JvmField
        val GAME_TAG: String = "game_fragment"

        fun newInstance(gameContents: GameContents): GameFragment {
            val fragment = GameFragment()
            fragment.gameContents = gameContents
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_game, container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getGameBox()
        getGameLogo()
        getGameTitle()
        getGameDetails()
    }

    private fun setGameDetails(channels: String, views: String): String {
        return String.format("%s %s\n%s %s", getString(R.string.channels), channels, getString(R.string.viewers), views)
    }

    private fun getGameLogo() {
        val url = gameContents.game?.logo!!.template
        Picasso.with(img_logo.context)
            .load(HelperGames.getLinkLogo(url, HelperGames.getDensity(this.context!!)))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(img_logo)
    }

    private fun getGameBox() {
        val url = gameContents.game?.box!!.template
        Picasso.with(img_box.context)
            .load(HelperGames.getLinkBox(url, HelperGames.getDensity(this.context!!)))
            .placeholder(R.drawable.placeholder)
            .error(R.drawable.placeholder)
            .into(img_box)
    }

    private fun getGameDetails() {
        val details = setGameDetails(gameContents.channels.toString(), gameContents.viewers.toString())
        txt_info.text = details
    }

    private fun getGameTitle() {
        title.text = gameContents.game?.name
    }
}