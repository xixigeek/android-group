package com.example.slidingtabforviewpager;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 * 视图相关工具类
 */
public class ViewUtil {

	/**
	 * 获取屏幕的长宽属性
	 * 
	 * @param context
	 * @return
	 */
	public static DisplayMetrics getDisplayMetrics(Context context) {
		DisplayMetrics dm = new DisplayMetrics();
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(dm);
		return dm;
	}

	/**
	 * 将px转为dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static final int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}

	/**
	 * 将dip转为px
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static final int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 将px值转换为sp值，保证文字大小不变
	 * 
	 * @param pxValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int px2sp(Context context, float pxValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (pxValue / fontScale + 0.5f);
	}

	/**
	 * 将sp值转换为px值，保证文字大小不变
	 * 
	 * @param spValue
	 * @param fontScale
	 *            （DisplayMetrics类中属性scaledDensity）
	 * @return
	 */
	public static int sp2px(Context context, float spValue) {
		final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
		return (int) (spValue * fontScale + 0.5f);
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public static void hideSoftInput(Activity context) {
		try {
			((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(context.getCurrentFocus().getWindowToken(),
					InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	/**
	 * 隐藏软键盘
	 * 
	 * @param context
	 */
	public static void hideSoftInput(Activity context, EditText edt) {
		try {
			((InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(edt.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断软键盘状态
	 * 
	 * @param context
	 */
	public static boolean isSoftInputShowed(Activity context) {
		int softInputMode = context.getWindow().getAttributes().softInputMode;
		return softInputMode == WindowManager.LayoutParams.SOFT_INPUT_STATE_UNSPECIFIED;
	}

	/**
	 * 获取SDK版本
	 * 
	 * @return
	 */
	public static int getSDKVerSion() {
		return android.os.Build.VERSION.SDK_INT;
	}

	/**
	 * 给某个视图集中的子视图添加标签 给adapter调用
	 * 
	 * @param convertView
	 * @param ids
	 */
	public static void storeToTag(View convertView, int... ids) {
		for (int id : ids) {
			convertView.setTag(id, convertView.findViewById(id));
		}
	}
}
