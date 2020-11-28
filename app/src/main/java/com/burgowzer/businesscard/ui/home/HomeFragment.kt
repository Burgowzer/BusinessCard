package com.burgowzer.businesscard.ui.home

import android.content.Context
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieListener
import com.burgowzer.businesscard.R
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        /*
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        */


        return root
    }

    override fun onResume() {
        super.onResume()
        val animation = view?.findViewById<LottieAnimationView>(R.id.nfc_animation)


        animation?.setOnClickListener{
            loadingAnimation(animation,"loading.json")
            animation.playAnimation()
            }
        }

    private fun loadingAnimation(view:LottieAnimationView, animationName: String) {
        view.setAnimation(animationName)

        text_home.text = "Loading"

    }



}
