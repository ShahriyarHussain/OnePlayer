package com.example.oneplayer;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentMain extends Fragment {
    ImageView button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        button = view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                openVideoActivity();
            }
        });
        /*
        MainActivity themeChanger = new MainActivity();
        Themes themes = new Themes();

        themeChanger.changeThemes(themes.getTheme());*/

        return view;
    }

    public void openVideoActivity(){
        Intent intent = new Intent(getActivity(), VideoActivity.class);
        startActivity(intent);
    }
}
