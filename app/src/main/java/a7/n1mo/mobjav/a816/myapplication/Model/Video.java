package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pablo on 10/11/2016.
 */
public class Video {
    private int fotoId;
    @SerializedName("Title")
    private String titulo;
    @SerializedName("Actors")
    private String actores;
    private String descripcion;
    @SerializedName("Genre")
    private String genero;

    @Override
    public String toString() {
        return "Video{" +
                "fotoId=" + fotoId +
                ", titulo='" + titulo + '\'' +
                ", actores='" + actores + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", genero='" + genero + '\'' +
                '}';
    }

    public Video(int fotoId, String titulo, String actor, String descripcion, String genero) {
        this.fotoId = fotoId;
        this.titulo = titulo;
        this.actores = actor;
        this.descripcion = descripcion;
        this.genero = genero;
    }

    public int getFotoId() {
        return fotoId;
    }

    public void setFotoId(int fotoId) {
        this.fotoId = fotoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getActores() {
        return actores;
    }

    public void setActores(String actores) {
        this.actores = actores;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
