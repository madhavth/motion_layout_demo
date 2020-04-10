package com.madhavth.motiondemo

import android.content.Context
import android.util.AttributeSet
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.viewpager2.widget.ViewPager2
import com.airbnb.lottie.LottieAnimationView

class DemoMotionLayout @JvmOverloads
constructor(context: Context, attributeSet: AttributeSet? = null, dsa: Int = 0) :
    MotionLayout(context, attributeSet, dsa) {
    private var viewPager2: ViewPager2? = null
    private var lottieViews: List<LottieAnimationView>? = null
    private var viewPagerChangeCallBack: MyViewPagerChangeCallBack? = null

    fun setUpLottieWithViewPager(viewPager2: ViewPager2, lottieViews: List<LottieAnimationView>) {
        this.viewPager2 = viewPager2
        this.lottieViews = lottieViews

        viewPagerChangeCallBack = MyViewPagerChangeCallBack()
        viewPager2.registerOnPageChangeCallback(viewPagerChangeCallBack!!)
    }

    private inner class MyViewPagerChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {

            if (lottieViews != null) {
                for (lottieAnim in lottieViews!!) {
                    lottieAnim.progress =
                        (position + positionOffset) / (viewPager2!!.adapter?.itemCount!!.toFloat() - 1f)
                }
            }
        }
    }


     fun addPageTransformer() {
        if(viewPager2!=null)
        {
            viewPager2!!.orientation = ViewPager2.ORIENTATION_VERTICAL

            viewPager2!!.setPageTransformer { page, position ->
                val pos = if (position < 0) -position else position
                page.rotationY = pos * 180
                page.scaleX = (1 - pos)
                page.scaleY = (1 - pos)
                page.alpha = (1 - pos)
                page.translationY = -position * viewPager2!!.width / 2
            }
        }
    }


    fun unRegisterViewPagerCallBack() {
        if (viewPagerChangeCallBack != null) {
            viewPager2?.unregisterOnPageChangeCallback(viewPagerChangeCallBack!!)
            viewPagerChangeCallBack = null
        }
    }
}