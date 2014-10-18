package com.example.slidingtabforviewpager;

import com.example.slidingtabforviewpager.R.drawable;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @Date 2014年10月13日 13:39:28
 * 
 * @author 门前大桥下 ❀
 * 
 *         ViewPager滑动视图辅助类（仿微信）
 *
 */
public class FixedSlideTab extends LinearLayout {

	private static final int DEFAULT_TAB_TXT_COLOR = 0xff9e9e9e;
	private static final int DEFAULT_TAB_TXT_COLOR_SELECTED = 0xff5677fc;
	private static final float DEFAULT_TAB_TXT_SIZE_SP = 16f;

	private static final int DEFAULT_INDICATOR_COLOR = 0xff5677fc;
	private static final int DEFAULT_INDICATOR_HEIGHT_DP = 4;
	private static final int DEFAULT_INDICATOR_PADDING_DP = 0;

	private static final int DEFAULT_BOTTOM_LINE_COLOR = 0xff9e9e9e;
	private static final float DEFAULT_BOTTOM_LINE_WIDTH_DP = 0.5f;

	private static final int DEFAULT_PRESSING_COLOR = 0xffe0e0e0;

	private final float density = getResources().getDisplayMetrics().density;

	private int mSelectedPosition;
	private float mSelectionOffset;

	private int mTextColor;
	private int mTextSelectedColor;
	private float mTextSize;

	private int mIndicatorColor;
	private int mIndicatorHeight;
	private Paint mIndicatorPaint;
	private int mIndicatorPadding;

	private Paint mBottomLinePaint;
	/** 注释掉了下划线， **/
	@SuppressWarnings("unused")
	private int mBottomLineColor;

	private int mPressingTab;
	private Paint mPressingPaint;
	private int mPressingColor;

	private OnClickListener mOnClickListener;
	private OnTouchListener mOnTouchListener;
	private OnTabClickListener mOnTabClickListener;

	private Context context;

	public FixedSlideTab(Context context) {
		this(context, null);
		this.context = context;

	}

	public FixedSlideTab(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		this.context = context;

	}

	public FixedSlideTab(Context context, AttributeSet attrs, int defStyle) {

		super(context, attrs, defStyle);
		setWillNotDraw(false);
		this.context = context;

		mTextColor = DEFAULT_TAB_TXT_COLOR;
		mTextSelectedColor = DEFAULT_TAB_TXT_COLOR_SELECTED;
		mTextSize = DEFAULT_TAB_TXT_SIZE_SP;

		mIndicatorColor = DEFAULT_INDICATOR_COLOR;
		mIndicatorHeight = (int) (DEFAULT_INDICATOR_HEIGHT_DP * density);
		mIndicatorPaint = new Paint();
		mIndicatorPaint.setColor(DEFAULT_INDICATOR_COLOR);
		mIndicatorPadding = (int) (DEFAULT_INDICATOR_PADDING_DP * density);

		mBottomLineColor = DEFAULT_BOTTOM_LINE_COLOR;
		mBottomLinePaint = new Paint();
		mBottomLinePaint.setColor(DEFAULT_BOTTOM_LINE_COLOR);
		mBottomLinePaint.setStyle(Paint.Style.STROKE);
		mBottomLinePaint.setStrokeWidth(DEFAULT_BOTTOM_LINE_WIDTH_DP * density);

		mPressingTab = -1;
		mPressingPaint = new Paint();
		mPressingPaint.setColor(DEFAULT_PRESSING_COLOR);
		mPressingColor = DEFAULT_PRESSING_COLOR;

		mOnClickListener = new MyOnClickListener();
		mOnTouchListener = new MyOnTouchListener();

	}

	public void addTab(String tabTitle,int id) {

		// int pad = ViewUtil.dip2px(context, 2);

		TextView tabTextView = new TextView(getContext());
		tabTextView.setTag(getChildCount());
		tabTextView.setTextColor(mTextColor);
		tabTextView.setTextSize(mTextSize);

		// tabTextView.setPadding(pad, 4 * pad, pad, pad);
		//
		 Drawable drawable =
		 getResources().getDrawable(id);
		 drawable.setBounds(0, 0, drawable.getMinimumWidth()*2,
		 drawable.getMinimumHeight()*2);
		
		 tabTextView.setCompoundDrawables(null, drawable, null, null);
		 tabTextView.setCompoundDrawablePadding(ViewUtil.dip2px(context, -3));

		// tabTextView.setTextSize(11);
		tabTextView.setText(tabTitle);
		tabTextView.setGravity(Gravity.CENTER);
		LayoutParams ly = new LayoutParams(0, LayoutParams.WRAP_CONTENT);
		ly.weight = 1f;
		tabTextView.setOnClickListener(mOnClickListener);
		tabTextView.setOnTouchListener(mOnTouchListener);
		addView(tabTextView, ly);
	}
	
	 public final float[] BT_SELECTED = new float[] {1,0,0,0,255,0,1,0,0,-50,0,0,1,0,-50,0,0,0,1,0};  
     public final float[] BT_NOT_SELECTED = new float[] {1,0,0,0,10,0,1,0,0,10,0,0,1,0,10,0,0,0,1,0};  
	

	public void remove() {

		try {
			removeAllViews();
		} catch (Exception e) {

		}
	}

	public void onTabScrolled(int position, float positionOffset) {
		mSelectedPosition = position;
		mSelectionOffset = positionOffset;
		// invalidate();
		requestLayout();
	}

	public void setOnTabClickListener(OnTabClickListener listener) {
		mOnTabClickListener = listener;
	}

	private TextView tempText, currentText;

	@SuppressLint("DrawAllocation")
	@Override
	protected void onDraw(Canvas canvas) {
		final int height = getHeight();
		final int childCount = getChildCount();
		for (int i = 0; i < childCount; i++) {
			TextView tabTextView = (TextView) getChildAt(i);
			if (i == mSelectedPosition) {
				// tabTextView.setTextColor(mTextSelectedColor);
				tabTextView.setTextColor(Color.argb(255, 69, 192, 26));
				tabTextView.getCompoundDrawables()[1].setColorFilter(Color.argb(255, 69, 192, 26), Mode.MULTIPLY);
				// tabTextView.setAlpha(1);
			} else {
				// tabTextView.setTextColor(mTextColor);
				// tabTextView.setAlpha(0);
				tabTextView.setTextColor(Color.argb(255 / 4, 69, 192, 26));
				tabTextView.getCompoundDrawables()[1].setColorFilter(Color.argb(255/3, 69, 192, 26), Mode.MULTIPLY);
			}
		}

		if (mPressingTab >= 0 && mSelectedPosition < getChildCount()) {
			View pressingView = getChildAt(mPressingTab);
			int left = pressingView.getLeft();
			int top = pressingView.getTop();
			int right = pressingView.getRight();
			int bottom = height;
			mPressingPaint.setColor(mPressingColor);
			canvas.drawRect(left, top, right, bottom, mPressingPaint);
		}

		currentText = (TextView) getChildAt(mSelectedPosition);

		if (childCount > 0) {
			View selectedTab = getChildAt(mSelectedPosition);
			int left = selectedTab.getLeft();
			int right = selectedTab.getRight();

			if (mSelectionOffset > 0f && mSelectedPosition < (getChildCount() - 1)) {
				View nextTab = getChildAt(mSelectedPosition + 1);
				tempText = (TextView) nextTab;
				left = (int) (mSelectionOffset * nextTab.getLeft() + (1.0f - mSelectionOffset) * left);
				right = (int) (mSelectionOffset * nextTab.getRight() + (1.0f - mSelectionOffset) * right);
			}

			mIndicatorPaint.setColor(mIndicatorColor);
			if (tempText != null) {
				if (mSelectionOffset > 0&&mSelectionOffset <1) {
					// tempText.setAlpha(mSelectionOffset);
					tempText.setTextColor(Color.argb((int) (mSelectionOffset * 255), 69, 192, 26));
					currentText.getCompoundDrawables()[1].setColorFilter(Color.argb((int) (mSelectionOffset * 255), 69, 192, 26), Mode.MULTIPLY);
					// currentText.setAlpha(1 - mSelectionOffset);
					currentText.getCompoundDrawables()[1].setColorFilter(Color.argb((int) ((1 - mSelectionOffset) * 255), 69, 192, 26), Mode.MULTIPLY);
					currentText.setTextColor(Color.argb((int) ((1 - mSelectionOffset) * 255), 69, 192, 26));
				}
			}

			canvas.drawRect(left + mIndicatorPadding, height, right - mIndicatorPadding,
					height - mBottomLinePaint.getStrokeWidth() + 2, mIndicatorPaint);

		}

		 mBottomLinePaint.setColor(mBottomLineColor);
		 canvas.drawLine(0, height - mBottomLinePaint.getStrokeWidth(),
		 getWidth(), height - mBottomLinePaint.getStrokeWidth(),
		 mBottomLinePaint);
	}

	private class MyOnClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			invalidate();
			if (mOnTabClickListener != null) {
				mOnTabClickListener.onTabClick(Integer.parseInt(String.valueOf(v.getTag())));
			}
		}

	}

	@SuppressLint("ClickableViewAccessibility")
	private class MyOnTouchListener implements View.OnTouchListener {

		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getActionMasked()) {
			case MotionEvent.ACTION_DOWN:
				mPressingTab = Integer.parseInt(String.valueOf(v.getTag())) ;
				invalidate();
				break;
			case MotionEvent.ACTION_UP:
				mPressingTab = -1;
				invalidate();
				break;
			default:
				break;
			}
			return false;
		}

	}

	public static interface OnTabClickListener {
		void onTabClick(int position);
	}

	public void setTextColor(int textColor) {
		this.mTextColor = textColor;
	}

	public void setTextSelectedColor(int textSelectedColor) {
		this.mTextSelectedColor = textSelectedColor;
	}

	public void setTextSize(float textSize) {
		this.mTextSize = textSize;
	}

	public void setIndicatorColor(int indicatorColor) {
		this.mIndicatorColor = indicatorColor;
	}

	public void setIndicatorHeight(int indicatorHeight) {
		this.mIndicatorHeight = indicatorHeight;
	}

	public void setIndicatorPadding(int indicatorPadding) {
		this.mIndicatorPadding = indicatorPadding;
	}

	public void setBottomLineColor(int bottomLineColor) {
		this.mBottomLineColor = bottomLineColor;
	}

	public void setPressingColor(int pressingColor) {
		this.mPressingColor = pressingColor;
	}

}
