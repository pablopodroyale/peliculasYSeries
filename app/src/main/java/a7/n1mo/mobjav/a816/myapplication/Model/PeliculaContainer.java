package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pablo on 16/11/2016.
 */
public class PeliculaContainer {
    @SerializedName("results")
    private List<Pelicula> listaDePeliculas;

    public List<Pelicula> getPeliculas() {
        return listaDePeliculas;
    }
}
