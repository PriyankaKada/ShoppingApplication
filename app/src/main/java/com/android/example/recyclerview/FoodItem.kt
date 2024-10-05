package com.android.example.recyclerview

/**
 * Created by hakim on 3/11/18.
 */

// The entity with default constructor

data class FoodItem(var listImage:Int,
                    var name:String,
                    var price:Int,
                    var quantity: Int,
                    var description:String)