package a7.n1mo.mobjav.a816.myapplication.View;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerPelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentDetalle.FragmentDetallePeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentDetalle.FragmentDetalleSeries;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerFavooritosPeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerPantallaInicial;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentRecycler.FragmentRecyclerSeries;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentViewPager.FragmentViewPagerPeliculas;
import a7.n1mo.mobjav.a816.myapplication.View.FragmentViewPager.FragmentViewPagerSeries;
import a7.n1mo.mobjav.a816.myapplication.utils.ResultListener;

public class ActividadPrincipal extends AppCompatActivity implements FragmentRecyclerPeliculas.SeleccionadorDePeliculas, FragmentRecyclerSeries.SeleccionadorDeSeries, FragmentRecyclerPantallaInicial.SeleccionadorDePeliculasPantallaInicial {
    DrawerLayout drawerLayout;
    FragmentManager fragmentManager;
    Toolbar miToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        FragmentRecyclerPantallaInicial fragmentRecyclerPantallaInicial = new FragmentRecyclerPantallaInicial();
        fragmentTransaction.replace(R.id.acaVaElFragment, fragmentRecyclerPantallaInicial);
        fragmentTransaction.commit();



        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(new ListenerNavigationView());

        miToolbar= (Toolbar)findViewById(R.id.toolbarAct1);
        miToolbar.setTitle("PANTALLA PRINCIPAL");
        setSupportActionBar(miToolbar);

        ActionBarDrawerToggle toggle= new ActionBarDrawerToggle(this, drawerLayout,miToolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();


    }
    //LE DIGO AL TOOLBAR QUE AGREGUE ESTAS OPCIONES
    @Override
    public boolean onCreateOptionsMenu(Menu menuToolbar){
        getMenuInflater().inflate(R.menu.menu_toolbar, menuToolbar);
        return true;
    }

    //METODO PARA DARLE COMPORTAMIENTO A CADA BOTON DEL ACTION BAR
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action1_toolbar_search:
                Toast.makeText(this,"Buscando",Toast.LENGTH_SHORT).show();
                return true;

            case R.id.action3_toolbar_share:
                Toast.makeText(this,"Compartir",Toast.LENGTH_SHORT).show();
                Intent unIntent= new Intent(ActividadPrincipal.this, ActividadLogins.class);
                startActivity(unIntent);

                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private class ListenerNavigationView implements NavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            seleccionarUnItem(item);

            return true;
        }
    }

    public void seleccionarUnItem(MenuItem item) {
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);

        if (item.getItemId() == R.id.itemPeliculas) {
            FragmentViewPagerPeliculas fragmentViewPagerPeliculas = new FragmentViewPagerPeliculas();
            fragmentTransaction.replace(R.id.acaVaElFragment, fragmentViewPagerPeliculas);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            miToolbar.setTitle("Peliculas");


        } else if (item.getItemId() == R.id.itemSeries) {
            FragmentViewPagerSeries fragmentViewPagerSeries = new FragmentViewPagerSeries();
            fragmentTransaction.replace(R.id.acaVaElFragment, fragmentViewPagerSeries);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            miToolbar.setTitle("Series");

        } else if (item.getItemId() == R.id.itemFavoritos) {
            FragmentRecyclerFavooritosPeliculas fragmentRecyclerFavooritosPeliculas = new FragmentRecyclerFavooritosPeliculas();
            fragmentTransaction.replace(R.id.acaVaElFragment, fragmentRecyclerFavooritosPeliculas);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
            miToolbar.setTitle("Peliculas favoritas");
            drawerLayout.closeDrawers();
        }
    }

    @Override
    public void peliculaSeleccionada(Pelicula unaPelicula) {
        FragmentDetallePeliculas unFragmentDetallePelicula = new FragmentDetallePeliculas();

        // Crear Bundle
        Bundle unBundleConDatosDeLaPeli = new Bundle();

        //CARGAR LOS DATOS EN EL BUNDLE
        unBundleConDatosDeLaPeli.putString("id",unaPelicula.getId());
        unBundleConDatosDeLaPeli.putString("tituloDeLaPeli", unaPelicula.getTitulo());
        unBundleConDatosDeLaPeli.putString("fotoPeli", unaPelicula.getUrlImagen());
        unBundleConDatosDeLaPeli.putString("descripcionPeli", unaPelicula.getDescripcion());
        unBundleConDatosDeLaPeli.putDouble("puntajePeli", unaPelicula.getPuntaje());


        // Adjuntar Bundle al fragment usando setArguments()
        unFragmentDetallePelicula.setArguments(unBundleConDatosDeLaPeli);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.acaVaElFragment, unFragmentDetallePelicula);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /*public void peliculaSeleccionadaEnPantallaInicial(Pelicula unaPelicula) {
        FragmentRecyclerPantallaInicial fragmentRecyclerPantallaInicial = new FragmentRecyclerPantallaInicial();

        // Crear Bundle
        Bundle unBundleConDatosDeLaPeli = new Bundle();

        //CARGAR LOS DATOS EN EL BUNDLE
        unBundleConDatosDeLaPeli.putString("tituloDeLaPeli", unaPelicula.getTitulo());
        unBundleConDatosDeLaPeli.putString("fotoPeli", unaPelicula.getUrlImagen());
        unBundleConDatosDeLaPeli.putString("descripcionPeli", unaPelicula.getDescripcion());
        unBundleConDatosDeLaPeli.putDouble("puntajePeli", unaPelicula.getPuntaje());


        // Adjuntar Bundle al fragment usando setArguments()
        fragmentRecyclerPantallaInicial.setArguments(unBundleConDatosDeLaPeli);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.acaVaElFragment, fragmentRecyclerPantallaInicial);

        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
*/
    @Override
    public void serieSeleccionada(Serie unaSerie) {
        FragmentDetalleSeries unFragmentDetalleSerie = new FragmentDetalleSeries();

        Bundle unBundleConDatosDeLaSerie = new Bundle();

        unBundleConDatosDeLaSerie.putString("tituloDeLaSerie", unaSerie.getTitulo());
        unBundleConDatosDeLaSerie.putString("fotoSerie", unaSerie.getUrlImagen());
        unBundleConDatosDeLaSerie.putString("descripcionSerie", unaSerie.getDescripcion());
        unBundleConDatosDeLaSerie.putDouble("puntajeSerie", unaSerie.getPuntaje());

        unFragmentDetalleSerie.setArguments(unBundleConDatosDeLaSerie);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.acaVaElFragment, unFragmentDetalleSerie);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    //metodo para paretar el boton atras y que vuelva al fragment principal
        @Override
        public void onBackPressed() {
            if (!fragmentManager.popBackStackImmediate()){
                super.onBackPressed();
            }
        }

    public void cargarInfoPeliculaJson(View view){
        ResultListenerView resultListenerView = new ResultListenerView();
        ControllerPelicula controllerPelicula = new ControllerPelicula();
        //PIDE LAS Peliculas Y LE DICE QUE LE AVISE CUANDO TERMINO (ResultListener)
        //controllerPelicula.obtenerPeliculasPopularesDeJson(resultListenerView);
    }
    public class ResultListenerView implements ResultListener<Pelicula> {

        @Override
        public void finish(Pelicula resultado) {
            //QUE HACE LA VISTA CUANDO RECIBE EL RESULTADO
            //MUESTRA LA Pelicula
            Toast.makeText(ActividadPrincipal.this, resultado.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}

