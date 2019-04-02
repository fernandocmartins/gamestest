package br.com.fernandocsrmartins.gamestest.api

import br.com.fernandocsrmartins.gamestest.model.TwitchRankingGames
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService{
    private val API_CALL: ApiCall

    init {
        val builder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.twitch.tv")
            .build()


        API_CALL = builder.create(ApiCall::class.java)
    }

    fun getGames(limit: Int, offset: Int): Call<TwitchRankingGames> {
        return API_CALL.getTopGames(limit, offset)
    }
}