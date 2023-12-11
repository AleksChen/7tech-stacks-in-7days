package com.example.grid.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.grid.R
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val ARG_SPUID = "param1"

/**
 * A simple [Fragment] subclass. Use the [DetailsFragment.newInstance] factory method to create an
 * instance of this fragment.
 */
class DetailsFragment : Fragment() {
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

        return inflater.inflate(R.layout.fragment_details, container, false)
    }

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
