package a7.n1mo.mobjav.a816.myapplication.Model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pablo on 1/11/2016.
 */
public class Pelicula /* extends Video*/ {
@SerializedName("original_title")
    private String titulo;

    @SerializedName("overview")
    private String descripcion;

    @SerializedName("id")
    private String id;
    @SerializedName("poster_path")
    private String urlImagen;
    @SerializedName("vote_average")
    private Double puntaje;


    public Pelicula(){

    }

    public Pelicula(String titulo, String descripcion, String urlImagen, Double puntaje,String id){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.urlImagen = urlImagen;
        this.id = id;
        this.puntaje = puntaje;
    }



    public Double getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(Double puntaje) {
        this.puntaje = puntaje;
    }

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

/* public Pelicula(int fotoId, String titulo, String actor, String descripcion, String genero){
        super(fotoId,titulo,actor,descripcion,genero);
    }*/
}
