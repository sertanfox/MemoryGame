package com.example.hafiza_oyunu;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
     SharedPreferences preferences;
     Instant start,finish;
     Runnable startDelay;
     Handler setDelay;
     TextView txt_record, txt_gameover, txt_stopwatch;
     //Button btn_finish;
     Button btn_reset;
     Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15;
    Button[] butonlar;

    boolean[] bools = new boolean[16];

    // Oyun Tahtasının Kurulmasına Dair Değişkenler
    int[][] oyunTahtasi = new int[4][4];
    int[] usedNumbers = new int[16];
    int sayi,x,y;
    int max_deger = 8;
    List<Integer> numbers =new ArrayList<Integer>();

    // Oyunun Doğru Çalışması Adına Kuralları Oluşturan Değişkenler
    int[] cevrilmisIndexler = new int[2];
    int cevrilmisAdet = 0;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setDelay = new Handler();

        addNumbersToList();
        System.out.println(numbers.toString());

    txt_record = (TextView) findViewById(R.id.txt_record);
    txt_gameover = (TextView) findViewById(R.id.txt_gameover);
    txt_stopwatch = (TextView) findViewById(R.id.txt_stopwatch);
    btn_reset = (Button) findViewById(R.id.btn_reset);
    //btn_finish = (Button) findViewById(R.id.btn_finish);
    btn0 = (Button) findViewById(R.id.btn0);
    btn1 = (Button) findViewById(R.id.btn1);
    btn2  = (Button) findViewById(R.id.btn2);
    btn3  = (Button) findViewById(R.id.btn3);
    btn4  = (Button) findViewById(R.id.btn4);
    btn5  = (Button) findViewById(R.id.btn5);
    btn6  = (Button) findViewById(R.id.btn6);
    btn7  = (Button) findViewById(R.id.btn7);
    btn8  = (Button) findViewById(R.id.btn8);
    btn9  = (Button) findViewById(R.id.btn9);
    btn10  = (Button) findViewById(R.id.btn10);
    btn11  = (Button) findViewById(R.id.btn11);
    btn12  = (Button) findViewById(R.id.btn12);
    btn13  = (Button) findViewById(R.id.btn13);
    btn14  = (Button) findViewById(R.id.btn14);
    btn15  = (Button) findViewById(R.id.btn15);

    butonlar = new Button[]
    {btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,
     btn11,btn12,btn13,btn14,btn15};

        preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //preferences.edit().remove("record").commit();
        long rekor = preferences.getLong("record",9999999);
        System.out.println("OYUN BAŞLANGICI : REKOR DEĞERİ : "+rekor);
        if(rekor != 9999999)
        {
            txt_record.setText("Rekor : "+formatTheRecord(rekor)+" sn");
        }
        ResetGame();

        long stopWatchStart = System.nanoTime();
        start = Instant.now();

        // Butonlar
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetGame();
            }
        });

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(0);
            }
        });

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(1);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(2);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(3);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(4);
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(5);
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(6);
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(7);
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(8);
            }
        });

        btn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(9);
            }
        });

        btn10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(10);
            }
        });

        btn11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(11);
            }
        });

        btn12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(12);
            }
        });

        btn13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(13);
            }
        });

        btn14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(14);
            }
        });

        btn15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenOrNot(15);
            }
        });

        /*
        btn_finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishTheGame();
            }
        });

         */

    }

    public void addNumbersToList(){
        for(int i = 1; i<=max_deger; i++)
        {
            numbers.add(i);
            numbers.add(i);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void finishTheGame()
    {
        for(int i = 0; i<16; i++)
        {
            bools[i]=true;
            int x = i/4;
            int y = i%4;
            int sayi = oyunTahtasi[x][y];
            butonlar[i].setText(Integer.toString(sayi));
            isGameOver();
        }


    }



    public void TersCevir()
    {
        int a = cevrilmisIndexler[0];
        int b = cevrilmisIndexler[1];
        boolean x = false;
        if(oyunTahtasi[a/4][a%4] != oyunTahtasi[b/4][b%4])
            x = true;
        boolean finalX = x;
        if(finalX)
        {
        startDelay = new Runnable()  {
            @Override
            public void run() {
                    butonlar[a].setText("");
                    butonlar[b].setText("");
                    bools[a] = false;
                    bools[b] = false;

                cevrilmisAdet = 0;
            }
        };
       setDelay.postDelayed(startDelay, 500);
        }
        else
            cevrilmisAdet = 0;

    }



    public void ClearUsedNumberArray()
    {
        for(int i = 0; i<16; i++)
            usedNumbers[i]=0;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public void isGameOver()
    {
        System.out.println("Oyun Bitti");
        int count = 0;
        for(int i = 0; i<16; i++)
            if(bools[i]==true)
                count++;
        if(count==16)
        {
            finish = Instant.now();
            long timeElapsedMillis = Duration.between(start, finish).toMillis();
            String result = formatTheRecord(timeElapsedMillis);
            txt_stopwatch.setText("Süreniz : "+result+" saniye");
            long sharedRecord = preferences.getLong("record",9999999);
            if(timeElapsedMillis-sharedRecord>1000)
                txt_gameover.setText("Daha hızlı ol, daha hızlı!");
            if(timeElapsedMillis-sharedRecord<1000)
                txt_gameover.setText("Ahh beee, çok az kalmıştı");
            if(sharedRecord>timeElapsedMillis)
            {
                txt_gameover.setText("Helal koçum, yeni rekor");
                SharedPreferences.Editor editor = preferences.edit();
                editor.putLong("record",timeElapsedMillis);
                editor.commit();
                txt_record.setText("Rekor : "+result+"sn");

            }

        }


    }

    public int Random(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    public int FindNewMaxValue(int[] sayilar)
    {
        int MAX = 0;
        for(int i = 0; i<sayilar.length; i++)
        {
            if(sayilar[i]>MAX)
                MAX = sayilar[i];
        }
        return MAX;
    }

    public boolean IsUsedTwice(int[] usedNumbers, int value)
    {
        int sayac = 0;
        for(int i = 0; i<usedNumbers.length; i++)
            if(usedNumbers[i] == value)
                sayac++;

        if(sayac>1) return true;
        else return false;
    }

    public void HepsiniTersCevir()
    {
        for(int i = 0; i<16; i++)
            butonlar[i].setText("");
    }

    public void AllBoolsFalse()
    {
        for(int i=0; i<16; i++)
            bools[i] = false;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void isIndexTwo()
    {
        isGameOver();
        if(cevrilmisAdet == 2)
        {
            TersCevir();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void OpenOrNot(int buttonNumber)
    {
        if(cevrilmisAdet != 2 && bools[buttonNumber]==false)
        {
            int x = buttonNumber/4;
            int y = buttonNumber%4;
            int sayi = oyunTahtasi[x][y];
            butonlar[buttonNumber].setText(Integer.toString(sayi));
            cevrilmisIndexler[cevrilmisAdet] = buttonNumber;
            cevrilmisAdet++;
            bools[buttonNumber]=true;
            isIndexTwo();
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void ResetGame()
    {
        txt_stopwatch.setText("");
        AllBoolsFalse();
        HepsiniTersCevir();
        ShuffleTable();
        txt_gameover.setText("");
        start = Instant.now();
    }

    public void ShuffleTable()
    {
        numbers.clear();
        addNumbersToList();
        int poz = 0;
        int sayac = 15;
        while(sayac>=0)
        {
            int randomSayi = Random(0,sayac);
            sayi = numbers.get(randomSayi);
            numbers.remove(randomSayi);
            x = poz/4;
            y = poz%4;
            oyunTahtasi[x][y] = sayi;
            sayac--;
            poz++;
        }


    }

    public String formatTheRecord(long record)
    {
        String str_record = String.valueOf(record);
        System.out.println("format The Record -> record Lenght : "+str_record.length());
        if(str_record.length()==4)
        {
            String str1 = str_record.substring(0, 1);
            String str2 = str_record.substring(1);
            String str3 = str1.concat(",");
            String str4 = str3.concat(str2);
            return str4;
        }
        else if(str_record.length()==5)
        {
            String str1 = str_record.substring(0, 2);
            String str2 = str_record.substring(2);
            String str3 = str1.concat(",");
            String str4 = str3.concat(str2);
            return str4;
        }
        else if(str_record.length()==6)
        {
            String str1 = str_record.substring(0, 3);
            String str2 = str_record.substring(3);
            String str3 = str1.concat(",");
            String str4 = str3.concat(str2);
            return str4;
        }
        else
        {
          return "";
        }
    }

}

