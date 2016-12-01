package a7.n1mo.mobjav.a816.myapplication.View;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import a7.n1mo.mobjav.a816.myapplication.R;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_DELAY = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // ESTABLECE QUE LA ORIENTACION SEA VERTICAL.
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // ESCONDE LA BARRA DE "ACTION BAR".
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_splash_screen);

        Toast.makeText(SplashScreenActivity.this, "Cargando... ", Toast.LENGTH_LONG).show();
        TimerTask task = new TimerTask() {
            @Override
            public void run(){

                // COMIENZO DE LA PROXIMA ACTIVIDAD.
                Intent mainIntent = new Intent().setClass(
                        SplashScreenActivity.this, ActividadPrincipal.class);
                startActivity(mainIntent);

                // CERRAR LA ACTIVIDAD PARA QUE EL USUARIO NO SEA CAPAZ DE IR PARA ATRAS PRESIONANDO EL BOTON "BACK BUTTON".
                finish();
            }
        };

        // SIMULA UN PROCESO LARGO DE COMIENZO DE APLICACION.
        Timer timer = new Timer();
        timer.schedule(task, SPLASH_SCREEN_DELAY);
    }
}
