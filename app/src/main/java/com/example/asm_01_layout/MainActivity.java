package com.example.asm_01_layout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.asm_01_layout.fragment.ChiFragment;
import com.example.asm_01_layout.fragment.GioiThieuFragment;
import com.example.asm_01_layout.fragment.ThongKeFragment;
import com.example.asm_01_layout.fragment.ThuFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    NavigationView navigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        navigationView = findViewById(R.id.navigationview);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_baseline_format_list_bulleted_24);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setTitle("");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                xulytuychon(item);
                return false;
            }
        });
    }

    private void xulytuychon(MenuItem item) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        switch (item.getItemId()){
            case R.id.menu_khoangchi:
                fragmentManager.beginTransaction().replace(R.id.fragmnet_context, ChiFragment.newInstance(null,null)).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_khoangthu:
                fragmentManager.beginTransaction().replace(R.id.fragmnet_context, ThuFragment.newInstance(null,null)).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_thongke:
                fragmentManager.beginTransaction().replace(R.id.fragmnet_context, ThongKeFragment.newInstance(null,null)).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_gioithieu:
                fragmentManager.beginTransaction().replace(R.id.fragmnet_context, GioiThieuFragment.newInstance(null,null)).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
                break;
            case R.id.menu_thoat:
                System.exit(0);
                break;
            default:
                fragmentManager.beginTransaction().replace(R.id.fragmnet_context, ThuFragment.newInstance(null,null)).commit();
                drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home){
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onOptionsItemSelected(item);
    }
}