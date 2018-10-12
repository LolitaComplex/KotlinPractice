package com.doing.kotlin.app.ui.fragment

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.doing.kotlin.app.R
import com.doing.kotlin.app.data.repository.HomeRepository
import com.doing.kotlin.app.ui.widget.RoundRectImageView
import com.doing.kotlin.baselib.data.image.ImageUtils
import com.doing.kotlin.baselib.ui.adapter.BaseViewHolder
import com.doing.kotlin.baselib.ui.adapter.CommonAdapter
import com.doing.kotlin.baselib.ui.fragment.BaseFragment
import com.doing.kotlin.baselib.utils.UiUtils
import com.youth.banner.BannerConfig
import com.youth.banner.loader.ImageLoader
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.find

class HomeFragment : BaseFragment() {

    override fun getLayoutId(): Int {
        return R.layout.fragment_home
    }

    override fun initView() {
        initBanner()
        initNews()
        initDiscount()
        initPager()
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
        mHomeDiscountRv.adapter = object : CommonAdapter<String>(mContext, R.layout.layout_home_discount_item,
                mutableListOf(HomeRepository.HOME_DISCOUNT_ONE, HomeRepository.HOME_DISCOUNT_TWO, HomeRepository.HOME_DISCOUNT_THREE,
                        HomeRepository.HOME_DISCOUNT_FOUR, HomeRepository.HOME_DISCOUNT_FIVE)) {
            override fun convert(holder: BaseViewHolder, data: String, position: Int) {
                holder.getView<ImageView>(R.id.mGoodsIconIv)?.let {
                    ImageUtils.setUrl(it, data)
                }
                holder.setText(R.id.mDiscountAfterTv, "￥159.00")
                        .setText(R.id.mDiscountBeforeTv, "￥1000.00")
            }
        }

    }

    private fun initPager() {
        mTopicPager.adapter = object : PagerAdapter(){
            private val data = listOf(HomeRepository.HOME_TOPIC_ONE, HomeRepository.HOME_TOPIC_TWO,
                    HomeRepository.HOME_TOPIC_THREE, HomeRepository.HOME_TOPIC_FOUR,
                    HomeRepository.HOME_TOPIC_FIVE)

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val view = UiUtils.inflate(R.layout.layout_topic_item, container)
                val ivCover = view.find<RoundRectImageView>(R.id.mTopicIv)
                ImageUtils.setUrl(ivCover, data[position])
                container.addView(view)
                return view
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                container.removeView(`object` as View)
            }


            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun getCount(): Int {
                return data.size
            }
        }


        mTopicPager.currentItem = 0
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder()
                .with(mTopicPager)
                .scale(0.3f)
                .pagerMargin(-30.0f)
                .spaceSize(0.0f)
                .build()
    }
}