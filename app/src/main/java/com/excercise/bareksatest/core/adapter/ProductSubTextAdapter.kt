package com.excercise.bareksatest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.excercise.bareksatest.core.domain.model.CustomBannerModel
import com.excercise.bareksatest.core.domain.model.MenuDataModel
import com.excercise.bareksatest.databinding.ItemCardRvDataBinding

class ProductSubTextAdapter : RecyclerView.Adapter<ProductSubTextAdapter.ProductViewHolder>() {
    inner class ProductViewHolder(private val binding: ItemCardRvDataBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: MenuDataModel) {
            binding.txtTitle.text = itemData.nameSectionMenu
            val bannerAdapter = ProductSubMenuAdapter()

            binding.rvCardDataProduct.apply {
                bannerAdapter.differ.submitList(itemData.listItemSection.toMutableList())
                layoutManager = GridLayoutManager(binding.root.context, 3)
                adapter = bannerAdapter
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<MenuDataModel>() {
        override fun areItemsTheSame(
            oldItem: MenuDataModel,
            newItem: MenuDataModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: MenuDataModel,
            newItem: MenuDataModel
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder =
        ProductViewHolder(
            ItemCardRvDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}