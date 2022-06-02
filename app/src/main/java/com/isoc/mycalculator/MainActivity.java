package com.isoc.mycalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public enum Operator{
        ADD("+"),    //addition
        LESS("-"),   //soustraction
        MULT("*"),   //multiplication
        DIV("/");   // division

        private String name="";

        Operator(String name) {
            this.name = name;
        }
        public String toString(){
            return name;
        }
    }

    private TextView txtScreen;
    private double Op1 = 0;
    private double Op2 = 0;

    private Operator operator = null;

    private boolean isOp1 = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtScreen = (TextView) findViewById(R.id.screen);

        Button btnEqual = (Button) findViewById(R.id.btnEqual);
        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calculate();
            }
        });

        Button btnClear = (Button) findViewById(R.id.btnClear);
        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();
            }
        });
    }

    private void updateDisplay(){
        String value = String.valueOf(Op1);
        if(!isOp1){
            value = String.valueOf(Op2);
        }
       // txtScreen.setText(String.format("%9d", value));
        txtScreen.setText(String.format("%s", value));
    }

    public void clear() {
        Op1 = 0;
        Op2 = 0;
        operator = null;
        isOp1 = true;
        updateDisplay();
    }

    private void Calculate() {
        if(isOp1) {
            // pas encore d'operation
        } else {
            switch(operator) {
                case ADD  : Op1 = Op1 + Op2;
                    break;
                case LESS : Op1 = Op1 - Op2;
                    break;
                case MULT  : Op1 = Op1 * Op2;
                    break;
                case DIV   : Op1 = Op1 / Op2;
                    break;
                default : return;
            }

            Op2 = 0;
            isOp1 = true;
            updateDisplay();
        }
    }

    public void setOperator(View view){
        switch (view.getId()){
            case R.id.btnAdd:operator=Operator.ADD;
                break;
            case R.id.btnLess:operator=Operator.LESS;
                break;
            case R.id.btnMult:operator=Operator.MULT;
                break;
            case R.id.btnDiv:operator=Operator.DIV;
                break;
            default:
                Toast.makeText(this, "Operator not found", Toast.LENGTH_LONG);
                return;

        }
        isOp1= false;
        updateDisplay();
    }

    public void addValue(View view){
        try {
            //int value = Integer.parseInt(((Button)view).getText().toString());
            String value =((Button)view).getText().toString();

           // double value = Double.valueOf(((Button)view).getText().toString());
            if(isOp1){
                if(value == "."){

                    Op1 =  Double.valueOf(String.valueOf(Op1)+".");

                } else{
                    Op1 = Op1 * 10 + Double.valueOf(value);
                }

                updateDisplay();
            } else {
                Op2 = Op2 * 10 + Double.valueOf(value);
                updateDisplay();
            }
        }catch (NumberFormatException | ClassCastException e) {
            Toast.makeText(this, "error value not correct",Toast.LENGTH_LONG);
        }
    }
}