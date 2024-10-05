package com.android.example.recyclerview

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(
    private val items: MutableList<CartItem>,
    private val onQuantityChanged: (Int) -> Unit // Callback to notify about price changes
) : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    inner class CartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val cityName: TextView = itemView.findViewById(R.id.textViewCityName)
        private val cityImage: ImageView = itemView.findViewById(R.id.imageViewCity)
        private val quantityText: TextView = itemView.findViewById(R.id.textViewQuantity)
        private val priceText: TextView = itemView.findViewById(R.id.textViewPrice)

        @SuppressLint("SetTextI18n")
        fun bind(cartItem: CartItem) {
            // Bind city data
            cityName.text = cartItem.city.name
            cityImage.setImageResource(cartItem.city.detailImage)
            quantityText.text = cartItem.quantity.toString()
            priceText.text = "${cartItem.city.price} * ${cartItem.quantity}"

            // Increase quantity
            itemView.findViewById<Button>(R.id.buttonIncrease).setOnClickListener {
                cartItem.quantity++
                notifyItemChanged(adapterPosition)
                onQuantityChanged(getTotalPrice()) // Notify total price change
            }

            // Decrease quantity
            itemView.findViewById<Button>(R.id.buttonDecrease).setOnClickListener {
                if (cartItem.quantity > 1) {
                    cartItem.quantity--
                    notifyItemChanged(adapterPosition)
                    onQuantityChanged(getTotalPrice()) // Notify total price change
                }
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
        return items.sumOf { it.city.price * it.quantity }
    }
}
