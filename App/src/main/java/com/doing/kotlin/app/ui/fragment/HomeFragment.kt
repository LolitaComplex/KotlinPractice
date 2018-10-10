package com.doing.kotlin.app.ui.fragment

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.widget.ImageView
import com.doing.kotlin.app.R
import com.doing.kotlin.app.data.repository.HomeRepository
import com.doing.kotlin.baselib.data.image.ImageUtils
import com.doing.kotlin.baselib.ui.adapter.BaseViewHolder
import com.doing.kotlin.baselib.ui.adapter.CommonAdapter
import com.doing.kotlin.baselib.ui.fragment.BaseFragment
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import java.util.*

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initBanner()
        initNews()
        initDiscount()
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

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券"))
    }

    private fun initDiscount() {
        mHomeDiscountRv.layoutManager = LinearLayoutManager(mContext,
                LinearLayoutManager.HORIZONTAL, false)
        mHomeDiscountRv.adapter = object :CommonAdapter<String>(mContext, R.layout.layout_home_discount_item,
                Arrays.asList(HomeRepository.HOME_DISCOUNT_ONE, HomeRepository.HOME_DISCOUNT_TWO, HomeRepository.HOME_DISCOUNT_THREE,
                        HomeRepository.HOME_DISCOUNT_FOUR, HomeRepository.HOME_DISCOUNT_FIVE)){
            override fun convert(holder: BaseViewHolder, data: String, position: Int) {
                holder.getView<ImageView>(R.id.mGoodsIconIv)?.let {
                    ImageUtils.setUrl(it, data)
                }
            }
        }

    }
}