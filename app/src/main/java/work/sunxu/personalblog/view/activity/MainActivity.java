package work.sunxu.personalblog.view.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import java.util.ArrayList;
import java.util.List;
import work.sunxu.personalblog.bean.Blog;
import work.sunxu.personalblog.R;
import work.sunxu.personalblog.view.fragment.BlogItemFragment;

/**
 * 主页面 activity
 */
public class MainActivity extends AppCompatActivity {

    private final String ANDROID = "Android";
    private final String CS = "CS";
    private final String THINKING = "Thinking";
    private final String ABOUT = "About";

    private final String sTabs[] = new String[] { ANDROID, CS, THINKING, ABOUT };

    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    private List<BlogItemFragment> mBlogItemFragmentList;


    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }


    private void initData() {

        // init bomb
        Bmob.initialize(this, "79da03a25dd74d553acdfcfe9b8aee01");

        BmobQuery<Blog> query = new BmobQuery<>();
        query.findObjects(new FindListener<Blog>() {
            @Override public void done(List<Blog> list, BmobException e) {
                if (list != null && list.size() != 0) {

                    // init 4 category Blog bean
                    ArrayList<Blog> androidCategory = new ArrayList<>();
                    ArrayList<Blog> csCategory = new ArrayList<>();
                    ArrayList<Blog> thinkingCategory = new ArrayList<>();
                    ArrayList<Blog> aboutCategory = new ArrayList<>();

                    for (Blog bean : list) {
                        switch (bean.getCategory()) {
                            case ANDROID:
                                androidCategory.add(bean);
                                break;
                            case CS:
                                csCategory.add(bean);
                                break;
                            case THINKING:
                                thinkingCategory.add(bean);
                                break;
                            case ABOUT:
                                aboutCategory.add(bean);
                                break;
                        }
                    }

                    initFragments(androidCategory, csCategory, thinkingCategory, aboutCategory);
                }
                else if (e != null) {

                    //TODO
                    e.printStackTrace();
                }
            }
        });
    }


    private void initFragments(ArrayList<Blog> androidCategory, ArrayList<Blog> csCategory, ArrayList<Blog> thinkingCategory, ArrayList<Blog> aboutCategory) {

        mBlogItemFragmentList = new ArrayList<>();
        mBlogItemFragmentList.add(BlogItemFragment.newInstace(androidCategory));
        mBlogItemFragmentList.add(BlogItemFragment.newInstace(csCategory));
        mBlogItemFragmentList.add(BlogItemFragment.newInstace(thinkingCategory));
        mBlogItemFragmentList.add(BlogItemFragment.newInstace(aboutCategory));

        mViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override public Fragment getItem(int position) {
                return mBlogItemFragmentList.get(position);
            }


            @Override public int getCount() {
                return sTabs.length;
            }


            @Override public CharSequence getPageTitle(int position) {
                return sTabs[position];
            }
        });
        mTabLayout.setupWithViewPager(mViewPager);
    }


    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mViewPager = findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tablayout);

        //setSupportActionBar(mToolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeButtonEnabled(true);
    }
}
