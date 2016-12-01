package a7.n1mo.mobjav.a816.myapplication.View.FragmentDetalle;


import android.content.Context;
import android.media.Rating;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a7.n1mo.mobjav.a816.myapplication.Controller.ControllerPelicula;
import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentDetallePeliculas extends Fragment {
    ControllerPelicula controllerPelicula;
    FloatingActionButton floatingActionButton;
    String elContenidoDelSobreId;
    //Crear constructor que reciba una película

    //On Create View
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vistaADevolver;
        vistaADevolver = inflater.inflate(R.layout.detalle_pelicula, container, false);

        floatingActionButton = (FloatingActionButton)vistaADevolver.findViewById(R.id.floatingActionButton);
        controllerPelicula = new ControllerPelicula();


        //Recibir bundle usando getArguments()
        Bundle unBundle = getArguments();


        // pasar los parámetros de la película a los TextView o lo que sea
        final String elContenidoDelSobreTitulo = unBundle.getString("tituloDeLaPeli");
        TextView unTextViewTiuloPeli = (TextView) vistaADevolver.findViewById(R.id.textViewTituloPelicula);
        unTextViewTiuloPeli.setText(elContenidoDelSobreTitulo);

        final String elContenidoDelSobreDescripcion = unBundle.getString("descripcionPeli");
        TextView textViewDescripcion = (TextView) vistaADevolver.findViewById(R.id.textViewDescripcionPelicula);
        textViewDescripcion.setText(elContenidoDelSobreDescripcion);

        elContenidoDelSobreId= unBundle.getString("id");



        final Double elContenidoDelSobrePuntaje = unBundle.getDouble("puntajePeli");
        Float ratingFloat= Float.parseFloat(elContenidoDelSobrePuntaje.toString());
        RatingBar rating = (RatingBar) vistaADevolver.findViewById(R.id.rating);
        rating.setRating(ratingFloat/2);

        final String elContenidoDelSobreImagen = unBundle.getString("fotoPeli");
        ImageView unImageVIewPeliculas = (ImageView) vistaADevolver.findViewById(R.id.imageViewPelicula);
        Glide.with(getContext()).load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,elContenidoDelSobreImagen)).into(unImageVIewPeliculas);


        checkEstadoBoton();


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Pelicula pelicula = new Pelicula(elContenidoDelSobreTitulo, elContenidoDelSobreDescripcion, elContenidoDelSobreImagen, elContenidoDelSobrePuntaje,elContenidoDelSobreId);
                controllerPelicula.agregarPeliculaAFavoritos(pelicula, getContext());
                checkEstadoBoton();
            }

        });

        return vistaADevolver;
    }


    public void checkEstadoBoton(){
        if(controllerPelicula.checkFavorito(elContenidoDelSobreId, getContext())){
            floatingActionButton.setImageResource(android.R.drawable.ic_delete);

        }else {
            floatingActionButton.setImageResource(R.drawable.estrellita);
        }
    }
}
