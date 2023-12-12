package com.example.grid.ui.home

import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.grid.R
import com.example.grid.databinding.FragmentHomeBinding
import com.example.grid.utils.loadJSONFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SpacesItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
    ) {
        with(outRect) {
            if (parent.getChildAdapterPosition(view) == 0) {
                top = space
            }
            left = space
            right = space
            bottom = space
        }
    }
}

class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {

        // 在 Fragment 的 onCreateView 生命周期中调用 supportActionBar?.hide() 进行隐藏，但会把所有页面的操作栏都隐藏
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val jsonString = loadJSONFromAsset(requireContext(), "product-list.json")
        val gson = Gson()
        val productListType = object : TypeToken<ProductList>() {}.type
        val res: ProductList = gson.fromJson(jsonString, productListType)

        val recyclerView = binding.recyclerView
        recyclerView.addItemDecoration(SpacesItemDecoration(36))

        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ProductAdapter(res.list)
    }

    data class Product(
            val spuId: Double,
            val name: String,
            val salePrice: Double,
            val img: String,
            val introduction: String,
            val tagImage: String
    )

    data class ProductList(val list: List<Product>)

    inner class ProductAdapter(private val products: List<Product>) :
            RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val productImage: ImageView = view.findViewById(R.id.productImage)
            val tagImage: ImageView = view.findViewById(R.id.tagImage)
            val nameTextView: TextView = view.findViewById(R.id.productName)
            val introductionTextView: TextView = view.findViewById(R.id.productIntroduction)
            val priceTextView: TextView = view.findViewById(R.id.productPrice)

            fun bind(product: Product) {
                nameTextView.text = product.name
                priceTextView.text = "${product.salePrice}元"
                introductionTextView.text = product.introduction
                Glide.with(view.context)
                        .load(product.img)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(productImage)

                if (product.tagImage != null) {
                    Glide.with(view.context).load(product.tagImage).into(tagImage)
                    tagImage.visibility = View.VISIBLE
                } else {
                    tagImage.visibility = View.GONE
                }
            }

            init {
                view.setOnClickListener {
                    // val product = products[adapterPosition]
                    findNavController().navigate(R.id.action_navigation_home_to_detailFragment)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_product, parent, false)

            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            holder.bind(products[position])
        }

        override fun getItemCount() = products.size

        fun getItem(position: Int) = products[position]
    }
}
