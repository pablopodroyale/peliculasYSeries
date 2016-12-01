package a7.n1mo.mobjav.a816.myapplication.View.AdapterRecycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Model.Pelicula;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by taral on 11/3/2016.
 */

    public class AdapterRecyclerPeliculas extends RecyclerView.Adapter implements View.OnClickListener{

        public static final Integer CELDA_PARA_INICIAL = 0;
        public static final Integer CELDA_PARA_GRID = 1;
        public static final Integer CELDA_PARA_FAVORITOS = 2;

        private Context unContext;
        private List<Pelicula> listaDePeliculas;
        private View.OnClickListener listener;
        private Integer tipoDeCelda;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setListaDePeliculas(List<Pelicula> listaDePeliculas){
        this.listaDePeliculas = listaDePeliculas;
    }

    public List<Pelicula> getListaDePeliculas(){

        return listaDePeliculas;
    }

    public AdapterRecyclerPeliculas(Context unContext, List<Pelicula> listaDePeliculas, View.OnClickListener unListener, Integer tipoDeCelda){
        this.unContext = unContext;
        this.listaDePeliculas = listaDePeliculas;
        this.listener = unListener;
        this.tipoDeCelda = tipoDeCelda;
    }

    public AdapterRecyclerPeliculas(Context unContext, List<Pelicula> listaDePeliculas, Integer tipoDeCelda){
        this.unContext = unContext;
        this.listaDePeliculas = listaDePeliculas;
        this.tipoDeCelda = tipoDeCelda;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater layoutInflater = LayoutInflater.from(unContext);
        View view = null;
        if (tipoDeCelda.equals(CELDA_PARA_GRID)){
            view = layoutInflater.inflate(R.layout.detalle_fragment_celda, parent, false);
        } else if (tipoDeCelda.equals(CELDA_PARA_INICIAL)) {
            view = layoutInflater.inflate(R.layout.detalle_fragment_celda_inicial, parent, false);
        }else if (tipoDeCelda.equals(CELDA_PARA_FAVORITOS)) {
            view = layoutInflater.inflate(R.layout.celda_favoritos, parent, false);
        }
        PeliculaViewHolder peliculasViewHolder = new PeliculaViewHolder(view);

        view.setOnClickListener(listener);
        return peliculasViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        Pelicula unaPelicula = listaDePeliculas.get(position);
        PeliculaViewHolder obtenerHolder = (PeliculaViewHolder)holder;
        obtenerHolder.cargarPelicula(unaPelicula);
    }

    @Override
    public int getItemCount(){
        return listaDePeliculas.size();
    }

    @Override
    public void onClick(View view){
        listener.onClick(view);
    }

    private class PeliculaViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenCartelera;

        public PeliculaViewHolder(View itemView){
            super(itemView);
            imagenCartelera =(ImageView)itemView.findViewById(R.id.fotoCarteleraID);
        }
        public void cargarPelicula(Pelicula unaPelicula){
            //imagenCartelera.setImageResource(unaPelicula.getUrlImagen());
            Glide.with(unContext).load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,unaPelicula.getUrlImagen())).into(imagenCartelera);
        }
    }
}
