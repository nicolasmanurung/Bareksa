package com.excercise.bareksatest.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ListDetailProductResponse(
    val code: Int,
    val `data`: List<Product>,
    val error: String,
    val message: String,
    val total_data: Int
) : Parcelable

@Parcelize
data class Product(
    val code: String,
    val details: Details,
    val name: String
) : Parcelable


@Parcelize
data class Details(
    val category: String,
    val category_id: Int,
    val currency: String,
    val custody: String,
    val im_avatar: String,
    val im_name: String,
    val inception_date: String,
    val min_balance: Int,
    val min_redemption: Int,
    val min_subscription: Int,
    val nav: Double,
    val return_cur_year: Double,
    val return_five_year: Double,
    val return_four_year: Double,
    val return_inception_growth: Double,
    val return_one_day: Double,
    val return_one_month: Double,
    val return_one_week: Double,
    val return_one_year: Double,
    val return_six_month: Double,
    val return_three_month: Double,
    val return_three_year: Double,
    val return_two_year: Double,
    val total_unit: Int,
    val type: String,
    val type_id: Int
) : Parcelable