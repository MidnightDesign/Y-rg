package at.yoerg.android.activity;

import android.app.Activity;
import android.os.Bundle;
import at.yoerg.android.R;
import at.yoerg.android.R.layout;

public class Main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
}