package com.example.futrivia;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private String respostaCorreta;
    private int respostaCorretaContador = 0;
    private int quizCount = 1;

    static final private int QUIZCOUNT = 30;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData [][]={
            {"Campeão da Libertadores 1990","Olimpia", "Boca Juniors", "River Plate", "Nacional"},
            {"Melhor do Mundo 2001","Figo", "Beckham", "Ronaldo", "Zidane"},
            {"Sede Copa do Mundo 1938","França", "Suiça", "Itália", "Espanha"},
            {"Maior finalista em Copas","Alemanha", "Brasil", "Itália", "Holanda"},
            {"Maior campeão francês","Saint-Etienne", "PSG", "Marseille", "Monaco"},
            {"Maior campeão paulista","Corinthians", "Palmeiras", "Santos", "São Paulo"},
            {"1° campeão da Libertadores","Peñarol", "Nacional", "Boca Juniors", "River Plate"},
            {"1° campeão da UCL (1992-93)","Marseille", "Real Madrid", "Milan", "Bayern Munich"},
            {"1° campeão da Copa do Mundo","Uruguai", "Brasil", "Alemanha", "Itália"},
            {"1° campeão do Brasileirão (1971)","Atlético-MG", "Flamengo", "Palmeiras", "Santos"},
            {"Maior campeão turco","Galatasaray", "Fenerbahçe", "Besiktas", "Trabzonspor"},
            {"Maior campeão espanhol","Real Madrid", "Barcelona", "Valencia", "Sevilla"},
            {"Mascote Copa do Mundo 1998","Footix", "Juanito", "Naranjito", "Zakumi"},
            {"Bola Copa do Mundo 1978","Tango", "Tiento", "Questra", "Fevernova"},
            {"Campeão Eurocopa 1996","Alemanha", "Holanda", "Itália", "Inglaterra"},
            {"Campeão C. Confederações 2001","França", "Brasil", "Inglaterra", "Argentina"},
            {"Maior campeão inglês","Manchester United", "Chelsea", "Manchester City", "Arsenal"},
            {"Maior campeão italiano","Juventus", "Inter", "Milan", "Lazio"},
            {"Campeão Brasileirão 1997","Vasco", "Palmeiras", "Cruzeiro", "Grêmio"},
            {"Campeão Sul-Americana 2005","Boca Juniors", "River Plate", "Internacional", "Pumas"},
            {"Campeão Champions 1996-97","Dortmund", "Juventus", "Real Madrid", "Barcelona"},
            {"Maior campeão português","Benfica", "Porto", "Braga", "Sporting"},
            {"Maior artilheiro da Copa","Klose", "Ronaldo", "Pelé", "Rummenigge"},
            {"Maior artilheiro Brasileirão","Dinamite", "Edmundo", "Romario", "Fred"},
            {"Artilheiro Copa 1990","Schilacci", "Milla", "Lineker", "Klinsmann"},
            {"Melhor do Mundo de 1999","Rivaldo", "Zidane", "Beckham", "Batistuta"},
            {"Campeão inglês 2007-08","Manchester United", "Chelsea", "Liverpool", "Arsenal"},
            {"Maior campeão argentino","River Plate", "Boca Juniors", "Independiente", "Estudiantes"},
            {"Maior campeão holandês","Ajax", "PSV", "Feyenoord", "Twente"},
            {"Artilheiro da Holanda","Van Persie", "Robben", "Van Nistelrooy", "Van Basten"},

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        setTheme(R.style.Theme_SplashScreen);

        setContentView(R.layout.activity_main);

        for (String[] quizDatum : quizData) {
            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizDatum[0]);
            tmpArray.add(quizDatum[1]);
            tmpArray.add(quizDatum[2]);
            tmpArray.add(quizDatum[3]);
            tmpArray.add(quizDatum[4]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        TextView contadorLabel = (TextView) findViewById(R.id.contadorLabel);
        contadorLabel.setText("Q"+ quizCount);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        TextView perguntaLabel = (TextView) findViewById(R.id.perguntaLabel);
        perguntaLabel.setText(quiz.get(0));
        respostaCorreta = quiz.get(1);

        quiz.remove(0);
        Collections.shuffle(quiz);

        Button respostaBtn1 = (Button) findViewById(R.id.respostaBtn1);
        respostaBtn1.setText(quiz.get(0));
        Button respostaBtn2 = (Button) findViewById(R.id.respostaBtn2);
        respostaBtn2.setText(quiz.get(1));
        Button respostaBtn3 = (Button) findViewById(R.id.respostaBtn3);
        respostaBtn3.setText(quiz.get(2));
        Button respostaBtn4 = (Button) findViewById(R.id.respostaBtn4);
        respostaBtn4.setText(quiz.get(3));

        quizArray.remove(randomNum);
    }

    public void checkResposta(View view){
        Button respostaBtn = (Button) findViewById(view.getId());
        String btnText;
        btnText = respostaBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(respostaCorreta)){
            alertTitle = "Correto!";
            respostaCorretaContador++;
        }

        else{
            alertTitle = "Errado";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("Resposta: "+ respostaCorreta + "\nPontuação"+respostaCorretaContador);
        builder.setPositiveButton("PRÓXIMO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (quizCount != QUIZCOUNT) {
                    quizCount++;
                    showNextQuiz();
                } else
                    resFinal();
            }

            private void resFinal() {
            }

        });
        builder.setCancelable(false);
        builder.setNegativeButton("DESISTIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resFinal();
            }
        });
        builder.show();
    }

    public void resFinal(){
        String finalScore;
        if(respostaCorretaContador == QUIZCOUNT){
            finalScore = "Gabaritou! PARABÉNS!";
        } else {
            finalScore = "Você não acertou todas";
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("RESULTADO FINAL");
        builder.setMessage(finalScore + "\nPontuação:" + respostaCorretaContador);
        builder.setNeutralButton("RECOMEÇAR", (dialog, i) -> {
            Intent restartIntent = getBaseContext().getPackageManager()
                    .getLeanbackLaunchIntentForPackage(getBaseContext().getPackageName());
            restartIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(restartIntent);
        });
        builder.setCancelable(false);
        builder.show();
    }
}
