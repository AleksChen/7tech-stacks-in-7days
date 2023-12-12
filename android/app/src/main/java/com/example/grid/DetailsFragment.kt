package com.example.grid.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.grid.R
import com.example.grid.databinding.FragmentDetailsBinding
import com.example.grid.utils.loadJSONFromAsset
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

private const val ARG_SPUID = "param1"

/**
 * A simple [Fragment] subclass. Use the [DetailsFragment.newInstance] factory method to create an
 * instance of this fragment.
 */
class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding

    // TODO: Rename and change types of parameters
    private var param1: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { param1 = it.getString(ARG_SPUID) }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        (requireActivity() as AppCompatActivity).supportActionBar?.show()
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility = View.GONE
        setHasOptionsMenu(true)

        binding = FragmentDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPager = binding.viewPager
        val productName = binding.productName
        val productSlogan = binding.productSlogan

        val jsonString = loadJSONFromAsset(requireContext(), "product-detail.json")
        val gson = Gson()
        val productType = object : TypeToken<Product>() {}.type
        val res: Product = gson.fromJson(jsonString, productType)

        val images = res.carouselImages ?: emptyList()
        viewPager.adapter = ImagePagerAdapter(requireContext(), images)

        productName.text = res.name
        productSlogan.text = res.detailedIntroductions
        binding.productBeanSourceText.text = res.beansSource

        val productIntroductionList = res.introduction ?: emptyList()
        productIntroductionList.forEach { introductionText ->
            val textView = TextView(context)
            textView.text = introductionText

            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(0, 20, 0, 0)

            textView.layoutParams = layoutParams
            textView.textSize = 20f
            binding.product.addView(textView)
        }


        val toCartButton = binding.toCart
        toCartButton.setOnClickListener {
            Toast.makeText(requireContext(), "加入购物车成功", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }

        val toBuyButton = binding.toBuy
        toBuyButton.setOnClickListener {
            Toast.makeText(requireContext(), "前往直接购物", Toast.LENGTH_SHORT).show()
            findNavController().navigateUp()
        }
        Glide.with(view.context).load(res.beansTagImage).into(binding.productBeanSourceImg)
    }

    data class Product(
            val spuId: Double,
            val name: String,
            val salePrice: Double,
            val carouselImages: List<String>,
            val introduction: List<String>,
            val detailedIntroductions: String,
            val beansSource: String,
            val beansTagImage: String,
    )

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            findNavController().navigateUp()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of this fragment using the provided
         * parameters.
         *
         * @param param1 Parameter 1.
         * @return A new instance of fragment DetailsFragment.
         */
        @JvmStatic
        fun newInstance(param1: String) =
                DetailsFragment().apply {
                    arguments = Bundle().apply { putString(ARG_SPUID, param1) }
                }
    }

    override fun onStop() {
        super.onStop()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility =
                View.VISIBLE
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as AppCompatActivity).supportActionBar?.hide()
        requireActivity().findViewById<BottomNavigationView>(R.id.nav_view).visibility =
                View.VISIBLE
    }
}

class ImagePagerAdapter(private val context: Context, private val images: List<String>) :
        PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.layoutParams =
                ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, // Width
                        ViewGroup.LayoutParams.WRAP_CONTENT // Height
                )
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        Glide.with(context).load(images[position]).into(imageView)

        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}
