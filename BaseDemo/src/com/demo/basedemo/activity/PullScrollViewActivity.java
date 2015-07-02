package com.demo.basedemo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.demo.basedemo.R;
import com.demo.basedemo.base.BaseActivity;
import com.markmao.pullscrollview.ui.widget.PullScrollView;

public class PullScrollViewActivity extends BaseActivity implements PullScrollView.OnTurnListener {

	private PullScrollView mScrollView;
	private ImageView mHeadImg;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_pull_scroll_view);
		mScrollView = (PullScrollView) findViewById(R.id.scroll_view);
		mHeadImg = (ImageView) findViewById(R.id.background_img);
		mScrollView.setHeader(mHeadImg);
		mScrollView.setOnTurnListener(this);
	}

	@Override
	public void onTurn() {

	}

}
