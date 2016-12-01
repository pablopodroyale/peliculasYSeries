package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by pablo on 16/11/2016.
 */
public class SerieContainer {
    @SerializedName("results")
    private List<Serie> listaDeSeries;

    public List<Serie> getSeries() {
        return listaDeSeries;
    }
}
