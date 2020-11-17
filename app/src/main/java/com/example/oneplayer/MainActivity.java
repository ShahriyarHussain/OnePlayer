package com.example.oneplayer;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.exoplayer2.extractor.ts.TsPayloadReader;
import com.google.android.material.navigation.NavigationView;

import static com.example.oneplayer.R.drawable.play_sky;
import static com.example.oneplayer.R.drawable.sidemenu_sky;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private boolean toggle;
    int theme;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageView button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMessage("New Update: Themes", true);//for sending update logs in a toast

        toggle = true; theme = 0;
        button = findViewById(R.id.button);
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.drawer);
        navigationView = findViewById(R.id.navigationView);

        toolbar.setNavigationIcon(sidemenu_sky);
        navigationView.setNavigationItemSelectedListener(this);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.open, R.string.close);
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);
        actionBarDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.END);
            }
        });
        actionBarDrawerToggle.setHomeAsUpIndicator(sidemenu_sky);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer, new FragmentMain());
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.END);
        TextView title1, title2, description2, intersting, instruction, beta;
        title1 = findViewById(R.id.title1);
        title2 = findViewById(R.id.title2);
        description2 = findViewById(R.id.desc);
        intersting = findViewById(R.id.interest1);
        instruction = findViewById(R.id.menu_instruction);
        beta = findViewById(R.id.beta);
        ImageView bg, bgab;
        bg = findViewById(R.id.background1);
        button = findViewById(R.id.button);
        bgab = findViewById(R.id.background2);

        if (item.getItemId() == R.id.enable_hw) {
            if (toggle) {
                showMessage("Hardware Accelration Enabled", false);
                //Toast.makeText(getApplicationContext(), "Hardware Accelration Enabled", Toast.LENGTH_SHORT).show();
                toggle = !toggle;
            }
            else {
                showMessage("Hardware Accelration Disabled", false);
                //Toast.makeText(getApplicationContext(), "Hardware Accelration Disabled", Toast.LENGTH_SHORT).show();
                toggle = !toggle;
            }
        } else if (item.getItemId() == R.id.about) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, new FragmentActivity());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.home) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragmentContainer, new FragmentMain());
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        } else if (item.getItemId() == R.id.changeTheme) {
            if (theme == 0) {
                try {
                    bg.setBackgroundResource(R.drawable.bg_2_orange);
                    button.setBackgroundResource(R.drawable.play_orange);
                } catch (NullPointerException e) {
                    bgab.setBackgroundResource(R.drawable.bg_2_orange);
                }
                theme = 1;

            } else if (theme == 1) {
                try {
                    bg.setBackgroundResource(R.drawable.bg_2);
                    button.setBackgroundResource(R.drawable.play_sky);
                } catch (NullPointerException e) {
                    bgab.setBackgroundResource(R.drawable.bg_2);
                }
                theme = 2;
            } else {
                try {
                    bg.setBackgroundResource(R.drawable.bg_2_purple);
                    button.setBackgroundResource(R.drawable.play_purp);
                    if (theme == 4) {
                        title1.setTextColor(Color.BLACK);
                        intersting.setTextColor(Color.BLACK);
                        instruction.setTextColor(Color.BLACK);
                    }
                } catch (NullPointerException e) {
                    bgab.setBackgroundResource(R.drawable.bg_2_purple);
                    title2.setTextColor(Color.BLACK);
                    description2.setTextColor(Color.BLACK);
                    beta.setTextColor(Color.BLACK);
                }
                theme = 0;
            }
        } else if (item.getItemId() == R.id.darkTheme) {
            try {
                bg.setBackgroundResource(R.drawable.bg_dark);
                title1.setTextColor(Color.WHITE);
                intersting.setTextColor(Color.WHITE);
                instruction.setTextColor(Color.WHITE);
                button.setBackgroundResource(R.drawable.play_sky);
            } catch (NullPointerException e) {
                bgab.setBackgroundResource(R.drawable.bg_dark);
                title2.setTextColor(Color.WHITE);
                description2.setTextColor(Color.WHITE);
                beta.setTextColor(Color.WHITE);
            }
            theme = 4;
        }
        return true;

    }


    private void showMessage(String msg, boolean b)    {
        Toast toast;
        if (b) {
            toast = Toast.makeText(MainActivity.this, " " + msg + " ", Toast.LENGTH_LONG);
        } else {
            toast = Toast.makeText(MainActivity.this, " " + msg + " ", Toast.LENGTH_SHORT);
        }
        View view = toast.getView();

        TextView text = (TextView)view.findViewById(android.R.id.message);

        if (Build.VERSION.SDK_INT>= Build.VERSION_CODES.M) {
            text.setTextAppearance(R.style.montserrat_font);
        }
        toast.show();
    }

}