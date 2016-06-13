package com.example.msato.calculation;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {

//    TextView numberWindow;
//    TextView formulaWindow;

    BigDecimal dcm;

    double dResult;
    double dCalcNum;
    long lngResult;
    String symbol;
    boolean isInput;
    // 大きな画面に表示する値
    String dispNumber;
    String dispFormula;
    Resources res;

    //TODO 小数点入力
    //TODO 桁数を制限する
    //TODO オーバフローを制御する
    //TODO クラス変数を削除する
    //TODO 計算用のクラスを作る

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();

    }

    // 空にする
    public void onClear(View v){
//        numberWindow.setText("0");
//        formulaWindow.setText("");
        setTextNumberWindow("0");
        setTextFormulaWindow("");
        dResult = 0;
        dCalcNum = 0;

    }

    public void onNumber(View v) {

        // 大きな画面の数字を取得
        dispNumber = getTextNumberWindow();
        // 小さな画面の数値を取得
        dispFormula = getTextFormulaWindow();
        // 入力した文字を保持
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

            case R.id.dot:
                inputNumber = ".";
                break;

            default:
                return;
        }

        // 入力値が0でない場合、かつ既に値が入ってる場合
        // 大きな画面に入力値を追加する
        if (!isInput && !dispNumber.equals("0")) {
            dispNumber = dispNumber + inputNumber;
//            numberWindow.setText(dispNumber);
        } else {
            // 入力値が0か、まだ値が入っていない場合、
            // 大きな画面＝入力値とする
            dispNumber = inputNumber;
//            numberWindow.setText(dispNumber);
        }
        // 大きな画面に値を反映する。
        setTextNumberWindow(dispNumber);
        // ?
        if (isInput) {
            isInput = false;
        }
//        dCalcNum = Double.parseDouble(dispNumber);
        //ここは変換時にエラーが起こる可能性があるため、気を付ける。（try+catch等）
        //
    }

    public void onSymbol(View v) {
//        dispNumber = numberWindow.getText().toString();
//        dispFormula = formulaWindow.getText().toString();

        // 大きな画面の数字を取得
        dispNumber = getTextNumberWindow();
        // 小さな画面の数値を取得
        dispFormula = getTextFormulaWindow();


        // 計算記号が入力された場合

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


//        formulaWindow.setText(dispNumber + " " + symbol); // 上部に現在の入力結果を表示
//        setTextFormulaWindow(dispNumber + " " + symbol);
        setTextFormulaWindow(dispFormula + " " + symbol);

        // 入力値が0でない場合（初期値=0)
        if(dResult != 0) {
            calculateNumber();
        }

//        dResult = Double.parseDouble(dispNumber);
        dCalcNum = Double.parseDouble(dispNumber);
        isInput = true;
        //ここでnum1(一つ目の数字[A+B=のA]がセットされたことを判断するためのisInputをtrueにする。)

    }

    /**
     * 計算結果を表示する。
     * @param v
     */
    public void onResult(View v){
//        dispNumber = numberWindow.getText().toString();
//        dispFormula = formulaWindow.getText().toString();
        calculateNumber();

    }

    private void calculateNumber() {
        dispNumber = getTextNumberWindow();
        dispFormula = getTextFormulaWindow();
        dCalcNum = Double.parseDouble(dispNumber);
        if(!dispNumber.equals("") && symbol != null){

            switch (symbol){
                case "+":
//                    dResult = Integer.parseInt(dispNumber) + dCalcNum;
                    dResult = dResult + dCalcNum;
                    break;

                case "-":
//                    dResult = Integer.parseInt(dispNumber) - dCalcNum;
                    dResult = dResult - dCalcNum;
                    break;

                case "*":
//                    dResult = Integer.parseInt(dispNumber) * dCalcNum;
                    dResult = dResult * dCalcNum;
                    break;

                case "/":
//                    dResult = Integer.parseInt(dispNumber) / dCalcNum;
                    dResult = dResult / dCalcNum;
                    break;

                default:


            }

            // 小数点を省略して表示
            if(dResult % 1 == 0){
                lngResult = (long) dResult;   //
//                numberWindow.setText(Long.toString(lngResult));
                setTextNumberWindow(Long.toString(lngResult));
            }else{
//                numberWindow.setText(Double.toString(dResult));
                setTextNumberWindow(Double.toString(dResult));
            }

//            formulaWindow.setText("");
            setTextFormulaWindow("");

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

    private void setTextNumberWindow(String s) {
        TextView numberWindow;
        numberWindow = (TextView)findViewById(R.id.number);
        numberWindow.setText(s);
    }

    private void setTextFormulaWindow(String s) {
        TextView formulaWindow;
        formulaWindow = (TextView)findViewById(R.id.formula);
        formulaWindow.setText(s);
    }

    private String getTextNumberWindow() {
        return ((TextView) findViewById(R.id.number)).getText().toString();
    }

    private String getTextFormulaWindow() {
        return ((TextView) findViewById(R.id.formula)).getText().toString();
    }
}
