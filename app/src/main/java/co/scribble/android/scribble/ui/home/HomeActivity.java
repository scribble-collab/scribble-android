package co.scribble.android.scribble.ui.home;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import co.scribble.android.scribble.C;
import co.scribble.android.scribble.databinding.ActivityHomeBinding;
import co.scribble.android.scribble.ui.home.adapter.MainPagerAdapter;
import co.scribble.android.scribble.ui.write.CreateActivity;

public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.fabCreateStory.setOnClickListener(v ->
                startActivity(new Intent(HomeActivity.this, CreateActivity.class)));

        ViewPager viewPager = binding.viewpager;
        viewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));

        TabLayout tabs = binding.tabs;
        tabs.setupWithViewPager(viewPager);

        for(int i = 0; i < tabs.getTabCount(); i++)
            tabs.getTabAt(i).setIcon(C.TAB_ICONS[i]);
    }
}