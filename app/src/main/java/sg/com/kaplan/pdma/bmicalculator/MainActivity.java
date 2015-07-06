package sg.com.kaplan.pdma.bmicalculator;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView status = (TextView) findViewById(R.id.textViewStatus);
        final EditText editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        final EditText editTextHeight = (EditText) findViewById(R.id.editTextHeight);
        Button button = (Button) findViewById(R.id.buttonCheck);

        final MediaPlayer soundObese = MediaPlayer.create(this,R.raw.obese);
        final MediaPlayer soundOverweight = MediaPlayer.create(this,R.raw.overweight);
        final MediaPlayer soundNormalweight = MediaPlayer.create(this,R.raw.normal);
        final MediaPlayer soundUnderweight = MediaPlayer.create(this,R.raw.underweight);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double weight = Double.parseDouble(editTextWeight.getText().toString());
                double height = Double.parseDouble(editTextHeight.getText().toString());
                double bmi = weight / (height * height);

                String statusText;
                int statusColor;

                if (bmi > 27.5) {
                    statusText = "Obese";
                    statusColor = Color.RED;
                    soundObese.start();
                    Toast.makeText(getApplicationContext(), "obese", Toast.LENGTH_SHORT).show();
                } else if (bmi > 23) {
                    statusText = "Overweight";
                    statusColor = Color.YELLOW;
                    soundOverweight.start();
                } else if (bmi > 18.5) {
                    statusText = "Normal weight";
                    statusColor = Color.GREEN;
                    soundNormalweight.start();
                } else {
                    statusText = "Underweight";
                    statusColor = Color.WHITE;
                    soundUnderweight.start();
                }

                status.setText(statusText);
                status.setBackgroundColor(statusColor);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
