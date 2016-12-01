package a7.n1mo.mobjav.a816.myapplication.View.FragmentDetalle;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by taral on 11/15/2016.
 */

public class FragmentDetalleSeries extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater miInflador, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vistaADevolver;
        vistaADevolver = miInflador.inflate(R.layout.detalle_serie, container, false);

        //Recibir bundle usando getArguments()
        Bundle unBundle = getArguments();

        // pasar los parámetros de la película a los TextView o lo que sea
        String elContenidoDelSobreTitulo = unBundle.getString("tituloDeLaSerie");
        TextView unTextView = (TextView) vistaADevolver.findViewById(R.id.textViewTituloSerie);
        unTextView.setText(elContenidoDelSobreTitulo);

        String elContenidoDelSobreDescripcion = unBundle.getString("descripcionSerie");
        TextView textViewDescripcion = (TextView) vistaADevolver.findViewById(R.id.textViewDescripcionSerie);
        textViewDescripcion.setText(elContenidoDelSobreDescripcion);

        Double elContenidoDelSobrePuntaje = unBundle.getDouble("puntajeSerie");
        Float ratingFloat= Float.parseFloat(elContenidoDelSobrePuntaje.toString());
        RatingBar rating = (RatingBar) vistaADevolver.findViewById(R.id.ratingSerie);
        rating.setRating(ratingFloat/2);

        String elContenidoDelSobreImagen = unBundle.getString("fotoSerie");
        ImageView unaImageViewSeries = (ImageView) vistaADevolver.findViewById(R.id.imageViewSerie);
        Glide.with(getContext()).load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,elContenidoDelSobreImagen)).into(unaImageViewSeries);

        return vistaADevolver;
    }
}
