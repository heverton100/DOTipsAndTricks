package com.example.dotipsandtricks.ui.skilltree;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.dotipsandtricks.R;

public class CategoryPPsFragment extends Fragment {

    CardView cv,cv2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View root = inflater.inflate(R.layout.fragment_category_pps, container, false);

        cv = root.findViewById(R.id.cvsk001);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i=new Intent(root.getContext(), SkilltreeActivity.class);
            root.getContext().startActivity(i);
            }
        });

        cv2 = root.findViewById(R.id.cvsk002);
        cv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent i=new Intent(root.getContext(), PointsPilotActivity.class);
            root.getContext().startActivity(i);
            }
        });

        return root;
    }

}