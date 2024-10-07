package com.kioskapp.ryan
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.NonNull
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.android.example.recyclerview.R
import com.android.example.recyclerview.databinding.FoodItemLayoutBinding

class FoodItemAdapter(
    private val context: Context,
    private var cities: List<FoodItem>
) : RecyclerView.Adapter<FoodItemAdapter.ViewHolder>() {

    @NonNull
    override fun onCreateViewHolder(@NonNull parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FoodItemLayoutBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(@NonNull holder: ViewHolder, position: Int) {
        val foodItem = cities[position]
        holder.binding.apply {
            listimage.setImageResource(foodItem.listImage)
            name.text = foodItem.name
            description.text = foodItem.description
            price.text = foodItem.price.toString()
        }

        holder.itemView.setOnClickListener { view ->
            val data = Bundle().apply {
                putInt("position", position)
            }
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment, data)
        }
    }

    override fun getItemCount(): Int {
        return cities.size
    }

    fun updateFoodItems(newFoodItems: List<FoodItem>) {
        this.cities = newFoodItems
        notifyDataSetChanged();
    }

    class ViewHolder(val binding: FoodItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}
