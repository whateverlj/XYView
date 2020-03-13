package mvvmdemo.example.com.xyviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final XYView mXYView =(XYView)findViewById(R.id.mXYView);
        final EditText scoreEt =(EditText)findViewById(R.id.scoreEt);
        Button setBtn =(Button)findViewById(R.id.setBtn);
        setBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(scoreEt.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"请输入分数",Toast.LENGTH_LONG).show();
                    return;
                }
                mXYView.setScore(Float.parseFloat(scoreEt.getText().toString()));
            }
        });
    }
}
