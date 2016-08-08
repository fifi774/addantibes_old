package com.addantibes.addantibes;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by fifi on 19/07/16.
 */
public class class_venir extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.layout_venir, container, false);

        final Button button = (Button) myView.findViewById(R.id.button_eglise);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.fr/maps/place/Assembl%C3%A9e+de+Dieu+d'Antibes/@43.5806965,7.1126265,15z/data=!4m5!3m4!1s0x0:0xb061ec9219b55d49!8m2!3d43.5806965!4d7.1126265"));
                startActivity(intent);
            }
        });

        return myView;
    }
}
