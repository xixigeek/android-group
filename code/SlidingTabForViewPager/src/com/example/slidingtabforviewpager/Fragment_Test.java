package com.example.slidingtabforviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Fragment Test FaragMent
 * 
 * @author 门前大桥下 ❀
 *
 */
public class Fragment_Test extends Fragment {

	public int index = 0;
	public TextView tv;
	public View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mView = inflater.inflate(R.layout.test, container, false);
		tv = (TextView) mView.findViewById(R.id.testText);
		tv.setText(getString(R.string.test) + index);

		return mView;
	}

	public static Fragment newInstance(int i) {
		Fragment_Test f = new Fragment_Test();
		f.index = i;
		return f;
	}
}
