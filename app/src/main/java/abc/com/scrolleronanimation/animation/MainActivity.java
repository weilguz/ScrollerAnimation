package abc.com.scrolleronanimation.animation;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import abc.com.scrolleronanimation.R;

/**
 * 仿微信首页 滑动viewPager时底部的button效果
 *
 * 根据viewPager滑动时当前页面滑动百分比进行alpha的渐变
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private List<Fragment> mFragments = new ArrayList<>();
    private TextView tv_ff;
    private TextView tv_find;
    private TextView tv_me;
    private TextView tv_message;
    private TextView tv_ff_select;
    private TextView tv_find_select;
    private TextView tv_me_select;
    private TextView tv_message_select;
    private ViewPager vp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();
    }

    private void initData() {
        MessageFragment messageFragment = new MessageFragment();
        MeFragment meFragment = new MeFragment();
        FindFargment findFargment = new FindFargment();
        FfFragment ffFragment = new FfFragment();
        mFragments.add(messageFragment);
        mFragments.add(findFargment);
        mFragments.add(ffFragment);
        mFragments.add(meFragment);
    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp_content);
        RelativeLayout ll_ff = (RelativeLayout) findViewById(R.id.ll_ff);
        RelativeLayout ll_find = (RelativeLayout) findViewById(R.id.ll_find);
        RelativeLayout ll_me = (RelativeLayout) findViewById(R.id.ll_me);
        RelativeLayout ll_message = (RelativeLayout) findViewById(R.id.ll_message);

        tv_ff = (TextView) findViewById(R.id.tv_ff);
        tv_find = (TextView) findViewById(R.id.tv_find);
        tv_me = (TextView) findViewById(R.id.tv_me);
        tv_message = (TextView) findViewById(R.id.tv_message);

        tv_ff_select = (TextView) findViewById(R.id.tv_ff_select);
        tv_find_select = (TextView) findViewById(R.id.tv_find_select);
        tv_me_select = (TextView) findViewById(R.id.tv_me_select);
        tv_message_select = (TextView) findViewById(R.id.tv_message_select);

        ll_ff.setOnClickListener(this);
        ll_find.setOnClickListener(this);
        ll_me.setOnClickListener(this);
        ll_message.setOnClickListener(this);

        vp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });
        vp.setCurrentItem(0);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //positionOffset 往左滑,positionOffset从0递增,往右滑,positionOffset从1递减
                //根据viewPager滑动偏移百分比更改透明度
                int addAlpha = (int)(255 * positionOffset);//左滑时增加,右滑时减少
                int reduceAlpha = (int) (255 * (1-positionOffset));//左滑时减少,右滑时增加

                //从左往右滑动,position从0 ~ mFragments.size()-1;
                //从右往左滑动,position从mFragments.size()-2 ~ 0;
                switch (position){
                    case 0: //只能左滑 偏移百分比逐渐变大
                        tv_message.setAlpha(addAlpha);
                        tv_message_select.setAlpha(reduceAlpha);

                        tv_find.setAlpha(reduceAlpha);
                        tv_find_select.setAlpha(addAlpha);
                        //红减黑加
                        tv_message_select.setTextColor(Color.argb(reduceAlpha,255,64,129));
                        tv_message.setTextColor(Color.argb(addAlpha,0,0,0));

                        //黑减红加
                        tv_find.setTextColor(Color.argb(reduceAlpha,0,0,0));
                        tv_find_select.setTextColor(Color.argb(addAlpha,255,64,129));

                        break;
                    case 1: //左右滑动
                        tv_find.setAlpha(addAlpha);
                        tv_find_select.setAlpha(reduceAlpha);

                        tv_ff.setAlpha(reduceAlpha);
                        tv_ff_select.setAlpha(addAlpha);

                        tv_find.setTextColor(Color.argb(addAlpha,0,0,0));
                        tv_find_select.setTextColor(Color.argb(reduceAlpha,255,64,129));
//
                        tv_ff.setTextColor(Color.argb(reduceAlpha,0,0,0));
                        tv_ff_select.setTextColor(Color.argb(addAlpha,255,64,129));

                        break;
                    case 2: //左右滑动
                        tv_ff.setAlpha(addAlpha);
                        tv_ff_select.setAlpha(reduceAlpha);

                        tv_me.setAlpha(reduceAlpha);
                        tv_me_select.setAlpha(addAlpha);

                        tv_ff.setTextColor(Color.argb(addAlpha,0,0,0));
                        tv_ff_select.setTextColor(Color.argb(reduceAlpha,255,64,129));

                        tv_me_select.setTextColor(Color.argb(addAlpha,255,64,129));
                        tv_me.setTextColor(Color.argb(reduceAlpha,0,0,0));

                        break;
                    case 3: //
                        /*tv_ff.setAlpha(addAlpha);
                        tv_ff_select.setAlpha(reduceAlpha);

                        tv_find.setAlpha(reduceAlpha);
                        tv_find_select.setAlpha(addAlpha);

                        tv_ff_select.setTextColor(Color.argb(reduceAlpha,255,64,129));
                        tv_ff.setTextColor(Color.argb(addAlpha,0,0,0));

                        tv_find.setTextColor(Color.argb(reduceAlpha,0,0,0));
                        tv_find_select.setTextColor(Color.argb(addAlpha,255,64,129));*/
                        break;

                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_message:
                vp.setCurrentItem(0);
                changeState(v.getId());
                break;
            case R.id.ll_find:
                vp.setCurrentItem(1);
                changeState(v.getId());
                break;
            case R.id.ll_ff:
                vp.setCurrentItem(2);
                changeState(v.getId());
                break;
            case R.id.ll_me:
                vp.setCurrentItem(3);
                changeState(v.getId());
                break;
        }
    }

    private void changeState(int id) {
        switch (id){
            case R.id.ll_message:
                tv_message.setAlpha(0);
                tv_message_select.setAlpha(1);
                tv_find.setAlpha(1);
                tv_find_select.setAlpha(0);
                tv_ff.setAlpha(1);
                tv_ff_select.setAlpha(0);
                tv_me.setAlpha(1);
                tv_me_select.setAlpha(0);
                break;
            case R.id.ll_find:
                tv_message.setAlpha(1);
                tv_message_select.setAlpha(0);
                tv_find.setAlpha(0);
                tv_find_select.setAlpha(1);
                tv_ff.setAlpha(1);
                tv_ff_select.setAlpha(0);
                tv_me.setAlpha(1);
                tv_me_select.setAlpha(0);
                break;
            case R.id.ll_ff:
                tv_message.setAlpha(1);
                tv_message_select.setAlpha(0);
                tv_find.setAlpha(1);
                tv_find_select.setAlpha(0);
                tv_ff.setAlpha(0);
                tv_ff_select.setAlpha(1);
                tv_me.setAlpha(1);
                tv_me_select.setAlpha(0);
                break;
            case R.id.ll_me:
                tv_message.setAlpha(1);
                tv_message_select.setAlpha(0);
                tv_find.setAlpha(1);
                tv_find_select.setAlpha(0);
                tv_ff.setAlpha(1);
                tv_ff_select.setAlpha(0);
                tv_me.setAlpha(0);
                tv_me_select.setAlpha(1);
                break;

        }
    }
}
