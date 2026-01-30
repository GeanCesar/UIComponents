package br.com.geancesar.uicomponents.activities;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.geancesar.uicomponents.R;
import br.com.geancesar.uicomponents.componentes.ProgressButton;
import br.com.geancesar.uicomponents.componentes.listeners.ProgressButtonListener;


public class MainActivity extends AppCompatActivity implements ProgressButtonListener {

    ProgressButton btTeste;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        btTeste = findViewById(R.id.btTeste);
        btTeste.setListener(this);
        btTeste.setOnClickListener( l -> {
            btTeste.setLoading();
            new AsyncTask() {
                @Override
                protected Object doInBackground(Object[] objects) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return null;
                }

                @Override
                protected void onPostExecute(Object o) {
                    super.onPostExecute(o);
                    btTeste.setNormal();
                }
            }.execute();
        });
    }

    @Override
    public void onLoading(ProgressButton botao) {

    }

    @Override
    public void onNormal(ProgressButton botao) {

    }
}
