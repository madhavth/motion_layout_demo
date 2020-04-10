package com.madhavth.motiondemo

import android.graphics.drawable.Animatable
import android.graphics.drawable.Drawable
import android.graphics.drawable.Icon
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.MotionScene
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_view_pager.view.*

class MainActivity : AppCompatActivity() {

    private var viewPagerChangeCallback: MyViewPagerChangeCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPagerAdapter = MyAdapter(this)
        viewPagerChangeCallback = MyViewPagerChangeCallBack()
        myViewPager.adapter = viewPagerAdapter

        setUpLottieProgress()
        addPageTransformer()
    }



    private fun addPageTransformer() {
        myViewPager.setPageTransformer { page, position ->
            val pos = if (position < 0) -position else position
            page.rotationY = pos * 180
            page.scaleX = (1 - pos)
            page.scaleY = (1 - pos)
            page.alpha = (1 - pos)
            page.translationX = -position * myViewPager.width / 2
        }
    }


    private fun setUpLottieProgress() {
        myViewPager.registerOnPageChangeCallback(viewPagerChangeCallback!!)
    }


    private inner class MyViewPagerChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            Log.d(
                "AnimationProgress",
                "$position, $positionOffset, ${lottieAnim.progress}, ${myViewPager.adapter?.itemCount}"
            )
            lottieAnim.progress =
                (position + positionOffset) / (myViewPager.adapter?.itemCount!!.toFloat() - 1f)
            lottieAnim2.progress =
                (position + positionOffset) / (myViewPager.adapter?.itemCount!!.toFloat() - 1f)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        myViewPager.unregisterOnPageChangeCallback(viewPagerChangeCallback!!)
        viewPagerChangeCallback = null
    }
}
