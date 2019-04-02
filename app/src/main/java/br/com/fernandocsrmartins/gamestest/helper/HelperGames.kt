package br.com.fernandocsrmartins.gamestest.helper

import android.content.Context
import android.net.ConnectivityManager

class HelperGames{

    companion object {
        fun getLinkBox(url: String, density: Float): String {
            val widthBox = (152 * density).toInt().toString()
            val heightBox = (218 * density).toInt().toString()
            return url.replace("{width}", widthBox).replace("{height}", heightBox)
        }

        fun getLinkLogo(url: String, density: Float): String {
            val widthLogo = (64 * density).toInt().toString()
            val heightLogo = (38 * density).toInt().toString()
            return url.replace("{width}", widthLogo).replace("{height}", heightLogo)
        }

        fun getDensity(context: Context): Float {
            return context.resources.displayMetrics.density
        }

        fun isInternet(context: Context): Boolean {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = connectivityManager.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }
    }
}