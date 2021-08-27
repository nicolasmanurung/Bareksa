package com.excercise.bareksatest.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "detailProduct")
class DetailProductEntity(
    @PrimaryKey
    val codeName: String,
    val name: String,
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
)