package gzoomswiperefresh;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.example.widgetpro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Bind(R.id.main_toolbar)
    Toolbar toolbar;

    @Bind(R.id.main_drawerlayout)
    DrawerLayout drawerLayout;

    @Bind(R.id.main_navigation)
    NavigationView navigationView;

    @Bind(R.id.main_tablayout)
    TabLayout tabLayout;

    @Bind(R.id.main_viewpager)
    ViewPager viewpager;

    List<Fragment>frag_list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.own_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle
                (this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_open);

        drawerLayout.addDrawerListener(toggle);//set方法已经过时了
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        initTablayout();
    }

    /**初始化tab的内容*/
    private void initTablayout() {
        List<String>tabs = new ArrayList<>();
        tabs.add("testFragemnt");
        ApiShowFragment f1 = new ApiShowFragment();
        frag_list.add(f1);
        TabFragmentAdapter adapter = new TabFragmentAdapter(getSupportFragmentManager(),frag_list,tabs);
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        tabLayout.setTabsFromPagerAdapter(adapter);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
