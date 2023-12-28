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
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
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
        requireActivity()
                .window
                .setFlags(
                        WindowManager.LayoutParams.FLAG_FULLSCREEN,
                        WindowManager.LayoutParams.FLAG_FULLSCREEN
                )
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
            val tagImage: String,
            var count: Double
    )

    data class ProductList(val list: List<Product>)

    fun updateCartDisplay(totalPrice: Double) {
        val cartView = binding.root.findViewById<ExtendedFloatingActionButton>(R.id.totalPrice)

        if (totalPrice > 0) {
            // 如果 totalPrice 大于 0，显示购物车并更新总价
            cartView.visibility = View.VISIBLE
            cartView.text = "总价：¥${totalPrice.toString()}"
        } else {
            // cart. = View.GONE
            cartView.visibility = View.GONE
        }
    }

    inner class ProductAdapter(private val products: List<Product>) :
            RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

        inner class ProductViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
            val productImage: ImageView = view.findViewById(R.id.productImage)
            val productFirstRow = view.findViewById<View>(R.id.productFirstRow)

            val tagImage: ImageView = view.findViewById(R.id.tagImage)
            val nameTextView: TextView = view.findViewById(R.id.productName)
            val introductionTextView: TextView = view.findViewById(R.id.productIntroduction)
            val priceTextView: TextView = view.findViewById(R.id.productPrice)

            val minusButton: ImageView = view.findViewById(R.id.minus)
            val addButton: ImageView = view.findViewById(R.id.add)
            val countTextView: TextView = view.findViewById(R.id.count)

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
                productImage.setOnClickListener {
                    findNavController().navigate(R.id.action_navigation_home_to_detailFragment)
                }

                productFirstRow.setOnClickListener {
                    findNavController().navigate(R.id.action_navigation_home_to_detailFragment)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
            val view =
                    LayoutInflater.from(parent.context)
                            .inflate(R.layout.item_product, parent, false)

            return ProductViewHolder(view)
        }

        override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
            val product = products[position]
            holder.bind(product)

            var totalPrice: Double

            if (product.count < 1) {
                holder.minusButton.visibility = View.GONE
                holder.countTextView.visibility = View.GONE
            }

            holder.minusButton.setOnClickListener {
                product.count--
                if (product.count > 0) {
                    holder.countTextView.text = product.count.toInt().toString()
                } else {
                    holder.countTextView.visibility = View.GONE
                    holder.minusButton.visibility = View.GONE
                }
                totalPrice = products.map { it.salePrice * it.count }.sum()
                updateCartDisplay(totalPrice)
            }

            holder.addButton.setOnClickListener {
                product.count++
                if (product.count >= 1) {
                    holder.countTextView.text = product.count.toInt().toString()
                    holder.countTextView.visibility = View.VISIBLE
                    holder.minusButton.visibility = View.VISIBLE
                }
                totalPrice = products.map { it.salePrice * it.count }.sum()
                updateCartDisplay(totalPrice)
            }
        }

        override fun getItemCount() = products.size

        fun getItem(position: Int) = products[position]
    }
}
