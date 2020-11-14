package com.i.paging.withoutJetpack

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.i.paging.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val repoViewModel = ViewModelProviders.of(this)[RepositoryViewModel::class.java]

        val seznam = seznam_repozitorijev

        val decoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        seznam.addItemDecoration(decoration)
        seznam.layoutManager = LinearLayoutManager(this)

        seznam.addOnScrollListener(object: RecyclerViewPager(seznam){
            override val jeZadnjaStran: Boolean
                get() = repoViewModel.jeZadnjaStran()

            override fun pridobiNaslednjoStran(start: Int, count: Int) {
                repoViewModel.pridobiPodatke(start, count)
            }
        })
        repoViewModel.vrniPodatke().observe(this, Observer { repozitoriji ->
            val recyclerViewManager = seznam.layoutManager as LinearLayoutManager
            val recyclerViewState = recyclerViewManager.onSaveInstanceState()
            seznam.adapter =
                RecyclerViewAdapter(repozitoriji)
            recyclerViewManager.onRestoreInstanceState(recyclerViewState)
        })

    }
}