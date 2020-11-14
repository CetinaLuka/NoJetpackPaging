package com.i.paging.withoutJetpack

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.i.paging.models.Repository

class RepositoryViewModel: ViewModel() {
    private var repozitoriji = MutableLiveData<List<Repository>>()
    private var zadnjaStran = false
    private val githubService = GithubService.create()

    fun vrniPodatke(): MutableLiveData<List<Repository>> {
        return repozitoriji
    }
    fun jeZadnjaStran(): Boolean{
        return zadnjaStran
    }
    fun pridobiPodatke(stran: Int, steviloVnosov: Int){
        searchRepos(
            githubService,
            "Android",
            stran,
            steviloVnosov,
            { repos ->
                if (repos.size > 0)
                    repozitoriji.value = repozitoriji.value.orEmpty() + repos
                else
                    zadnjaStran = true
            },
            { error ->
                Log.i("Pridobivanje podatkov", "Napaka")
            })
    }
}