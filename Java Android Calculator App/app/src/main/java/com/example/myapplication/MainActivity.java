package com.example.myapplication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {

    public EditText display;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display = findViewById(R.id.textCalculator);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getString(R.string.display).equals(display.getText().toString())){
                    display.setText("");
                }
            }
        });
    }

    private void updateText(String stringToAdd){
        String oldString = display.getText().toString();
        int cursorPosition = display.getSelectionStart();
        String leftString = oldString.substring(0, cursorPosition);
        String rightString = oldString.substring(cursorPosition);
        if (getString(R.string.display).equals(display.getText().toString())){
            display.setText(stringToAdd);
            display.setSelection(cursorPosition + 1);
        } else {
            display.setText(String.format("%s%s%s", leftString, stringToAdd, rightString));
            display.setSelection(cursorPosition + 1);
        }
    }
    public void zeroBTN(View v){
     updateText("0");
    }

    public void cancelBTN(View v){
     display.setText("");
    }
    public void parenthesesBTN(View v){
     int cursorPosition = display.getSelectionStart();
     int openPar =0;
     int closedPar =0;
     int textLen = display.getText().length();
     for (int i =0; i<cursorPosition; i++){
         if (display.getText().toString().substring(i, i+1).equals("(")){
             openPar+=1;
         }
         if (display.getText().toString().substring(i, i+1).equals(")")){
             closedPar+=1;
         }
     }

     if (openPar == closedPar || display.getText().toString().substring(textLen-1, textLen).equals("(")){
         updateText("(");
        }
      else if ( closedPar < openPar && !display.getText().toString().substring(textLen-1, textLen).equals("(")){
            updateText(")");
        }
        display.setSelection(cursorPosition+1);
    }
    public void exponentBTN(View v){
   updateText("^");
    }
    public void divideBTN(View v){
   updateText(getString(R.string.divide));
    }
    public void sevenBTN(View v){
     updateText("7");
    }
    public void eightBTN(View v){
    updateText("8");
    }
    public void nineBTN(View v){
    updateText("9");
    }
    public void fourBTN(View v){
   updateText("4");
    }
    public void fiveBTN(View v){
   updateText("5");
    }
    public void sixBTN(View v){
    updateText("6");
    }
    public void oneBTN(View v){
   updateText("1");
    }
    public void twoBTN(View v){
  updateText("2");
    }
    public void threeBTN(View v){
   updateText("3");
    }
    public void minusPlusBTN(View v){
  //updateText("-");
    }
    public void pointBTN(View v){
 updateText(".");
    }
    public void equalBTN(View v){

        String aux = display.getText().toString();
        aux=aux.replaceAll("÷", "/");
        aux=aux.replaceAll("×", "*");
        Expression exp = new Expression(aux);
        String result = String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());


    }
    public void plusBTN(View v){
   updateText("+");
    }
    public void minusBTN(View v){
     updateText("-");
    }
    public void multiplyBTN(View v){
    updateText(getString(R.string.multiply));
    }
    public void backspaceBTN(View v){
    int cursorPosition = display.getSelectionStart();
    int length = display.getText().length();
    if (cursorPosition != 0 && length != 0){
        SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
        selection.replace(cursorPosition-1, cursorPosition, "");
        display.setText(selection);
        display.setSelection(cursorPosition-1);
    }
    }
}