package com.salikh.calculator;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends BaseActivity {


    boolean leftBracket = true;
    private LinearLayout mainBackground, cardBackground;
    private Button themeBtn;
    private TextView workingsTV;
    private TextView resultTV;
    private String workings = "";
    private String formula = "";
    private String tempFormula = "";
    private Button cView, qovusView, korenView, boluvView, kopaytruvView, ayruView, qoshuvView, barobarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadViews();
        setBars();
        setListeners();
    }

    private void setListeners() {
        themeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThemeActivity.class);
                startActivity(intent);
                Animatoo.animateZoom(MainActivity.this);
                finish();
            }
        });
    }

    private void loadViews() {
        workingsTV = findViewById(R.id.workingTextView);
        resultTV = findViewById(R.id.resultTextView);
        themeBtn = findViewById(R.id.theme_view);
        mainBackground = findViewById(R.id.main_back);
        cardBackground = findViewById(R.id.card_back_view);

        //btn
        cView = findViewById(R.id.c_view);
        qovusView = findViewById(R.id.qovs_view);
        korenView = findViewById(R.id.koren_view);
        boluvView = findViewById(R.id.boluv_view);
        kopaytruvView = findViewById(R.id.kopaytru_view);
        ayruView = findViewById(R.id.ayrish_view);
        qoshuvView = findViewById(R.id.qoshish_view);
        barobarView = findViewById(R.id.barobar_view);
    }

    private void setWorkings(String giveValue) {
        workings = workings + giveValue;
        workingsTV.setText(workings);
    }

    private void setBars() {
        getWindow().setStatusBarColor(getResources().getColor(R.color.backra_gray));
        getWindow().setNavigationBarColor(getResources().getColor(R.color.backra_gray));
    }

    public void equalsOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");
        checkForPower();

        try {
            result = (double) engine.eval(formula);
        } catch (ScriptException e) {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        if (result != null) {
            resultTV.setText(String.valueOf(result.doubleValue()));
        }
    }

    private void checkForPower() {

        ArrayList<Integer> indexOfPowers = new ArrayList<>();
        for (int i = 0; i < workings.length(); i++) {
            if (workings.charAt(i) == '^') {
                indexOfPowers.add(i);
            }
        }

        formula = workings;
        tempFormula = workings;
        for (Integer index : indexOfPowers) {
            changeFormula(index);
        }

    }

    private void changeFormula(Integer index) {

        String numberLeft = "";
        String numberRight = "";

        for (int i = index + 1; i < workings.length(); i++) {
            if (isNumber(workings.charAt(i))) {
                numberRight = numberRight + workings.charAt(i);
            } else {
                break;
            }
        }

        for (int i = index - 1; i >= 0; i--) {
            if (isNumber(workings.charAt(i))) {
                numberLeft = numberLeft + workings.charAt(i);
            } else {
                break;
            }
        }

        String original = numberLeft + "^" + numberRight;
        String changed = "Math.pow(" + numberLeft + "," + numberRight + ")";

        tempFormula = tempFormula.replace(original, changed);

    }

    private boolean isNumber(char c) {
        return (c <= '9' && c >= '0') || c == '.';
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        workings = "";
        resultTV.setText("");
        leftBracket = true;
    }

    public void bracketsOnClick(View view) {
        if (leftBracket) {
            setWorkings("(");
            leftBracket = false;
        } else {
            setWorkings(")");
            leftBracket = true;
        }

    }

    public void powerOfOcClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnclick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");

    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClock(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    @Override
    public void setThemeData() {
        mainBackground.setBackgroundColor(MemoryHelper.getHelpr().getThemeBack());
        cardBackground.setBackgroundColor(MemoryHelper.getHelpr().getThemeCard());
        getWindow().setStatusBarColor(MemoryHelper.getHelpr().getThemeBack());
        getWindow().setNavigationBarColor(MemoryHelper.getHelpr().getThemeBack());
        cView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        qovusView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        korenView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        boluvView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        kopaytruvView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        ayruView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        qoshuvView.setTextColor(MemoryHelper.getHelpr().getThemeText());
        barobarView.setTextColor(MemoryHelper.getHelpr().getThemeText());
    }

}
