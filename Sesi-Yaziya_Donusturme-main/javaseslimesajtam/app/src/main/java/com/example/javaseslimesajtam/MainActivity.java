package com.example.javaseslimesajtam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private Translator translatoren ;
    private Translator translatorrus ;
    private Translator translatorchina ;
    private Translator translatorarapça ;
    private Translator translatoralmanca ;
    private Translator translatoritalyanca ;


    private Boolean aBooleanen =false;
    private Boolean aBooleanrus =false;
    TextView gelenveri;

    Button yükle;


    public EditText metinçeviri;
   public Button çeviriyayınlabtn;
   public EditText yazılandil;
   public EditText hedefdil;
   public LinearLayout çeviriekran;
   public Button dil;
    public LinearLayout diller;
    public LinearLayout diller2;


    public ImageButton seskayıt;
    public EditText metin;
    public Button yayınlabtn;
    public Button çevirbtn;
    public Button çevirbtn2;
    public Intent intent;
    public static final int başlatıldı = 1;
    public static final int başlatılamadı=0;
    public SpeechRecognizer recognizer;
    public static ArrayList<String> speech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seskayıt = (ImageButton)findViewById(R.id.seskayıt);
        metin = (EditText)findViewById(R.id.metin);
        çevirbtn=findViewById(R.id.çevirbtn);
        yayınlabtn = (Button)findViewById(R.id.yayınlabtn);
        metinçeviri=findViewById(R.id.metinçeviri);
        çeviriyayınlabtn=findViewById(R.id.çeviriyayınlabtn);
        dil=findViewById(R.id.dil);
        çevirbtn=findViewById(R.id.çevirbtn);


        çeviriekran=this.findViewById(R.id.çeviriekran);
        diller=this.findViewById(R.id.diller);
        diller2=this.findViewById(R.id.diller2);



        yükle=findViewById(R.id.yükle);



        metin.setEnabled(false);

        seskayıt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

                try{
                    startActivityForResult(intent, başlatıldı);
                }catch(ActivityNotFoundException e)
                {
                    e.printStackTrace();
                    System.out.println("hata"+e);
                    startActivityForResult(intent,başlatılamadı);
                }
            }
        });

        TranslatorOptions translatorOptionsen=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.ENGLISH)

                .build();

        TranslatorOptions translatorOptionsrus=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.RUSSIAN)

                .build();

        TranslatorOptions translatorOptionschina=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.CHINESE)

                .build();

        TranslatorOptions translatorOptionsarapça=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.ARABIC)

                .build();
        TranslatorOptions translatorOptionsalmanca=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.GERMAN)

                .build();

        TranslatorOptions translatorOptionsitalyanca=new TranslatorOptions.Builder()
                .setSourceLanguage(TranslateLanguage.TURKISH)
                .setTargetLanguage(TranslateLanguage.FRENCH)

                .build();

        translatoren= Translation.getClient(translatorOptionsen);
        translatorrus= Translation.getClient(translatorOptionsrus);
        translatorchina= Translation.getClient(translatorOptionschina);
        translatorarapça= Translation.getClient(translatorOptionsarapça);
        translatoralmanca= Translation.getClient(translatorOptionsalmanca);
        translatoritalyanca= Translation.getClient(translatorOptionsitalyanca);

        downloadmodel();


        yayınlabtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Paylaş();
                metin.getText().clear();

            }
        });

        çeviriyayınlabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                çeviriyayınlabtn();
                metin.getText().clear();
                metinçeviri.getText().clear();

            }
        });

        dil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diller.setVisibility(View.VISIBLE);
                diller2.setVisibility(View.VISIBLE);
            }
        });







    }


    public void downloadmodel(){

        DownloadConditions downloadConditions=new DownloadConditions.Builder()
                .requireWifi().build();


        translatoren.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

        translatorrus.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

        translatorchina.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

        translatorarapça.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

        translatoralmanca.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

        translatoritalyanca.downloadModelIfNeeded(downloadConditions)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                        aBooleanen=true;
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        aBooleanen=false;
                    }
                });

    }

    public void buttondownloadmodel(View view){

        downloadmodel();

    }

    public void çeviriekran(View view){
        çeviriekran.setVisibility(View.VISIBLE);




    }

    public void ingilizce(View view){

        if(aBooleanen){
            translatoren.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    public void rus(View view){

        if(aBooleanen){
            translatorrus.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    public void china(View view){

        if(aBooleanen){
            translatorchina.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    public void arapça(View view){

        if(aBooleanen){
            translatorarapça.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    public void almanca(View view){

        if(aBooleanen){
            translatoralmanca.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    public void italyanca(View view){

        if(aBooleanen){
            translatoritalyanca.translate(metin.getText().toString())
                    .addOnSuccessListener(new OnSuccessListener<String>() {
                        @Override
                        public void onSuccess(String s) {

                            metinçeviri.setText(s);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            metinçeviri.setText(e.toString());
                        }
                    });
        }
    }

    protected void Paylaş() {


        String txt = metin.getText().toString();

        if(txt.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Boş paylaşım yapılamaz", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent share_intent = new Intent(android.content.Intent.ACTION_SEND); // intenti oluşturuyoruz
            share_intent.setType("text/plain");
            share_intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"");        // mesaj konusu olarak, Mobilhanem Örnek yazdık
            share_intent.putExtra(android.content.Intent.EXTRA_TEXT, metin.getText().toString()); // mesaj içeriği olarak, söylediğimiz söz gönderilecek
            startActivity(Intent.createChooser(share_intent, "Paylaşmak için birini seçiniz"));  // paylaşmak istediğimiz platformu seçiyoruz


        }

    }

    protected void çeviriyayınlabtn() {


        String txt = metin.getText().toString();

        if(txt.isEmpty())
        {
            Toast.makeText(getApplicationContext(), "Boş paylaşım yapılamaz", Toast.LENGTH_LONG).show();
        }
        else
        {
            Intent share_intent = new Intent(android.content.Intent.ACTION_SEND); // intenti oluşturuyoruz
            share_intent.setType("text/plain");
            share_intent.putExtra(android.content.Intent.EXTRA_SUBJECT,"");        // mesaj konusu olarak, Mobilhanem Örnek yazdık
            share_intent.putExtra(android.content.Intent.EXTRA_TEXT, metinçeviri.getText().toString()); // mesaj içeriği olarak, söylediğimiz söz gönderilecek
            startActivity(Intent.createChooser(share_intent, "Paylaşmak için birini seçiniz"));  // paylaşmak istediğimiz platformu seçiyoruz


        }

    }

    @Override
    protected void onActivityResult(int sorgu, int resultCode, Intent veri)
    {
        super.onActivityResult(sorgu, resultCode, veri);

        switch (sorgu) {
            case başlatıldı: {

                if (resultCode == RESULT_OK && veri != null)
                {

                    speech = veri.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    metin.setText(speech.get(0));

                    String yandex_key = "YANDEX_KEY";





                }
                break;
            }
            case başlatılamadı:{
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("uygulama çalıştırılamadı");

                AlertDialog alert = builder.create();
                alert.show();
            }


        }

    }


}