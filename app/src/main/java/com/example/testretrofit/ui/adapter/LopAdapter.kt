package com.example.testretrofit.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.testretrofit.databinding.ItemLopBinding
import com.example.testretrofit.model.Lop

class LopAdapter(
    private var lops: List<Lop>,
    private val editOnClick: (Lop, Int) -> Unit,
    private val deleteOnClick: (Lop, Int) -> Unit
) : RecyclerView.Adapter<LopAdapter.LopViewHolder>() {
    
    inner class LopViewHolder(private val binding: ItemLopBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lop: Lop, position: Int) {
            binding.tvTenLop.text = lop.tenlop
            binding.btnEdit.setOnClickListener {
                editOnClick(lop, position)
            }
            binding.btnDelete.setOnClickListener {
                deleteOnClick(lop, position)
            }
        }
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LopViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemLopBinding.inflate(layoutInflater, parent, false)
        return LopViewHolder(binding)
    }
    
    override fun getItemCount(): Int = lops.size
    
    override fun onBindViewHolder(holder: LopViewHolder, position: Int) {
        holder.bind(lops[position], position)
    }
    
    fun updateLops(newLops: List<Lop>) {
        lops = newLops
        notifyDataSetChanged()
    }
}
