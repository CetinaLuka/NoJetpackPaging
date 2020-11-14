package com.i.paging.withoutJetpack

import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewPager(recyclerView: RecyclerView) : RecyclerView.OnScrollListener() {
    //velikost vsake strani
    private var velikostStrani = 10
    //spremeljivka ki hrani številko trenutne strani
    private var trenutnaStran = 0
    //spremenljivka, ki določi kdaj se pridobi nova stran
    private var meja = 2
    abstract val jeZadnjaStran: Boolean
    private var jeOsvezeno = false
    fun vrniNaslednjoStran(): Int{
        trenutnaStran += 1
        return trenutnaStran
    }
    fun vrniVelikostStrani(): Int{
        return velikostStrani
    }

    private val layoutManager: RecyclerView.LayoutManager?
    init {
        recyclerView.addOnScrollListener(this)
        this.layoutManager = recyclerView.layoutManager
        pridobiNaslednjoStran(vrniNaslednjoStran(), vrniVelikostStrani())
    }

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        super.onScrollStateChanged(recyclerView, newState)
        if (newState == RecyclerView.SCROLL_STATE_DRAGGING) {
            val totalItemCount = layoutManager!!.itemCount

            var lastVisibleItemPosition = 0
            var firstVisibleItemPosition = 0
            if (layoutManager is LinearLayoutManager) {
                lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            }
            Log.i("paging", "$firstVisibleItemPosition - $lastVisibleItemPosition, $totalItemCount")
            if(jeZadnjaStran)
                return
            if (lastVisibleItemPosition + 1 + meja >= totalItemCount) {
                Log.i("paging", (lastVisibleItemPosition + 1 + meja).toString() + ">=" + totalItemCount.toString())
                if (!jeOsvezeno) {
                    jeOsvezeno = true
                    pridobiNaslednjoStran(vrniNaslednjoStran(), vrniVelikostStrani())
                }
            } else {
                jeOsvezeno = false
            }
        }
    }
    abstract fun pridobiNaslednjoStran(start: Int, count: Int)

}