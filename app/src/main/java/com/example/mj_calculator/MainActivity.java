package com.example.mj_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView nresult, ninptext;
    MaterialButton none,ntwo,nthree,nfour,nfive,nsix,nseven,neight,nnine,nzero,ndot1,ndot2,neq,nc,nper,nac,ndiv,nmul,nsub,nadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nresult = findViewById(R.id.result);
        ninptext = findViewById(R.id.inptext);
        buttfun(none, R.id.one);
        buttfun(ntwo, R.id.two);
        buttfun(nthree, R.id.three);
        buttfun(nfour, R.id.four);
        buttfun(nfive, R.id.five);
        buttfun(nsix, R.id.six);
        buttfun(nseven, R.id.seven);
        buttfun(neight, R.id.eight);
        buttfun(nnine, R.id.nine);
        buttfun(nzero, R.id.zero);
        buttfun(ndot1, R.id.dot1);
        buttfun(ndot2, R.id.dot2);
        buttfun(neq, R.id.eq);
        buttfun(nc, R.id.c);
        buttfun(nper, R.id.per);
        buttfun(nac, R.id.ac);
        buttfun(ndiv, R.id.div);
        buttfun(nmul, R.id.mul);
        buttfun(nsub, R.id.sub);
        buttfun(nadd, R.id.add);
    }
    void buttfun(MaterialButton but, int fi){
        but = findViewById(fi);
        but.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton btn = (MaterialButton) view;
        String s1 = btn.getText().toString();
        String s2 = ninptext.getText().toString();
        if(s1.equals("AC")){
            ninptext.setText("");
            nresult.setText("");
            return;
        }
        if(s1.equals("=")){
            ninptext.setText(nresult.getText());
            nresult.setText("Ans : ");
            return;
        }
        if(s1.equals("C")){
            s2 = s2.substring(0,s2.length()-1);
        }else{
            s2 = s2+s1;
        }
        ninptext.setText(s2);
        String result = rst(s2);
        if(!result.equals("err")){
            nresult.setText(result);
        }
    }
    String rst(String data){
        try {
            Context co = Context.enter();
            co.setOptimizationLevel(-1);
            Scriptable sc = co.initStandardObjects();
            String Result = co.evaluateString(sc,data,"Javascript",1,null).toString();
            if(Result.endsWith(".0")){
                Result = Result.replace(".0","");
            }
            return Result;
        }
        catch (Exception e){
            return "err";
        }
    }
}
