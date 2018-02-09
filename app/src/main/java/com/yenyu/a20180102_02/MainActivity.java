package com.yenyu.a20180102_02;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{

    CheckBox chk1;
    Button bt1;
    RadioGroup rg1;
    Switch sw1;
    ProgressBar pb,pb2;
    SeekBar sb;
    TextView tv1;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chk1 = (CheckBox) findViewById(R.id.checkBox);
        bt1 = (Button) findViewById(R.id.button);
        rg1 = (RadioGroup) findViewById(R.id.radioGroup);
        sb = (SeekBar) findViewById(R.id.seekBar);
        tv1 = (TextView) findViewById(R.id.textView);
        sw1 = (Switch) findViewById(R.id.switch1);
        pb= (ProgressBar)findViewById(R.id.progressBar);
        pb2=(ProgressBar) findViewById(R.id.progressBar2);

         sw1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
         {
             @Override
             public void onCheckedChanged(CompoundButton compoundButton, boolean b)
             {
                 if (b) {
                     pb.setVisibility(View.VISIBLE);
                 } else {
                     pb.setVisibility(View.INVISIBLE);
                 }
             }
         });

        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
                // SeekBar 進度軸
        {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                tv1.setText(String.valueOf(i));  //顯示在TextView 將 整數轉成字串，i為進度值。
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar)
            {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {

            }

        });
    }


    public void click1(View v)
    {
        switch (rg1.getCheckedRadioButtonId())
        //用RadioGroup 把 RadioButton 包住
        //使用switch 來抓不同 RadioButton的ID
        {
            case R.id.radioButton:
                Toast.makeText(MainActivity.this, "第一個按鈕", Toast.LENGTH_SHORT).show();
            case R.id.radioButton2:
                Toast.makeText(MainActivity.this, "第二個按鈕", Toast.LENGTH_SHORT).show();
            case R.id.radioButton3:
                Toast.makeText(MainActivity.this, "第三個按鈕", Toast.LENGTH_SHORT).show();
        }

        Toast.makeText(MainActivity.this, String.valueOf(sb.getProgress()), Toast.LENGTH_SHORT).show();
        //Toast.makeText(顯示介面,"內容",顯示長短).show();
        //中間的值，利用getProgress 得到值，再使用String.valueOf 轉成字串
    }



    public void click2(View v) //按下按鈕，ProgressBar 跑三秒消失
    {
        pb.setVisibility(View.VISIBLE); //顯示progressbar
        new Thread() //新增一個執行緒(不然會將主執行序停止三秒)
        {
            @Override
            public void run() //覆寫裡面的run方法
            {
                super.run();
                try //執行三秒
                {
                    Thread.sleep(3000);
                } catch (InterruptedException e) //捕捉 執行序中斷的訊息
                {
                    e.printStackTrace(); //在命令行印出異常訊息: 在跑的過程中出错的位置及原因
                }
                runOnUiThread(new Runnable()
                        //runOnUiThread 放在UI的執行緒等待被執行
                        //新增一個UI執行緒
                {
                    @Override
                    public void run() {
                        pb.setVisibility(View.INVISIBLE); //當上面跑完三秒後執行 消失!
                    }
                });
            }
        }.start();
    }
    public void click3(View v) //點擊按鈕 水平ProgressBar -10
    {
        pb2.setProgress(pb2.getProgress() - 10);
        // 設定進度(int) -> 裡面寫 抓取進度-10
    }

    public void click4(View v) //點及按鈕 水平ProgressBar +10
    {
        pb2.setProgress(pb2.getProgress() + 10);
        // 設定進度(int) -> 裡面寫 抓取進度+10
    }

    public void clickGo(View v) //自動跑進度軸
    {
       new Thread() //新增一個執行緒，不能使用原本的主執行緒
       {
           @Override
           public void run() {
               super.run();
               for (i=0;i<100;i++) //迴圈1~100
               {
                   runOnUiThread(new Runnable() //新稱一個UI執行緒，下面填要執行的程式
                   {@Override
                       public void run() {
                           pb2.setProgress(i);}
                   });
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();}
               }
           }
       }.start();
    }

}
