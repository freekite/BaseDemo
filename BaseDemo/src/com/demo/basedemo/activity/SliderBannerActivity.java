package com.demo.basedemo.activity;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import cn.lightsky.infiniteindicator.InfiniteIndicatorLayout;
import cn.lightsky.infiniteindicator.slideview.BaseSliderView;
import cn.lightsky.infiniteindicator.slideview.DefaultSliderView;

import com.demo.basedemo.R;
import com.demo.basedemo.base.BaseActivity;
import com.demo.basedemo.bean.SliderBannerInfo;
import com.demo.basedemo.utils.ToastUtil;

public class SliderBannerActivity extends BaseActivity implements BaseSliderView.OnSliderClickListener {

	private List<SliderBannerInfo> bannerImages = new ArrayList<SliderBannerInfo>();
	private InfiniteIndicatorLayout mAnimCircleIndicator;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_slider_banner);
		bannerImages.add(new SliderBannerInfo("Page A", "http://img0.bdstatic.com/img/image/shouye/sheying0701.jpg"));
		bannerImages.add(new SliderBannerInfo("Page B", "http://img0.bdstatic.com/img/image/shouye/bizhi0701.jpg"));
		bannerImages.add(new SliderBannerInfo("Page C", "http://img0.bdstatic.com/img/image/shouye/mingxing0701.jpg"));
		bannerImages.add(new SliderBannerInfo("Page D", "http://img0.bdstatic.com/img/image/shouye/dongman0504.jpg"));
		initView();
	}

	private void initView() {
		mAnimCircleIndicator = (InfiniteIndicatorLayout) findViewById(R.id.infinite_anim_circle);
		for (SliderBannerInfo name : bannerImages) {
			DefaultSliderView textSliderView = new DefaultSliderView(this);
			textSliderView.image(name.getUrl()).setScaleType(BaseSliderView.ScaleType.Fit).setOnSliderClickListener(this);
			textSliderView.getBundle().putString("extra", name.getData());
			mAnimCircleIndicator.addSlider(textSliderView);
		}
		mAnimCircleIndicator.setIndicatorPosition(InfiniteIndicatorLayout.IndicatorPosition.Right_Bottom);
	}

	@Override
	protected void onPause() {
		super.onPause();
		mAnimCircleIndicator.stopAutoScroll();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mAnimCircleIndicator.startAutoScroll();
	}

	@Override
	public void onSliderClick(BaseSliderView slider) {
		ToastUtil.toast(this, slider.getBundle().get("extra") + "");
	}
}
