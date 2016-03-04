package com.example.viewpageimagesdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * 
 *  <code>ViewPagerMulTiFragmentDemo.java</code>
 *  <p>功能:viewpager多图选择
 *  
 *  @author ccy
 *  @version 1.0 
 *  </br>最后修改人 无
 */
public class ViewPagerMulTiFragmentDemo extends Activity {

	private ViewPager      viewPager;
	private CircleIndicator indicator;

	private List<ImageBean> mList = new ArrayList<ImageBean>();
	private HashMap<Integer, List<ImageBean>> map = new HashMap<Integer, List<ImageBean>>();
	private int[] res = {R.drawable.test1,R.drawable.test2,R.drawable.test3,R.drawable.test4,R.drawable.test5,R.drawable.test6};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.view_pager_multi_fragment_demo);
		viewPager = (ViewPager)findViewById(R.id.view_pager);
		indicator = (CircleIndicator) findViewById(R.id.indicator);
		for (int i = 0; i < res.length; i++) {
			ImageBean imageBean = new ImageBean();
			imageBean.setName("第"+i+"项");
			imageBean.setRes(res[i]);
			mList.add(imageBean);
		}
		List<List<ImageBean>> resultList = subList(mList, 3);
		for (int i = 0; i < resultList.size(); i++) {
			map.put(i, resultList.get(i));
		}
		viewPager.setAdapter(new MyPagerAdapter());
		viewPager.setPageMargin(getResources().getDimensionPixelSize(R.dimen.page_margin));
		indicator.setViewPager(viewPager);
	}



	class MyPagerAdapter extends PagerAdapter {
		@Override
		public int getCount() {
			return map.size();
		}

		@Override
		public boolean isViewFromObject(View view, Object object) {
			return (view == object);
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);  
			RelativeLayout relativeLayout = new RelativeLayout(ViewPagerMulTiFragmentDemo.this);
			relativeLayout.setLayoutParams(relativeParams);
			if(map.size() != 0){
				List<ImageBean> list = map.get(position);
				if(list != null && list.size() != 0){
					int margin = ScreenUtil.dip2px(ViewPagerMulTiFragmentDemo.this, 25);
					//根布局参数  
					RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);  
					layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
					//根布局  
					LinearLayout layout = new LinearLayout(ViewPagerMulTiFragmentDemo.this);  
					layout.setLayoutParams(layoutParams);  
					layout.setOrientation(LinearLayout.HORIZONTAL); 

					for (int i = 0; i < list.size(); i++) {
						final ImageBean bean = list.get(i);
						LinearLayout.LayoutParams ivLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);  
						ivLayoutParams.setMargins(margin, 0, margin, 0);
						ImageView imageView = new ImageView(ViewPagerMulTiFragmentDemo.this);
						relativeParams.addRule(RelativeLayout.CENTER_IN_PARENT,RelativeLayout.TRUE);
						imageView.setOnClickListener(new OnClickListener() {
							
							@Override
							public void onClick(View v) {
								Toast.makeText(ViewPagerMulTiFragmentDemo.this, bean.getName(), Toast.LENGTH_LONG).show();
							}
						});
						imageView.setImageResource(bean.getRes());
						imageView.setLayoutParams(ivLayoutParams);
						layout.addView(imageView,i);
					}
					relativeLayout.addView(layout);
				}
			}
			container.addView(relativeLayout);
			return relativeLayout;
		}

		@Override
		public int getItemPosition(Object object) {
			return map.size();
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

	//将list按blockSize大小等分，最后多余的单独一份  
	public static <T> List<List<T>> subList(List<T> list, int blockSize) {  
		List<List<T>> lists = new ArrayList<List<T>>();  
		if (list != null && blockSize > 0) {  
			int listSize = list.size();  
			if(listSize<=blockSize){  
				lists.add(list);  
				return lists;  
			}  
			int batchSize = listSize / blockSize;  
			int remain = listSize % blockSize;  
			for (int i = 0; i < batchSize; i++) {  
				int fromIndex = i * blockSize;  
				int toIndex = fromIndex + blockSize;  
				lists.add(list.subList(fromIndex, toIndex));  
			}  
			if(remain>0){  
				lists.add(list.subList(listSize-remain, listSize));  
			}  
		}
		return lists;  
	}


}
