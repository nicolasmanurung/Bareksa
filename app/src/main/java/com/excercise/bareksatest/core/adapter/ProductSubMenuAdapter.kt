package com.excercise.bareksatest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.excercise.bareksatest.R
import com.excercise.bareksatest.databinding.ItemCardRvContainerBinding

class ProductSubMenuAdapter :
    RecyclerView.Adapter<ProductSubMenuAdapter.ProductSubMenuViewHolder>() {
    inner class ProductSubMenuViewHolder(private val binding: ItemCardRvContainerBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: String, position: Int) {
            binding.txtTitle.text = itemData
            when (position) {
                0 -> {
                    binding.cvContainer.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartOneColorBackground
                        )
                    )
                }

                1 -> {
                    binding.cvContainer.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartTwoColorBackground
                        )
                    )
                }
                2 -> {
                    binding.cvContainer.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartThreeColorBackground
                        )
                    )
                }
            }
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSubMenuViewHolder =
        ProductSubMenuViewHolder(
            ItemCardRvContainerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductSubMenuViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)
    }

    override fun getItemCount(): Int = differ.currentList.size
}