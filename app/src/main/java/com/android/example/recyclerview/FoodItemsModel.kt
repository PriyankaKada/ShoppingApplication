package com.android.example.recyclerview

import androidx.lifecycle.ViewModel

class FoodItemModel:ViewModel() {

    val foodItems = loadData()


    private fun loadData():List<FoodItem> {
        val foodItem1 = FoodItem(R.drawable.caesar_salad,"Caesar Salad",30,1,"Crisp romaine lettuce, crunchy croutons, and Parmesan cheese, all tossed in a creamy Caesar dressing made with anchovies, garlic, and lemon juice. Visual Cue: Look for a bowl of bright green romaine, golden croutons, and shavings of cheese.")

        val foodItem2 = FoodItem(R.drawable.greek_salad,"Greek Salad",70,1,"A refreshing mix of ripe tomatoes, cucumbers, red onion, bell peppers, Kalamata olives, and feta cheese, drizzled with olive oil and oregano. Visual Cue: Colorful chunks of vegetables topped with white feta and dark olives.")

        val foodItem3 = FoodItem(R.drawable.caprese_salad,"Caprese Salad",30,1,"Slices of fresh mozzarella and tomatoes, layered with basil leaves and drizzled with balsamic reduction and olive oil. Simple yet delicious. Visual Cue: Alternating red and white slices, garnished with vibrant green basil.")

        val foodItem4 = FoodItem(R.drawable.cobb_salad,"Cobb Salad",50,1,"A hearty salad featuring chopped greens, grilled chicken, bacon, hard-boiled eggs, avocado, tomatoes, and blue cheese, often served with a vinaigrette. Visual Cue: A colorful plate with neatly arranged sections of each ingredient.")

        val foodItem5 = FoodItem(R.drawable.nicoise_salad,"Nicoise Salad",60,1," A French salad combining tuna (canned or fresh), hard-boiled eggs, green beans, potatoes, olives, and tomatoes, usually dressed with a vinaigrette. Visual Cue: A beautiful arrangement of ingredients, often served on a platter.")
        val foodItem6 = FoodItem(R.drawable.quinoa_salad,"Quinoa Salad",60,1,"  A protein-packed salad made with cooked quinoa, mixed vegetables (like bell peppers and cucumbers), herbs, and a light lemon dressing. Visual Cue: Fluffy quinoa mixed with colorful diced veggies and fresh herbs.")
        val foodItem7 = FoodItem(R.drawable.pasta_salad,"Pasta Salad",60,1,"Typically made with cooked pasta, olives, cherry tomatoes, mozzarella, and a vinaigrette. Perfect for picnics and potlucks. Visual Cue: Twirls of pasta mixed with vibrant vegetables and chunks of cheese.")
        val foodItem8 = FoodItem(R.drawable.beans_salad,"Bean Salad",60,1,"A protein-rich salad made with various beans (like kidney, chickpeas, and black beans), diced vegetables, and a tangy dressing. It's hearty and great for a filling meal")


        val list = mutableListOf(foodItem1,foodItem2,foodItem3,foodItem4,foodItem5,foodItem6,foodItem7,foodItem8)
        return  list
    }
}