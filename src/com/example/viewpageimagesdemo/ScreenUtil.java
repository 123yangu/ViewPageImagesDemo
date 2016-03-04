package com.example.viewpageimagesdemo;

import android.app.Activity;
import android.content.Context;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * 
* @Title: ScreenUtil.java 
* @Package com.yeehealth.utils 
* @Description: 屏幕帮助�?
* @author ccy  
* @date 2014-11-14 下午3:42:52 
* @version V1.0
 */
public class ScreenUtil {
	private static Display defaultDisplay;
	
	public static Display getDefaultDisplay(Context context) {
		if (null == defaultDisplay) {
			WindowManager systemService = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
			defaultDisplay = systemService.getDefaultDisplay();
		}
		return defaultDisplay;
	}
	/**
	 * 
	* @Title: getScreenWidth 
	* @Description: 获得屏幕宽度 
	* @param @param context
	* @param @return    
	* @return int    
	* @author ccy
	* @throws
	 */
	public static int getScreenWidth(Context context) {
		return context.getResources().getDisplayMetrics().widthPixels;
	}

	/**
	 * 
	* @Title: getScreenHeight 
	* @Description: 获得屏幕高度 
	* @param @param context
	* @param @return    
	* @return int    
	* @author ccy
	* @throws
	 */
	public static int getScreenHeight(Context context) {
		return context.getResources().getDisplayMetrics().heightPixels;
	}

	public static float getScreenDensity(Context context) {
		return context.getResources().getDisplayMetrics().density;
	}

	/**
	 * 
	* @Title: dip2px 
	* @Description: 将dip转换为px 
	* @param @param context
	* @param @param px
	* @param @return    
	* @return int    
	* @author ccy
	* @throws
	 */
	public static int dip2px(Context context, float px) {
		final float scale = getScreenDensity(context);
		return (int) (px * scale + 0.5);
	}
	
	 /**
     * 
     * <b>@Description:<b>dp2px<br/>
     * <b>@param context
     * <b>@param dp
     * <b>@return<b>int<br/>
     * <b>@Author:<b>ccy<br/>
     * <b>@Since:<b>2014-9-4-上午10:04:48<br/>
     */
    public static int dp2px(Context context,int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				context.getResources().getDisplayMetrics());
	}
    
    public static int getWidth(Context context) {
		return getDefaultDisplay(context).getWidth();
	}
    
    public static void setListViewHeightBasedOnChildren(ListView listView) {    
		ListAdapter listAdapter = listView.getAdapter();    
		if (listAdapter == null) {  
			// pre-condition    
			return;    
		}    

		int totalHeight =0;  
		for (int i = 0; i < listAdapter.getCount(); i++) {    
			View listItem = listAdapter.getView(i, null, listView);    
			listItem.measure(0, 0);   
			totalHeight += listItem.getMeasuredHeight();    
		}    

		System.out.println("aaa==="+totalHeight);  
		ViewGroup.LayoutParams params = listView.getLayoutParams();    
		params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));    
		listView.setLayoutParams(params);    
	}
    
    /**
	 * 获取状�?栏高�?
	 * 
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		Class<?> c = null;
		Object obj = null;
		java.lang.reflect.Field field = null;
		int x = 0;
		int statusBarHeight = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			statusBarHeight = context.getResources().getDimensionPixelSize(x);
			return statusBarHeight;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return statusBarHeight;
	}
	
	 /**
     * 获取状�?栏高度＋标题栏高�?
     *
     * @param activity
     * @return
     */
    public static int getTopBarHeight(Activity activity) {
        return activity.getWindow().findViewById(Window.ID_ANDROID_CONTENT).getTop();
    }

}
