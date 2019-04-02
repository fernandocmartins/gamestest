package br.com.fernandocsrmartins.gamestest.model

import com.google.gson.annotations.SerializedName

class TwitchRankingGames (
    @SerializedName("_total")
    val total: Int,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("top")
    val gameContentsList: List<GameContents>)