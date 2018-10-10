package com.doing.kotlin.app.ui.fragment

import android.content.Context
import android.widget.ImageView
import com.doing.kotlin.app.R
import com.doing.kotlin.app.data.repository.HomeRepository
import com.doing.kotlin.baselib.data.image.ImageUtils
import com.doing.kotlin.baselib.ui.fragment.BaseFragment
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initBanner()
    }

    private fun initBanner() {
        mHomeBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
        mHomeBanner.setImageLoader(object : ImageLoader() {

            override fun displayImage(context: Context?, path: Any, imageView: ImageView) {
                ImageUtils.setUrl(imageView, path.toString())
            }
        })
        mHomeBanner.setImages(listOf(HomeRepository.HOME_BANNER_ONE, HomeRepository.HOME_BANNER_TWO,
                HomeRepository.HOME_BANNER_THREE, HomeRepository.HOME_BANNER_FOUR))
        mHomeBanner.isAutoPlay(true)
        mHomeBanner.setIndicatorGravity(BannerConfig.RIGHT)
        mHomeBanner.start()
    }
}