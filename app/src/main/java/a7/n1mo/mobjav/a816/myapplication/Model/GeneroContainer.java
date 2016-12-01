package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pablo on 18/11/2016.
 */
public class GeneroContainer {
    @SerializedName("genres")
    private List<Genero>listaDeGeneros;

    public GeneroContainer(List<Genero> listaDeGeneros) {
        this.listaDeGeneros = listaDeGeneros;
    }

    public List<Genero> getListaDeGeneros() {
        return listaDeGeneros;
    }
}
