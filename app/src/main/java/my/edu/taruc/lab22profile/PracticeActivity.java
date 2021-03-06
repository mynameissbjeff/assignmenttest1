package my.edu.taruc.lab22profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.Random;

public class PracticeActivity extends AppCompatActivity {

    private static final int REQUEST_MAIN_MENU = 1;

    public TextView textViewNum;
    public  TextView textViewQuestion;
    public Button buttonA;
    public Button buttonB;
    public Button buttonC;
    public Button buttonD;
    public int countNum = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        textViewNum = (TextView)findViewById(R.id.textViewNum);
        textViewQuestion = (TextView)findViewById(R.id.textViewQuestion);
        buttonA = (Button)findViewById(R.id.buttonA);
        buttonB = (Button)findViewById(R.id.buttonB);
        buttonC = (Button)findViewById(R.id.buttonC);
        buttonD = (Button)findViewById(R.id.buttonD);
        CreateQuestion();
        textViewNum.setText(""+countNum);
    }


    void CreateQuestion(){
        int min = 1;
        int min2 = 0;
        int max = 15;
        float ans;

        Bundle bundle = getIntent().getExtras();
        String operation= bundle.getString("operation");

        Random r = new Random();
        int num1 = r.nextInt(max - min + 1) + min;
        int num2 = r.nextInt(num1 - min2 + 1) + min;
        int ansnum = r.nextInt(4-1+1)+1;

        ans = getAnswer(operation,num1,num2);

        setAnswer(ansnum,ans);
    }

    public float getAnswer(String operation,float num1,float num2){
        float ans = 0;
        float mix = randomAns(1,4);
        if(operation.equals("ADD")||(operation.equals("MIX")&&mix==1)){
            textViewQuestion.setText(num1 + "+" + num2 + " = ?");
            ans = num1 + num2;
        }
        else if(operation.equals("SUB")||(operation.equals("MIX")&&mix==2)){
            textViewQuestion.setText(num1 + "-" + num2 + " = ?");
            ans = num1 - num2;
        }
        else if(operation.equals("MUL")||(operation.equals("MIX")&&mix==3)){
            textViewQuestion.setText(num1 + "X" + num2 + " = ?");
            ans = num1 * num2;
        }
        else if(operation.equals("DIV")||(operation.equals("MIX")&&mix==4)){
            textViewQuestion.setText(num1 + "/" + num2 + " = ?");
            ans = num1 / num2;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        ans = Float.valueOf(decimalFormat.format(ans));
        return ans;
    }

    public void setAnswer(int ansnum,float ans){
        //buttonA.setText("A. "+randomAns(0,30));
        //buttonB.setText("B. "+randomAns(0,30));
        //buttonC.setText("C. "+randomAns(0,30));
        //buttonD.setText("D. "+randomAns(0,30));
        buttonA.setText("A. "+randomAns((int)ans-10,(int)ans+10));
        buttonB.setText("B. "+randomAns((int)ans-10,(int)ans+10));
        buttonC.setText("C. "+randomAns((int)ans-10,(int)ans+10));
        buttonD.setText("D. "+randomAns((int)ans-10,(int)ans+10));
        if(ansnum == 1){
            buttonA.setText("A. "+ans);
        }
        else if(ansnum == 2){
            buttonB.setText("B. "+ans);
        }
        else if(ansnum == 3){
            buttonC.setText("C. "+ans);
        }
        else if(ansnum == 4){
            buttonD.setText("D. "+ans);
        }
    }

    public float randomAns(int min,int max){
        Random r = new Random();
        float randans = r.nextInt(max - min + 1) + min;
        return randans;
    }

    public void buttonAans(View view){
        if(countNum<12){
            countNum++;
            textViewNum.setText(""+countNum);
            CreateQuestion();
        }
        else if(countNum >= 12){
            Intent intent = new Intent(this,MainActivity.class);
            startActivityForResult(intent, REQUEST_MAIN_MENU);
            finish();
        }

    }

}
