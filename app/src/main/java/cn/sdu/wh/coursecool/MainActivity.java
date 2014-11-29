package cn.sdu.wh.coursecool;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import cn.sdu.wh.coursecool.User.UserActivity;

public class MainActivity extends Activity implements OnClickListener,
        OnPageChangeListener{

    private static final String TAG = "ScrollMenu---->";
    /** 各菜单TextView控件 */
    private TextView Head01;
    private TextView Head02;
    private TextView Head03;
    private TextView Head04;
    private TextView curTxt;
    private View imgsearch;
    private View imguser;
    private PopupWindow popupWindow;
    private View barbg;
    private int page;
    private ListView list;
    private ArrayList viewlist;
    private ViewPager viewPager;
    /** 顶部横向ScrollView */
    private HorizontalScrollView horiScroll;
    private ViewPager vPager_Sc;
    /** ViewPager内容数据列表 */
    private List<View> pageList;
    /** 移动小方框 */
    private ImageView imgTransBg;
    /** 自定义ViewPager适配器 */
    private MainPageAdapter mAdapter;
    /** 手机屏幕的宽度 */
    private int disWidth;
    /** 调整ScrollView位置时计算的偏差 */
    private int offset = 0;
    /** 是否已经计算了偏差的标识 */
    private boolean hasOffset = false;


    //调试界面用数据
    private String []t_name={"《庄子》释要","《诗经》的世界","中古文明与宋词","言语交际艺术","中国审美文化史","艺术概论","文学的视觉化","外国文学名作欣赏","中西文化比较"};
    private String []t_teacher={"聂中庆","姜亚林","姜亚林","高万云","许丙泉","洪树华","管恩森","仵从巨","胡志明","胡志明"};
    private String []t_time={"周四@5-6","周六@2-3","周三@5-6","周一@5-6","周三@5-6","周四@5-6","周三@5-6","周四@5-6","周三@5-6","周四@5-6","周四@5-6"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        setPageAdapter();
        //txk_list();
    }

    private void initView() {
        horiScroll = (HorizontalScrollView) findViewById(R.id.horiScroll);
        vPager_Sc = (ViewPager) findViewById(R.id.vPager_Sc);
        imgTransBg = (ImageView) findViewById(R.id.imgTransBg);
        Head01 = (TextView) findViewById(R.id.Head01);
        Head02 = (TextView) findViewById(R.id.Head02);
        Head03 = (TextView) findViewById(R.id.Head03);
        Head04 = (TextView) findViewById(R.id.Head04);
        imgsearch=(View) findViewById(R.id.imgsearch);
        imguser=(View) findViewById(R.id.imgbarop);
        barbg=(View)findViewById(R.id.barbg);
        Head01.setOnClickListener(this);
        Head02.setOnClickListener(this);
        Head03.setOnClickListener(this);
        Head04.setOnClickListener(this);
        imguser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                startActivity(new Intent(MainActivity.this, UserActivity.class));
            }
        });
        imgsearch.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                showPopUp(v);
            }
        });

        vPager_Sc.setOnPageChangeListener(this);
        // 当前TextView默认为第一个
        curTxt = Head01;
        // 设置默认位置的字体颜色为白色的选中效果
        // 获取手机屏幕宽度
        disWidth = getWindowManager().getDefaultDisplay().getWidth();
        Log.i(TAG, "手机屏幕宽度disWidth:" + disWidth);
    }
    /** 设置ViewPager数据适配器 */
    private void setPageAdapter() {
        pageList = PageDataUtil.getPageList(this);
        mAdapter = new MainPageAdapter(pageList);
        vPager_Sc.setAdapter(mAdapter);
    }

    /**
     * 设置小方框的移动动画效果(没计算偏差,但基本效果能完全实现)
     *
     * @param endTxt
     */
    private void imgTrans(TextView endTxt) {
        // 当前TextView的中心点
        int startMid = curTxt.getLeft() + curTxt.getWidth() / 2;
        // 移动开始位置左边缘
        int startLeft = startMid - imgTransBg.getWidth() / 2;
        // 目的TextView的中心点
        int endMid = endTxt.getLeft() + endTxt.getWidth() / 2;
        // 移动结束位置左边缘
        int endLeft = endMid - imgTransBg.getWidth() / 2;
        // 构造动画
        TranslateAnimation move = new TranslateAnimation(startLeft, endLeft, 0,0);
        move.setDuration(100);
        move.setFillAfter(true);
        imgTransBg.startAnimation(move);

		/*
		 * 以下步骤用于处理ScrollView根据滑块的位置来调整自身的滚动, 以便达到更好的视觉效果
		 */
        int[] location = new int[2];
        // 获取目的TextViw在当前屏幕中的坐标点,主要用到X轴方向坐标:location[0]
        endTxt.getLocationOnScreen(location);
        // 调整ScrollView的位置
        if (location[0] < 0) {
            // 目的位置超出左边屏幕,则调整到紧靠该位置的左边
            // 此处ScrollView直接根据位置点滑动
            horiScroll.smoothScrollTo(endTxt.getLeft(), 0);
        } else if ((location[0] + endTxt.getWidth()) > disWidth) {
            // 目的位置超出右边屏幕,则调整到紧靠该位置的右边
            // 此处ScrollView需计算滑动距离
            horiScroll.smoothScrollBy(location[0] + endTxt.getWidth()
                    - disWidth, 0);
        } else {
            // 此处如果没超出屏幕,也需保持原地滑动
            // 如果不加该效果,则滑块可能出现动画延迟或停滞
            horiScroll.smoothScrollTo(horiScroll.getScrollX(), 0);
        }

        // 切换字体颜色

        // 更新当前TextView的记录
        curTxt = endTxt;
    }

    /** 偏差计算的时候用到,不用则可以忽略 */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
			/*
			 * 计算getLocationOnScreen()可能带来的偏差:
			 * 因为getLocationOnScreen()方法要等UI绘制完才能获取正确的值, 所以不能在onCreate()中直接计算,
			 * 此处设置一个boolean标识,在第一次触动屏幕时获取一次
			 */
                if (curTxt == Head01 && !hasOffset) {
                    // 获取第一个TextView的坐标值
                    int[] heaDlocal = new int[2];
                    curTxt.getLocationOnScreen(heaDlocal);
                    // x轴方向偏差值
                    offset = heaDlocal[0];
                    Log.i(TAG, "偏差值offset:" + offset);
                    hasOffset = true;
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 设置小方框的移动动画效果(计算了偏差,细节效果更好), 此处的偏差需要演示的时候才能观察到
     *
     * @param endTxt
     */
    private void imgTransMod(TextView endTxt) {
        // 当前TextView的中心点
        int startMid = curTxt.getLeft() + curTxt.getWidth() / 2;
        // 移动开始位置左边缘
        int startLeft = startMid - imgTransBg.getWidth() / 2;
        // 目的TextView的中心点
        int endMid = endTxt.getLeft() + endTxt.getWidth() / 2;
        // 移动结束位置左边缘
        int endLeft = endMid - imgTransBg.getWidth() / 2;
        // 构造动画
        TranslateAnimation move = new TranslateAnimation(startLeft, endLeft, 0,
                0);
        move.setDuration(100);
        move.setFillAfter(true);
        imgTransBg.startAnimation(move);

		/*
		 * 以下步骤用于处理ScrollView根据滑块的位置来调整自身的滚动,以便达到更好的视觉效果
		 */
        int[] location = new int[2];
        // 获取目的TextViw在当前屏幕中的坐标点,主要用到X轴方向坐标:location[0]
        endTxt.getLocationOnScreen(location);

        // 调整ScrollView的位置
        if ((location[0] - offset) < 0) {
            Log.i(TAG, "超出左屏幕");
            // 目的位置超出左边屏幕,则调整到紧靠该位置的左边
            // 此处ScrollView直接根据位置点滑动
            horiScroll.smoothScrollTo(endTxt.getLeft(), 0);
        } else if ((location[0] + endTxt.getWidth()) > disWidth) {
            Log.i(TAG, "超出右屏幕");
            Log.i(TAG, "移动像素点:"
                    + (location[0] + offset + endTxt.getWidth() - disWidth));
            // 目的位置超出右边屏幕,则调整到紧靠该位置的右边
            // 此处ScrollView需计算滑动距离
            horiScroll.smoothScrollBy(location[0] + offset + endTxt.getWidth()
                    - disWidth, 0);
        } else {
            // 如果没超出屏幕,也需保持原地滑动
            // 若不加该效果,则滑块可能出现动画延迟或停滞
            horiScroll.smoothScrollTo(horiScroll.getScrollX(), 0);
        }

        // 切换字体颜色

        // 更新当前TextView的记录
        curTxt = endTxt;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Head01:
                // 启动滑块移动动画
                imgTransMod(Head01);
                // 更新Page页
                vPager_Sc.setCurrentItem(0);
                barbg.setBackgroundResource(R.drawable.bar_bg1);
                page=1;
                break;
            case R.id.Head02:
                imgTransMod(Head02);
                vPager_Sc.setCurrentItem(1);
                barbg.setBackgroundResource(R.drawable.bar_bg2);
                page=2;
                break;
            case R.id.Head03:
                imgTransMod(Head03);
                vPager_Sc.setCurrentItem(2);
                barbg.setBackgroundResource(R.drawable.bar_bg3);
                page=3;
                break;
            case R.id.Head04:
                imgTransMod(Head04);
                vPager_Sc.setCurrentItem(3);
                barbg.setBackgroundResource(R.drawable.bar_bg4);
                page=4;
                break;
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        switch (arg0) {
            case 0:
                // 直接关联点击事件
                Head01.performClick();
                barbg.setBackgroundResource(R.drawable.bar_bg1);
                page=1;
                break;
            case 1:
                Head02.performClick();
                barbg.setBackgroundResource(R.drawable.bar_bg2);
                page=2;
                break;
            case 2:
                Head03.performClick();
                barbg.setBackgroundResource(R.drawable.bar_bg3);
                page=3;
                break;
            case 3:
                Head04.performClick();
                barbg.setBackgroundResource(R.drawable.bar_bg4);
                page=4;
                break;
        }
    }


    private void showPopUp(View v) {
        View popupView = getLayoutInflater().inflate(R.layout.tong_search, null);
        popupWindow = new PopupWindow(popupView, LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        View S_bg=popupView.findViewById(R.id.s_bg);
        switch (page) {
            case 1:
                S_bg.setBackgroundResource(R.drawable.search_bg1);
                break;
            case 2:
                S_bg.setBackgroundResource(R.drawable.search_bg2);
                break;
            case 3:
                S_bg.setBackgroundResource(R.drawable.search_bg3);
                break;
            case 4:
                S_bg.setBackgroundResource(R.drawable.search_bg4);
                break;
            default:
                break;
        }
        popupWindow.showAsDropDown(v);
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    }



    //通选课指南页面的函数
//	private void txk_list(){
//		LayoutInflater inflater = getLayoutInflater();
//
//		viewPager = (ViewPager) findViewById(R.id.vPager_Sc);
//
//		viewlist = new ArrayList<View>();
//
//		View view1 = inflater.inflate(R.layout.page_content1, null);
//		View view2 = inflater.inflate(R.layout.page_content2, null);
//		View view3 = inflater.inflate(R.layout.page_content2, null);
//		View view4 = inflater.inflate(R.layout.page_content2, null);
//		ListView listview1 = (ListView) view1.findViewById(R.id.page1);
//		ListView listview2 = (ListView) view2.findViewById(R.id.page2);
//		ListView listview3 = (ListView) view3.findViewById(R.id.page3);
//		ListView listview4 = (ListView) view4.findViewById(R.id.page4);
//		viewlist.add(view1);
//		viewlist.add(view2);
//		viewlist.add(view3);
//		viewlist.add(view4);


    //生成动态数组，并且转载数据
//       ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
//       for(int i=0;i<3;i++){
//            HashMap<String, Object>map = new HashMap<String, Object>();
//            map.put(t_name[i],"n");
//            map.put(t_teacher[i],"te");
//            map.put(t_time[i],"ti");
//            listItem.add(map);
//       }
//       SimpleAdapter adapter = new SimpleAdapter(
//    		    this,
//    		    listItem,
//    		    R.layout.course_cell,
//				new String[]{"n","te","ti"},
//				new int[]{R.id.name,R.id.teacher,R.id.time});
    //@     	viewPager.setAdapter(new MyPagerAdapter(this, viewlist));

    //@    viewPager.setAdapter(adapter);
    //      viewPager.setCurrentItem(0);
//       listview2.setAdapter(adapter);
//       listview3.setAdapter(adapter);
//       listview4.setAdapter(adapter);
//	}



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
