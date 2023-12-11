package com.example.grid.ui.me

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.grid.databinding.FragmentMeBinding
import com.example.grid.R
import com.bumptech.glide.Glide

class MeFragment : Fragment() {

    private lateinit var binding: FragmentMeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMeBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.hide()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        binding.userName.text = getString(R.string.user_name)
        Glide.with(view.context).load("https://www.guibook.com/upload/image/202005/15908056758211805.jpg").circleCrop() .into(binding.avatar)
        
        val grid = binding.root.findViewById<GridLayout>(R.id.grid)

        repeat(8) { i ->
            val button = Button(requireContext()).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = 0
                    height = GridLayout.LayoutParams.WRAP_CONTENT
                    columnSpec = GridLayout.spec(i % 2, 1f)  // assign items to the column and set the weight
                    rowSpec = GridLayout.spec(i / 2)     // assign items to the row
                }
                text = "设置入口 $i"
            }
            grid.addView(button)
        }

    }
}
