package sh.dfm.dmitriibeletskiy.victorina;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

    TextView tvQwe, tvRes;
    RadioButton rb1, rb2, rb3;
    RadioGroup rbGr;
    Button btnpreview, btnanswer, btnnext;


    String[] q;// массив вопросов
    String[][] a;//массив ответов
    int[] ra;//массив индексов правильных ответов
    int current_question;//текущий вопрос
    int score; //кол-во баллов

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

///////////////////////////////

        initComponents();
        current_question = -1;

///////////////////////////

        q = getResources().getStringArray(R.array.q1);
        ra = getResources().getIntArray(R.array.ra1);
        a = new String[q.length][3];

        for (int i = 0; i < q.length; i++) {
            int strId = getResources().getIdentifier("a"+i, "array", getPackageName());
            a[i] = getResources().getStringArray(strId);
        }

        pressNext();
    }


    private void initComponents() {

        tvQwe = (TextView) findViewById(R.id.tvQwe);
        tvRes = (TextView) findViewById(R.id.tvRes);

        rb1 = (RadioButton) findViewById(R.id.rb1);
        rb2 = (RadioButton) findViewById(R.id.rb2);
        rb3 = (RadioButton) findViewById(R.id.rb3);
        rbGr = (RadioGroup) findViewById(R.id.rbGr);

        btnpreview = (Button) findViewById(R.id.btnpreview);
        btnanswer = (Button) findViewById(R.id.btnanswer);
        btnnext = (Button) findViewById(R.id.btnnext);

        View.OnClickListener onClick = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case (R.id.btnpreview):
                        pressPreview();
                        break;
                    case (R.id.btnanswer):
                        pressAnswer();
                        break;
                    case (R.id.btnnext):
                        pressNext();
                        break;
                    default:
                        break;
                }

            }
        };
        btnpreview.setOnClickListener(onClick);
        btnanswer.setOnClickListener(onClick);
        btnnext.setOnClickListener(onClick);

    }

    /////////////////// onClick Button methods
    public void pressPreview() {

        if (current_question > 0) {
            current_question--;
        }
        Log.d("MainActivity", "q.length =  " + q.length);
        Log.d("MainActivity", "current_question =  " + current_question);

        tvQwe.setText(q[current_question].toString());

        rb1.setText(a[current_question][0].toString());
        rb2.setText(a[current_question][1].toString());
        rb3.setText(a[current_question][2].toString());

        rbGr.clearCheck();

//test commit

    }

    public void pressAnswer() {

        int selected = -1;
        //проверка что выбранно
        switch (rbGr.getCheckedRadioButtonId()) {
            case (R.id.rb1):
                selected = 0;
                break;
            case (R.id.rb2):
                selected = 1;
                break;
            case (R.id.rb3):
                selected = 2;
                break;
            default:
                tvRes.setText("None rb1");

        }
        //Ответ  ra[0]=0;


        if (selected != -1) {
            int correctAnswer = ra[current_question];
            if (selected == correctAnswer) {
                tvRes.setTextColor(getResources().getColor(R.color.Green));
                tvRes.setText("correct +10");
                pressNext();
            } else {
                tvRes.setTextColor(getResources().getColor(R.color.Read));
                tvRes.setText("Ответ не верный");
                SystemClock.sleep(60);
            }

        }
        rbGr.clearCheck();

    }

    public void pressNext() {

        if (current_question < q.length - 1) {
            current_question++;
        }
        tvQwe.setText(q[current_question].toString());

        rb1.setText(a[current_question][0].toString());
        rb2.setText(a[current_question][1].toString());
        rb3.setText(a[current_question][2].toString());

        rbGr.clearCheck();
    }

}
