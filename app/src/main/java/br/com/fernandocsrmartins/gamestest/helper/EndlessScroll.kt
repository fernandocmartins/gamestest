package br.com.fernandocsrmartins.gamestest.helper

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log

class EndlessScroll(
    val func: () -> Unit,
    val manager: LinearLayoutManager) : RecyclerView.OnScrollListener() {

    private var total = 0
    private var loading = true
    private var threshold = 2
    private var visibleItem = 0
    private var itemCount = 0
    private var sumItemCount = 0

    override fun onScrolled(recyclerView: RecyclerView, posx: Int, posy: Int) {
        super.onScrolled(recyclerView, posx, posy)

        if (posy > 0) {
            itemCount = recyclerView.childCount
            sumItemCount = manager.itemCount
            visibleItem = manager.findFirstVisibleItemPosition()

            if (loading) {
                if (sumItemCount > total) {
                    loading = false
                    total = sumItemCount
                }
            }
            if (!loading && (sumItemCount - itemCount)
                <= (visibleItem + threshold)) {
                Log.i("EndlessScroll", "bottom")
                func()
                loading = true
            }
        }
    }
}