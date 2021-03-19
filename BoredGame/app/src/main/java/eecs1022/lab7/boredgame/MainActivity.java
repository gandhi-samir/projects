package eecs1022.lab7.boredgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void setContentsOfTextView(int id, String newContents) {
        View view = findViewById(id);
        TextView textView = (TextView) view;
        textView.setText(newContents);
    }

    public void buttonPressed(View view) {
        for (int i = 0; i < 30; i++) {
            String output = "" + getRandomNumber();
            setContentsOfTextView(R.id.numberOutput, output);
            System.out.println(output);
        }
    }


    public int getRandomNumber(){
        int x = (int)((Math.random()*100)+1);


        return x;
    }



    }

