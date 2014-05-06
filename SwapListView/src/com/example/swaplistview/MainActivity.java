package com.example.swaplistview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.text.method.ScrollingMovementMethod;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yaming.widget.SwipeRefreshLayout;
import com.yaming.widget.SwipeRefreshLayout.OnRefreshListener;

public class MainActivity extends ActionBarActivity implements
		OnRefreshListener {
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView lv_main;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.srl_main);
		lv_main = (ListView) findViewById(R.id.lv_main);
		swipeRefreshLayout.setOnRefreshListener(this);
		TextView textview= (TextView) findViewById(R.id.empty_view);
		textview.setMovementMethod(new ScrollingMovementMethod());
		lv_main.setEmptyView(textview);
		lv_main.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView arg0, int arg1) {
				
			}
			
			@Override
			public void onScroll(AbsListView absListView, int arg1, int arg2, int arg3) {
				boolean topRowVerticalPosition = absListView.getChildCount() > 0
						&& (absListView.getFirstVisiblePosition() > 0 || absListView
								.getChildAt(0).getTop() < absListView
								.getPaddingTop());;
				swipeRefreshLayout.setEnabled(!topRowVerticalPosition);
			}
		});
		swipeRefreshLayout.setColorScheme(android.R.color.holo_blue_bright,
				android.R.color.holo_green_light,
				android.R.color.holo_orange_light,
				android.R.color.holo_red_light);
	}

	public void onRefresh() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				swipeRefreshLayout.setRefreshing(false);
				lv_main.setAdapter(new ArrayAdapter<>(MainActivity.this,
						android.R.layout.simple_list_item_1, new String[] {
								"ddddddddd", "ddddddddd", "ddddddddd",
								"ddddddddd", "ddddddddd", "ddddddddd",
								"ddddddddd", "ddddddddd", "ddddddddd",
								"ddddddddd", "ddddddddd", "ddddddddd",
								"ddddddddd" }));
			}
		}, 5000);
	}

}
