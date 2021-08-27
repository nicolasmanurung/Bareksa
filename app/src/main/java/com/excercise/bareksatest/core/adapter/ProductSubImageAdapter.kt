package com.excercise.bareksatest.core.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.excercise.bareksatest.R
import com.excercise.bareksatest.core.domain.model.BannerModel
import com.excercise.bareksatest.databinding.ItemCardRvImageBinding

class ProductSubImageAdapter :
    RecyclerView.Adapter<ProductSubImageAdapter.ProductSubImageViewHolder>() {
    inner class ProductSubImageViewHolder(private val binding: ItemCardRvImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(itemData: BannerModel) {
            Glide.with(binding.root.context)
                .load(itemData.imageSource)
                .into(binding.imgProduct)
            binding.txtNameProduct.text = itemData.nameProduct
            when (itemData.index) {
                0 -> {
                    binding.cvProductBanner.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartOneColorBackground
                        )
                    )
                }

                1 -> {
                    binding.cvProductBanner.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartTwoColorBackground
                        )
                    )
                }
                2 -> {
                    binding.cvProductBanner.setCardBackgroundColor(
                        ContextCompat.getColorStateList(
                            binding.root.context,
                            R.color.chartThreeColorBackground
                        )
                    )
                }
            }
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<BannerModel>() {
        override fun areItemsTheSame(
            oldItem: BannerModel,
            newItem: BannerModel
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: BannerModel,
            newItem: BannerModel
        ): Boolean {
            return oldItem.nameProduct == newItem.nameProduct
        }
    }

    val differ = AsyncListDiffer(this, differCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductSubImageViewHolder =
        ProductSubImageViewHolder(
            ItemCardRvImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )

    override fun onBindViewHolder(holder: ProductSubImageViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size
}