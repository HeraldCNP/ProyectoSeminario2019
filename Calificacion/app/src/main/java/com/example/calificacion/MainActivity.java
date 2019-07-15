package com.example.calificacion;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    LinearLayout rating;
    CheckBox star;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rating = (LinearLayout)findViewById(R.id.ratings);
        for (int i=1;i<=5;i++){
            star = (CheckBox)rating.findViewWithTag(String.valueOf(i));
            star.setOnClickListener(starsListener);
        }

    }
    private View.OnClickListener starsListener = new View.OnClickListener(){
        public void onClick(View view){
            int tag = Integer.valueOf((String)view.getTag());

            for(int i=1;i<tag;i++){
                star = (CheckBox) rating.findViewWithTag(String.valueOf(i));
                star.setChecked(true);
            }
            for(int i=tag+1;i<=5;i++){
                star=(CheckBox)rating.findViewWithTag(String.valueOf(i));
                star.setChecked(false);
            }

        }
    };

}
