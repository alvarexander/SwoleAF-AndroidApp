import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> arrayList;
    TextView cat;
    LinearLayout linearLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // LinearLayout that we will add views to
        linearLayout = (LinearLayout) findViewById(R.id.mainLinear);



        arrayList = new ArrayList<String>();

        // Random stuff to put into contents of your new views
        arrayList.add("Workout 1");
        arrayList.add("Workout 2");
        arrayList.add("Workout 3");
        arrayList.add("Workout 4");
        arrayList.add("Workout 5");
        arrayList.add("Workout 1");
        arrayList.add("Workout 2");


        // Create a LayoutInflater from this Context
        LayoutInflater inflater = LayoutInflater.from(this);

        // Iterate through data you want to display
        for (String work : arrayList) {

            // Create new view (your own xml layout file, ViewGroup to be the root, boolean of whether to attach to root)
            View view  = inflater.inflate(R.layout.row, linearLayout, false);

            // Edit contents of the new view
            TextView workout = (TextView) view.findViewById(R.id.texts);
            workout.setText(work);

            // Add the view to the ViewGroup and it should show up
            linearLayout.addView(view);
        }
    }
}
