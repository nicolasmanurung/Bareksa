package com.excercise.bareksatest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.excercise.bareksatest.databinding.ItemCardRvButtonBinding

class ProductButtonAdapter : RecyclerView.Adapter<ProductButtonAdapter.ProductButtonViewHolder>() {
    inner class ProductButtonViewHolder(private val binding: ItemCardRvButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: String) {

        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<String>() {
        override fun areItemsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: String,
            newItem: String
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductButtonViewHolder =
        ProductButtonViewHolder(
            ItemCardRvButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductButtonViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}