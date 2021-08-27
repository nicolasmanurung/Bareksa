package com.excercise.bareksatest.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CustomBannerModel(
    val listBanner: List<BannerModel>,
    val listMenuData: List<MenuDataModel>
) : Parcelable

@Parcelize
data class BannerModel(
    val index: Int,
    val imageSource: String,
    val nameProduct: String
) : Parcelable

@Parcelize
data class MenuDataModel(
    val nameSectionMenu: String,
    val listItemSection: List<String>
) : Parcelable