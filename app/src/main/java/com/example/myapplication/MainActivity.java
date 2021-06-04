package com.example.myapplication;

import android.os.Bundle;
import android.view.View;

import com.example.myapplication.ui.news.NewsFragment;
import com.example.myapplication.ui.weather.WeatherFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NavController navController;
    private List<Integer> topLevelDestinations = Arrays.asList(
            R.id.homeFragment,
            R.id.newsListFragment,
            R.id.weatherFragment
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initNavigation();
    }

    private void initNavigation(){
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                new HashSet(topLevelDestinations)).build();
        NavHostFragment navHostFragment =  (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
        this.navController = navHostFragment.getNavController();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
        navController.addOnDestinationChangedListener((controller, destination, arguments) -> {
            boolean isVisible = topLevelDestinations.contains(destination.getId());
            if(isVisible) {
                navView.setVisibility(View.VISIBLE);
            } else {
                navView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}