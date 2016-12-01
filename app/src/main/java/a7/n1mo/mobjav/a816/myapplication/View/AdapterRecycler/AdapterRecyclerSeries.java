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
import a7.n1mo.mobjav.a816.myapplication.Model.Serie;
import a7.n1mo.mobjav.a816.myapplication.R;
import a7.n1mo.mobjav.a816.myapplication.utils.TMDBHelper;

/**
 * Created by taral on 11/3/2016.
 */

public class AdapterRecyclerSeries extends RecyclerView.Adapter implements View.OnClickListener{

    public static final Integer CELDA_PARA_INICIAL = 0;
    public static final Integer CELDA_PARA_GRID = 1;

    private Context unContext;
    private List<Serie> listaDeSeries;
    private View.OnClickListener listener;
    private Integer tipoDeCelda;

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public void setListaDeSeries(List<Serie> listaDeSeries){
        this.listaDeSeries = listaDeSeries;
    }
    public List<Serie> getListaDeSeries(){

        return listaDeSeries;
    }

    public AdapterRecyclerSeries(Context unContext, List<Serie> listaDeSeries, View.OnClickListener unListener, Integer tipoDeCelda){
        this.unContext = unContext;
        this.listaDeSeries = listaDeSeries;
        this.listener = unListener;
        this.tipoDeCelda = tipoDeCelda;
    }

    public AdapterRecyclerSeries(Context unContext, List<Serie> listaDeSeries, Integer tipoDeCelda){
        this.listaDeSeries = listaDeSeries;
        this.unContext = unContext;
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
        }
        SeriesViewHolder seriesViewHolder = new SeriesViewHolder(view);

        view.setOnClickListener(listener);
        return seriesViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position){
        Serie unaSerie = listaDeSeries.get(position);
        SeriesViewHolder obtenerHolder = (SeriesViewHolder)holder;
        obtenerHolder.cargarSerie(unaSerie);
    }

    @Override
    public int getItemCount(){
        return listaDeSeries.size();
    }

    @Override
    public void onClick(View view){
        listener.onClick(view);
    }

    private class SeriesViewHolder extends RecyclerView.ViewHolder{
        private ImageView imagenCartelera;

        public SeriesViewHolder(View itemView){
            super(itemView);
            imagenCartelera =(ImageView)itemView.findViewById(R.id.fotoCarteleraID);
        }
        public void cargarSerie(Serie unaSerie){
            //imagenCartelera.setImageResource(unaSerie.getFotoId());
            Glide.with(unContext).load(TMDBHelper.getImagePoster(TMDBHelper.IMAGE_SIZE_W300,unaSerie.getUrlImagen())).into(imagenCartelera);
        }
    }
}
