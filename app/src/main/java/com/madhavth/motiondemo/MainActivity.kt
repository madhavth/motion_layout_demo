package com.madhavth.motiondemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPagerAdapter = MyAdapter(this)
        myViewPager.adapter = viewPagerAdapter

        motionLayout.setUpLottieWithViewPager(myViewPager,
            listOf<LottieAnimationView>(lottieAnim,lottieAnim2))
        motionLayout.addPageTransformer()
    }



    override fun onDestroy() {
        super.onDestroy()
        motionLayout.unRegisterViewPagerCallBack()
    }
}
