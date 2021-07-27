package com.example.noteapp.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.noteapp.model.Note
import com.example.noteapp.databinding.CustomRowBinding


class ListAdapter:RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var binding : CustomRowBinding? = null
    private var noteList = emptyList<Note>()

    class MyViewHolder(val itemBinding : CustomRowBinding): RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        return MyViewHolder(CustomRowBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = noteList[position]

        holder.itemBinding.titleTxt.text = currentItem.title
        holder.itemBinding.bodyTxt.text = currentItem.body
        holder.itemBinding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.itemBinding.rowLayout.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    fun setData(note: List<Note>){
        this.noteList = note
        notifyDataSetChanged()
    }

}