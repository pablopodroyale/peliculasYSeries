package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pablo on 1/11/2016.
 */
public class Serie /*extends Video*/ {
    @SerializedName("name")
    private String titulo;

    @SerializedName("overview")
    private String descripcion;

    @SerializedName("id")
    private Integer id;
    @SerializedName("poster_path")
    private String urlImagen;

    private String temporada;
    @SerializedName("vote_average")
    private Double puntaje;

    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }
    /*public Serie(int fotoId, String titulo, String actor, String descripcion, String genero, String temporada) {
        super(fotoId,titulo,actor,descripcion,genero);
        this.temporada = temporada;
    }*/

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public void setTemporada(String temporada) {
        this.temporada = temporada;
    }

    public String getTemporada() {
        return temporada;
    }
}
