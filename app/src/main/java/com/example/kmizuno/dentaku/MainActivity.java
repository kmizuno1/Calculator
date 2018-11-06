package com.example.kmizuno.dentaku;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    Button button;

    //ボタンが押されたときに呼ばれるリスナー　＝が押されたときは初期化します。
    View.OnClickListener buttonListener = new View.OnClickListener(){
        @Override
        public void onClick(View view){
            recentOperator =R.id.button_equal;
            result =0;
            isOperatorKeyPushed = false;

            textView.setText("");
            editText.setText("");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = (TextView) findViewById(R.id.textview);
        editText = (EditText) findViewById(R.id.edittext);
        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(buttonListener);

        //各ボタンを押したときの個別の命令　1～0と .は同じリスナーを呼び　ボタンに書かれた数字によって処理します。
        findViewById(R.id.button_1).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_2).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_3).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_4).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_5).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_6).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_7).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_8).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_9).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_0).setOnClickListener(buttonNumberListener);
        findViewById(R.id.button_dot).setOnClickListener(buttonNumberListener);

        findViewById(R.id.button_add).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_subtract).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_divide).setOnClickListener(buttonOperatorListener);
        findViewById(R.id.button_multiply).setOnClickListener(buttonOperatorListener);

        findViewById(R.id.button_equal).setOnClickListener(buttonOperatorListener);
    }

    //1～0と　.を押下したときの処理　数字に数字をつなげるだけです。　.は1回だけの処理を入れる必要がある。
    View.OnClickListener buttonNumberListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Button button = (Button) view;

            if (isOperatorKeyPushed == true) {
                editText.setText(button.getText());
            } else {
                editText.append(button.getText());
            }

            isOperatorKeyPushed = false;
        }
    };
    int recentOperator = R.id.button_equal;
    double result;
    boolean isOperatorKeyPushed;

        View.OnClickListener buttonOperatorListener;

    {
        buttonOperatorListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String b=editText.getText().toString();

                if(b.equals("")){
                    return;
                }
                if(b.equals(".")){
                    return;
                }

                Button operatorButton = (Button) view;
                double value = Double.parseDouble(b);

                if (recentOperator == R.id.button_equal//＝ボタンが押されたとき
                        ) {
                    result = value;//double型のresultにvalue
                } else {
                    result = calc(recentOperator, result, value);
                    editText.setText(String.valueOf(result));
                }

                recentOperator = operatorButton.getId();
                textView.setText(operatorButton.getText());
                isOperatorKeyPushed = true;
            }

            //計算処理 0で割る処理のときは　0を返してby zeroエラー回避
            double calc(int operator, double value1, double value2) {
                switch (operator) {
                    case R.id.button_add:
                        return value1 + value2;
                    case R.id.button_subtract:
                        return value1 - value2;
                    case R.id.button_divide:
                        return value1 / value2;
                    case R.id.button_multiply:
                        return value1 * value2;
                    default:
                        return value1;
                }
            }
        };
    }
}
