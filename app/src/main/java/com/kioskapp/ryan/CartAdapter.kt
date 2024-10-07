package com.kioskapp.ryan

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recyclerview.R

class CartAdapter(
    private val items: MutableList<FoodItem>,
    private val onQuantityChanged: (Int) -> Unit // Callback to notify about price changes
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val FoodItemName: TextView = itemView.findViewById(R.id.textViewFoodItemName)
        private val FoodItemImage: ImageView = itemView.findViewById(R.id.imageViewFoodItem)
        private val quantityText: TextView = itemView.findViewById(R.id.textViewQuantity)
        private val priceText: TextView = itemView.findViewById(R.id.textViewPrice)
        private val delete: ImageButton = itemView.findViewById(R.id.delete)

        @SuppressLint("SetTextI18n")
        fun bind(foodItem: FoodItem) {
            // Bind FoodItem data
            FoodItemName.text = foodItem.name
            FoodItemImage.setImageResource(foodItem.listImage)
            quantityText.text = foodItem.quantity.toString()
            priceText.text = "${foodItem.price}"

            delete.setOnClickListener {
                FoodItemManager.getInstance().removeFromCart(items[adapterPosition])
                notifyItemChanged(adapterPosition)
                onQuantityChanged(getTotalPrice())
            }

            // Increase quantity
            itemView.findViewById<Button>(R.id.buttonIncrease).setOnClickListener {
                foodItem.quantity++
                notifyItemChanged(adapterPosition)
                onQuantityChanged(getTotalPrice()) // Notify total price change
            }

            // Decrease quantity
            itemView.findViewById<Button>(R.id.buttonDecrease).setOnClickListener {
                if (foodItem.quantity > 1) {
                    foodItem.quantity--
                }else{
                    FoodItemManager.getInstance().removeFromCart(foodItem)
                }
                notifyItemChanged(adapterPosition)
                onQuantityChanged(getTotalPrice()) // Notify total price change
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_cart, parent, false)
        return CartViewHolder(view)
    }

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun getTotalPrice(): Int {
        return items.sumOf { it.price * it.quantity }
    }
}
