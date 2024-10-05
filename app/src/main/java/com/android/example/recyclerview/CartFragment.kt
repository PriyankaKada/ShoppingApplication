package com.android.example.recyclerview

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CartFragment : Fragment() {

    private lateinit var cartAdapter: CartAdapter
    private lateinit var totalPriceTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        totalPriceTextView = view.findViewById(R.id.textViewTotalPrice)
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerViewCart)
        recyclerView.layoutManager = LinearLayoutManager(context)

        val cartCities = CityManager.getInstance().getCartCities()
        val cartItems = cartCities.map { CartItem(it, 1) }.toMutableList()

        cartAdapter = CartAdapter(cartItems) { totalPrice ->
            updateTotalPrice(totalPrice)
        }
        recyclerView.adapter = cartAdapter

        // Initial total price calculation
        updateTotalPrice(cartAdapter.getTotalPrice())

        // Checkout button click listener
        view.findViewById<Button>(R.id.buttonCheckout).setOnClickListener {
            showCheckoutDialog(cartItems)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun updateTotalPrice(totalPrice: Int) {
        totalPriceTextView.text = "$totalPrice"
    }

    private fun showCheckoutDialog(cartItems: List<CartItem>) {
        val dialogBuilder = AlertDialog.Builder(requireContext())
        dialogBuilder.setTitle("Order Confirmation")
        dialogBuilder.setMessage("Order placed successfully!")
        dialogBuilder.setCancelable(false)

        // Set the dialog button
        dialogBuilder.setPositiveButton("OK") { dialog, _ ->
            dialog.dismiss()
            CityManager.getInstance().removeAllFromCart()
            // Navigate to MainFragment after confirming the order
            findNavController().navigate(R.id.action_cartFragment_to_mainFragment)
        }

        // Create the dialog
        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }
}
