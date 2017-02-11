package me.blog.bluenotekr85.skillbook.smarttablayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.blog.bluenotekr85.skillbook.R;

/*
    build.gradle -> dependencies 추가
    compile 'com.ogaclejapan.smarttablayout:library:1.+@aar'
 */
public class SmartTabLayoutBook extends AppCompatActivity {

    private SmartTabLayout tab;
    private ViewPager vpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_tab_layout_book);
        // ---------- > Views
        tab    = (SmartTabLayout)findViewById(R.id.tab);
        vpager = (ViewPager)findViewById(R.id.vpager);
        // ---------- > ViewPagerAdapter 에 전달할 List 생성
        // ---------- > Generic 이 반드시 아래와 같을 필요는 없음
        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = null;
        // ---------- > List 에 담을 Fragment 와 기타 정보 (Title) Map 작성
        // ---------- > 3개의 탭 메뉴 (Page1, Page2, Page3) 생성
        map = new HashMap<String, Object>();
        map.put(ViewPagerAdapter.KEY_FRAGMENT, new Fragment());
        map.put(ViewPagerAdapter.KEY_TITLE, "Page 1");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put(ViewPagerAdapter.KEY_FRAGMENT, new Fragment());
        map.put(ViewPagerAdapter.KEY_TITLE, "Page 2");
        list.add(map);

        map = new HashMap<String, Object>();
        map.put(ViewPagerAdapter.KEY_FRAGMENT, new Fragment());
        map.put(ViewPagerAdapter.KEY_TITLE, "Page 3");
        list.add(map);
        // ---------- > assemble Adapter
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), list);
        vpager.setAdapter(adapter);
        tab.setViewPager(vpager);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {

        // ---------- > Map 의 String Key 값을 미리 static final 로 정의해둠
        public static final String KEY_FRAGMENT = "FRAGMENT";
        public static final String KEY_TITLE     = "TITLE";
        private List<Map<String, Object>> list;

        public ViewPagerAdapter(FragmentManager fm, List<Map<String, Object>> list) {
            super(fm);
            this.list = list;
        }
        // ---------- > FragmentPagerAdapter 의 기본 Implements Methods
        @Override // ---------- > 해당 position 의 Fragment 반환
        public Fragment getItem(int position) {
            if (list != null && list.get(position) != null && list.get(position).get(KEY_FRAGMENT) != null) return (Fragment)list.get(position).get(KEY_FRAGMENT);
            else return null;
        }

        @Override // ---------- > list count 반환
        public int getCount() {
            if (list != null) return list.size();
            else return 0;
        }
        // ---------- > 추가 Override Method
        @Override // ---------- > 탭 제목
        public CharSequence getPageTitle(int position) {
            if (list != null && list.get(position) != null && list.get(position).get(KEY_TITLE) != null) return (String)list.get(position).get(KEY_TITLE);
            else return null;
        }

    }

}