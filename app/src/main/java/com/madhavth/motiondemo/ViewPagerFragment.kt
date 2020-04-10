package com.madhavth.motiondemo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_view_pager.view.*
import kotlin.properties.Delegates

/**
 * A simple [Fragment] subclass.
 */
class ViewPagerFragment : Fragment() {

    private var message: String=""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_pager, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        message= arguments?.getString(POSITION)?:""
        view.tvFragment.text = message
    }

    companion object{
        @JvmStatic
        fun newInstance(message: String): ViewPagerFragment
        {
            val fragment = ViewPagerFragment()
            val bundle = Bundle()
            bundle.apply {
                putString(POSITION, message)
            }
            fragment.arguments = bundle
            Log.d("ViewPagerFragment", "message is $message, ${fragment.arguments?.getInt(POSITION)}")
            return fragment
        }

        const val POSITION: String = "POSITION"
    }

}

