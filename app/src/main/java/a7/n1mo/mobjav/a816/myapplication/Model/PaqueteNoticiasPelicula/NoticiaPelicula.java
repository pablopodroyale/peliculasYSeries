package a7.n1mo.mobjav.a816.myapplication.Model.PaqueteNoticiasPelicula;

import com.google.gson.annotations.SerializedName;

/**
 * Created by pablo on 22/11/2016.
 */
public class NoticiaPelicula {
    private String title;
    private String comments;
    private String description;
    private String encoded;

    @Override
    public String toString() {
        return "NoticiaPelicula{" +
                "title='" + title + '\'' +
                ", comments='" + comments + '\'' +
                ", description='" + description + '\'' +
                ", encoded='" + encoded + '\'' +
                '}';
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncoded() {
        return encoded;
    }

    public void setEncoded(String encoded) {
        this.encoded = encoded;
    }
}
