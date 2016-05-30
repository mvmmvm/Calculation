package com.example.msato.calculation;

import android.app.ActionBar;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView numberWindow;
    TextView formulaWindow;
    double num1;
    double num2;
    long numLong;
    String symbol;
    boolean isInput;
    String number;
    String formula;
    Resources res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberWindow = (TextView)findViewById(R.id.number);
        formulaWindow = (TextView)findViewById(R.id.formula);
        res = getResources();

    }

    public void onClear(View v){
        numberWindow.setText("0");
        formulaWindow.setText("");
        num1 = 0;
        num2 = 0;

    }

    public void onNumber(View v) {

        number = numberWindow.getText().toString();
        formula = formulaWindow.getText().toString();
        String inputNumber;

        // ここで既に入力された桁数を確認する
        // TODO 上限に達している場合以降の処理を行わない。
        // 小数点("." が含まれる）場合、上限桁数に＋１する

        switch (v.getId()) {

            case R.id.zero:
                inputNumber = "0";
                break;

            case R.id.one:
                inputNumber = "1";
                break;

            case R.id.two:
                inputNumber = "2";
                break;

            case R.id.three:
                inputNumber = "3";
                break;

            case R.id.four:
                inputNumber = "4";
                break;

            case R.id.five:
                inputNumber = "5";
                break;

            case R.id.six:
                inputNumber = "6";
                break;

            case R.id.seven:
                inputNumber = "7";
                break;

            case R.id.eight:
                inputNumber = "8";
                break;

            case R.id.nine:
                inputNumber = "9";
                break;

            default:
                return;
        }

        if (!isInput && !number.equals("0")) {
            number = number + inputNumber;
            numberWindow.setText(number);
        } else {
            number = inputNumber;
            numberWindow.setText(number);
        }
        if (isInput) {
            isInput = false;
        }
        num2 = Double.parseDouble(number);
        //ここは変換時にエラーが起こる可能性があるため、気を付ける。（try+catch等）
        //
    }

    public void onSymbol(View v) {
        number = numberWindow.getText().toString();
        formula = formulaWindow.getText().toString();


        switch (v.getId()){
            case R.id.plus:
                symbol = res.getString(R.string.plus);
                break;

            case R.id.minus:
                symbol = res.getString(R.string.minus);
                break;

            case R.id.multiply:
                symbol = res.getString(R.string.multiply);
                break;

            case R.id.divide:
                symbol = res.getString(R.string.divide);
                break;

            case R.id.percent:
                symbol = res.getString(R.string.percent);
                break;
        }


        formulaWindow.setText(number + " " + symbol); // 上部に現在の入力結果を表示
        num1 = Double.parseDouble(number);
        isInput = true;
        //ここでnum1(一つ目の数字[A+B=のA]がセットされたことを判断するためのisInputをtrueにする。)

    }

    /**
     * 計算結果を表示する。
     * @param v
     */
    public void onResult(View v){
        number = numberWindow.getText().toString();
        formula = formulaWindow.getText().toString();
        if(!number.equals("") && symbol != null){

            switch (symbol){
                case "+":
                    num1 = num1 + num2;
                    break;

                case "-":
                    num1 = num1 - num2;
                    break;

                case "*":
                    num1 = num1 * num2;
                    break;

                case "/":
                    num1 = num1 / num2;
                    break;

                default:


            }





            if(num1 % 1 == 0){
                numLong = (long)num1;
                numberWindow.setText(Long.toString(numLong));
            }else{
                numberWindow.setText(Double.toString(num1));
            }

            formulaWindow.setText("");






        }

}


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        TableRow row = (TableRow)findViewById(R.id.table_row);
        Button button = (Button)findViewById(R.id.mc);
        Button equalButton = (Button)findViewById(R.id.equal);
        TextView number = (TextView)findViewById(R.id.number);
        equalButton.setMinimumWidth(0);
        equalButton.setWidth(button.getWidth());
        equalButton.setHeight(row.getHeight()*2);
        number.setHeight(row.getHeight());
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
        // automatically handle clicks on the Home/Up button, so double
        // as you specify a parent activity in AndroidManifest.xml.
        double id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
