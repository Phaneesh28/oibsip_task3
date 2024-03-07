package com.example.calculatorapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView resultTv,solutionTv;
    MaterialButton buttonC,buttonBrackOpen,buttonBrackClose;
    MaterialButton buttonDivide,buttonMultiply,buttonPlus,buttonMinus,buttonEquvals;
    MaterialButton button0,button1,button2,button3,button4,button5,button6,button7,button8,button9;
    MaterialButton buttonAC,buttonDot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultTv = findViewById(R.id.result_tv);
        solutionTv = findViewById(R.id.solution_tv);

        assingId(buttonC,R.id.button_c);
        assingId(buttonBrackOpen,R.id.button_open_bracket);
        assingId(buttonBrackClose,R.id.button_closed_bracket);
        assingId(buttonDivide,R.id.button_divide);
        assingId(buttonMultiply,R.id.button_multiplication);
        assingId(buttonPlus,R.id.button_addition);
        assingId(buttonMinus,R.id.button_minus);
        assingId(buttonEquvals,R.id.button_equval);
        assingId(button0,R.id.button_0);
        assingId(button1,R.id.button_1);
        assingId(button2,R.id.button_2);
        assingId(button3,R.id.button_3);
        assingId(button4,R.id.button_4);
        assingId(button5,R.id.button_5);
        assingId(button6,R.id.button_6);
        assingId(button7,R.id.button_number_7);
        assingId(button8,R.id.button_number_8);
        assingId(button9,R.id.button_number_9);
        assingId(buttonAC,R.id.button_ac);
        assingId(buttonDot,R.id.button_dot);

    }

    void assingId(MaterialButton btn,int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button =(MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = solutionTv.getText().toString();

        if(buttonText.equals("AC")){
            solutionTv.setText("");
            resultTv.setText("0");
            return;
        }
        if(buttonText.equals("=")){
            solutionTv.setText(resultTv.getText());
            return;
        }
        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else {
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionTv.setText(dataToCalculate);

        String finalResult = getResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultTv.setText(finalResult);
        }

    }
    String getResult(String data) {
        try{
            Context context = Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable = context.initStandardObjects();
            String finalResult = context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }
}