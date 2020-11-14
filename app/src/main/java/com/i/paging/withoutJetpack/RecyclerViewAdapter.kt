package com.i.paging.withoutJetpack

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.i.paging.R
import com.i.paging.models.Repository
import kotlinx.android.synthetic.main.repo_item.view.*

class RecyclerViewAdapter(private val seznam: List<Repository>) : RecyclerView.Adapter<CustomViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val cellForRow = layoutInflater.inflate(R.layout.repo_item, parent, false)
        return CustomViewHolder(cellForRow)
    }

    override fun getItemCount(): Int {
        return seznam.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(seznam[position])
    }
}
class CustomViewHolder(val view: View): RecyclerView.ViewHolder(view){
    fun bind(repo: Repository){
        view.repo_name?.text = repo.fullName
        view.repo_description?.text = repo.description
        view.repo_stars?.text = repo.stars.toString()
    }
}