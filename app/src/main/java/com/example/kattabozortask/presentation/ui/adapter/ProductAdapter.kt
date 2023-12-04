package com.example.kattabozortask.presentation.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.kattabozortask.R
import com.example.kattabozortask.databinding.ItemCategoryOffersBinding
import com.example.kattabozortask.network.data.Product
import com.squareup.picasso.Picasso


class ProductAdapter : ListAdapter<Product, ProductAdapter.Holder>(Comparator()) {



    class Holder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = ItemCategoryOffersBinding.bind(view)

        fun bind(item: Product) = with(binding) {
            textViewProductName.text = item.name
            textViewBrand.text = "Brand: ${item.brand}"
            textViewCategory.text = "Category: ${item.category}"
            textViewMerchant.text = "Merchant: ${item.merchant}"
            Picasso.get().load(item.image.url).into(imageViewProduct)

            val attributes = StringBuilder()
            for (i in 0 until item.attributes.size){
                val attribute = item.attributes[i]
                attributes.append("${attribute.name}: ${attribute.value}\n")
            }
            textViewAttributes.text = attributes
        }
    }

    class Comparator : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder =
        Holder(LayoutInflater.from(parent.context).inflate(R.layout.item_category_offers, parent, false))


    override fun onBindViewHolder(holder: Holder, position: Int) = holder.bind(getItem(position))


}