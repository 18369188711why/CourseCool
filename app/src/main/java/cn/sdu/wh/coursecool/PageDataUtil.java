package cn.sdu.wh.coursecool;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class PageDataUtil {
	/**
	 * ��ȡViewPager��������б�
	 * @param context
	 * @return
	 */
	public static List<View> getPageList(Context context){
		List<View> list = new ArrayList<View>();
		list.add(createPage(context, R.layout.page_content1));
		list.add(createPage(context, R.layout.page_content2));
		list.add(createPage(context, R.layout.page_content3));
		list.add(createPage(context, R.layout.page_content4));
		return list;
	}
	
	/**
	 * ����Pageҳ
	 * @param context
	 * @param layouResId
	 * @return
	 */
	private static View createPage(Context context,int layouResId){
		LayoutInflater inflater = LayoutInflater.from(context);
		return inflater.inflate(layouResId, null);
	}
}
