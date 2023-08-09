package com.erenaksu.deneme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    EditText editText1;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editTextText);
        editText1 = findViewById(R.id.editTextNumberPassword);
        sharedPreferences=this.getSharedPreferences("com.erenaksu.deneme", Context.MODE_PRIVATE);

        String isim=sharedPreferences.getString("NickName","");
        int sifre=sharedPreferences.getInt("Sifre",0);
        if(sifre==0 && isim==""){

        }else{
            textView.setText("Kullanici Kaydolmustur...\n Kullanici Adi:"+isim+"\nSifresi:"+sifre);
        }

    }

    public void kaydol(View view) {
        if (editText.getText().toString().matches("") || editText1.getText().toString().matches("")){
            textView.setText("Lutfen Kullanici Adinizi Ve Sifrenizi Eksiksiz Giriniz..");
        }
        else{
          String isim=editText.getText().toString();
          sharedPreferences.edit().putString("NickName",isim).apply();

          int sifre=Integer.parseInt(editText1.getText().toString());
          sharedPreferences.edit().putInt("Sifre",sifre).apply();

          textView.setText("Kullanici Kaydolmustur...\n Kullanici Adi:"+isim+"\n Sifresi:"+sifre);
        }
    }

    public void sil(View view){

        if(editText.getText().toString().matches("") || editText1.getText().toString().matches("")){
            textView.setText("Lutfen Kullanici Adinizi Ve Sifrenizi Eksiksiz Giriniz..");
        }
        else{
            int sifre=sharedPreferences.getInt("Sifre",0);
            String isim=sharedPreferences.getString("NickName","");
            if(sifre!=0){
                sharedPreferences.edit().remove("Sifre").apply();
                textView.setText("Kayitli Kullanici Silinmistir...");
            }
            if(isim!=""){
                sharedPreferences.edit().remove("NickName").apply();
                textView.setText("Kayitli Kullanici Silinmistir...");
            }
        }
    }
}