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

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;
import static com.example.oneplayer.R.drawable.sidemenu_sky;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    String theme;
    private boolean toggle, dark = false;

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    ActionBarDrawerToggle actionBarDrawerToggle;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ImageView button;
    Themes themes = new Themes();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showMessage("New Update: Themes", true);//for sending update logs in a toast

        toggle = true; theme = "default";
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
                toggle = !toggle;
            }
            else {
                showMessage("Hardware Accelration Disabled", false);
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

            changeThemes(themes.getTheme());

        } else if (item.getItemId() == R.id.darkTheme) {
            //if (themes.getDark()) return true;
            //else {
                try {
                    bg.setBackgroundResource(R.drawable.bg_dark);
                    title1.setTextColor(Color.WHITE);
                    intersting.setTextColor(Color.WHITE);
                    instruction.setTextColor(Color.WHITE);
                    button.setBackgroundResource(R.drawable.play_sky);
                    themes.setDark(true);
                } catch (NullPointerException e) {
                    bgab.setBackgroundResource(R.drawable.bg_dark);
                    title2.setTextColor(Color.WHITE);
                    description2.setTextColor(Color.WHITE);
                    beta.setTextColor(Color.WHITE);
                    themes.setDark(true);
                }
                //themes.setDark(true);
            //}


        }
        return true;

    }

    public void changeThemes(String theme) {
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

        if (theme.equals("default")) {
            try {
                bg.setBackgroundResource(R.drawable.bg_2_orange);
                button.setBackgroundResource(R.drawable.play_orange);
                //if (themes.getDark()) {
                    title1.setTextColor(Color.BLACK);
                    intersting.setTextColor(Color.BLACK);
                    instruction.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            } catch (NullPointerException e) {
                bgab.setBackgroundResource(R.drawable.bg_2_orange);
                //if (themes.getDark()) {
                    title2.setTextColor(Color.BLACK);
                    description2.setTextColor(Color.BLACK);
                    beta.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            }
            themes.setTheme("orange");

        } else if (theme.equals("orange")) {

            try {
                bg.setBackgroundResource(R.drawable.bg_2_purple);
                button.setBackgroundResource(R.drawable.play_purp);
                //if (themes.getDark()) {
                    title1.setTextColor(Color.BLACK);
                    intersting.setTextColor(Color.BLACK);
                    instruction.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            } catch (NullPointerException e) {
                bgab.setBackgroundResource(R.drawable.bg_2_purple);
                //if (dark) {
                    title2.setTextColor(Color.BLACK);
                    description2.setTextColor(Color.BLACK);
                    beta.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            }
            themes.setTheme("purple");

        } else if (theme.equals("purple")) {
            try {
                bg.setBackgroundResource(R.drawable.bg_2);
                button.setBackgroundResource(R.drawable.play_sky);
                //if (themes.getDark()) {
                    title1.setTextColor(Color.BLACK);
                    intersting.setTextColor(Color.BLACK);
                    instruction.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            } catch (NullPointerException e) {
                bgab.setBackgroundResource(R.drawable.bg_2);
                //if (dark) {
                    title2.setTextColor(Color.BLACK);
                    description2.setTextColor(Color.BLACK);
                    beta.setTextColor(Color.BLACK);
                    themes.setDark(!themes.getDark());
                //}
            }
            themes.setTheme("default");
        }


    }


    @SuppressLint("ShowToast")
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