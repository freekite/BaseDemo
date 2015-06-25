package com.demo.basedemo.activity;

import in.srain.cube.views.loadmore.LoadMoreContainer;
import in.srain.cube.views.loadmore.LoadMoreHandler;
import in.srain.cube.views.loadmore.LoadMoreListViewContainer;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.demo.basedemo.R;
import com.demo.basedemo.adapter.DemoListAdapter;
import com.demo.basedemo.base.BaseActivity;
import com.demo.basedemo.utils.DensityUtil;

public class PullToRefreshActivity extends BaseActivity {

	private List<String> dataList = new ArrayList<String>();
	private DemoListAdapter mAdapter;
	private PtrClassicFrameLayout mPtrFrameLayout;
	private LoadMoreListViewContainer loadMoreListViewContainer;
	private ListView mListView;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.activity_pull_to_refresh);

		initView();
	}

	private void initView() {
		mAdapter = new DemoListAdapter(dataList, this);
		mPtrFrameLayout = (PtrClassicFrameLayout) findViewById(R.id.load_more_list_view_ptr_frame);
		mPtrFrameLayout.setLoadingMinTime(1000);
		mPtrFrameLayout.setPtrHandler(new PtrHandler() {
			@Override
			public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
				// here check list view, not content.
				return PtrDefaultHandler.checkContentCanBePulledDown(frame, mListView, header);
			}

			@Override
			public void onRefreshBegin(PtrFrameLayout frame) {
				refreshData();
			}
		});
		mPtrFrameLayout.setLastUpdateTimeRelateObject(true);

		mListView = (ListView) findViewById(R.id.load_more_small_image_list_view);
		// header place holder
		View headerMarginView = new View(this);
		headerMarginView.setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dp2px(this, 20)));
		mListView.addHeaderView(headerMarginView);

		// load more container
		loadMoreListViewContainer = (LoadMoreListViewContainer) findViewById(R.id.load_more_list_view_container);
		loadMoreListViewContainer.useDefaultFooter();

		// binding view and data
		mListView.setAdapter(mAdapter);
		loadMoreListViewContainer.setLoadMoreHandler(new LoadMoreHandler() {
			@Override
			public void onLoadMore(LoadMoreContainer loadMoreContainer) {
				loadData();
			}
		});

		// auto load data
		mPtrFrameLayout.postDelayed(new Runnable() {
			@Override
			public void run() {
				mPtrFrameLayout.autoRefresh(false);
			}
		}, 150);
	}

	private void refreshData() {
		dataList.clear();
		loadData();
	}

	private void loadData() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				runOnUiThread(new Runnable() {

					@Override
					public void run() {

						for (int i = 0; i < 10; i++) {
							dataList.add(i + "");
						}

						// ptr
						mPtrFrameLayout.refreshComplete();
						loadMoreListViewContainer.loadMoreFinish(false, true);
						mAdapter.notifyDataSetChanged();
						// loadMoreListViewContainer.loadMoreError(0,
						// event.message);
					}
				});
			}
		}).start();

	}

}
