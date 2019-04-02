package br.com.fernandocsrmartins.gamestest.view

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import br.com.fernandocsrmartins.gameslist.R
import br.com.fernandocsrmartins.gamestest.adapter.TwitchGamesAdapter
import br.com.fernandocsrmartins.gamestest.api.ApiService
import br.com.fernandocsrmartins.gamestest.helper.EndlessScroll
import br.com.fernandocsrmartins.gamestest.helper.HelperGames
import br.com.fernandocsrmartins.gamestest.model.GameContents
import br.com.fernandocsrmartins.gamestest.model.TwitchRankingGames
import io.paperdb.Paper
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.scrolling.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private var gameContentsList: MutableList<GameContents> =  ArrayList()
    private var adapter: TwitchGamesAdapter? = null
    private var snack: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Paper.init(this)
        bindViews()
        startGames(10, 0)
    }

    private fun bindViews(){
        content_scrolling_swipe_refresh.setOnRefreshListener(this)
        adapter = TwitchGamesAdapter(gameContentsList as ArrayList<GameContents>, this)
        content_scrolling_recycler_view.adapter = adapter
        content_scrolling_recycler_view.apply {
            setHasFixedSize(true)
            val gridLayoutManager = GridLayoutManager(context, 1)
            layoutManager = gridLayoutManager
            clearOnScrollListeners()
            addOnScrollListener(EndlessScroll({ startGames(adapter!!.itemCount + 10, 0) }, gridLayoutManager))
        }
    }

    private fun startGames(limit: Int, offset: Int) {
        if (HelperGames.isInternet(this)) {
            if (snack != null) snack!!.dismiss()
            ApiService.getGames(limit, offset).enqueue(object : Callback<TwitchRankingGames> {
                override fun onResponse(call: Call<TwitchRankingGames>, response: Response<TwitchRankingGames>) {
                    if (response.isSuccessful)
                        update(response.body()?.gameContentsList)
                }

                override fun onFailure(call: Call<TwitchRankingGames>, t: Throwable) {
                    hideLoading()
                    snackStart(R.string.error)
                    snack?.show()
                }
            })
        } else {
            snackStart(R.string.internet_fail)
            update(Paper.book().read("games"))
            snack?.show()
            hideLoading()
        }
    }

    private fun hideLoading() {
        progress_bar.visibility = View.GONE
        content_scrolling_swipe_refresh.isRefreshing = false
    }

    fun getGameDetails(gameContents: GameContents) {
        val gameFragment = GameFragment.newInstance(gameContents, this)
        gameFragment.show(supportFragmentManager, GameFragment.GAME_TAG)
    }

    private fun update(list: List<GameContents>?) {
        if(list != null){
            gameContentsList.clear()
            gameContentsList.addAll(list)
            Paper.book().write("games", list)
        }
        adapter?.notifyDataSetChanged()
        hideLoading()
    }

    override fun onRefresh() {
        startGames(10, 0)
    }

    private fun snackStart(messageId: Int) {
        snack = Snackbar.make(content_scrolling_recycler_view, messageId, Snackbar.LENGTH_INDEFINITE)
    }
}
