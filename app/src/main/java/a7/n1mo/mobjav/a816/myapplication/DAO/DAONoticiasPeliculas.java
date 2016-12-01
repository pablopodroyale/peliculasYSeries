/*
package a7.n1mo.mobjav.a816.myapplication.DAO;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;
import android.util.Xml;

import org.xmlpull.v1.XmlPullParser;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import a7.n1mo.mobjav.a816.myapplication.Model.PaqueteNoticiasPelicula.NoticiaPelicula;

*/
/**
 * Created by pablo on 22/11/2016.
 *//*

public class DAONoticiasPeliculas {
    //PARSEO EN XML
    public NoticiaPelicula getNoticiaPeliculaXml(Context context) {
        XmlPullParser parser = Xml.newPullParser();

        NoticiaPelicula noticiaPelicula=null;
        List<NoticiaPelicula> listaNoticiasPeliculas = null;


        try {
            AssetManager manager = context.getAssets();
            InputStream input = manager.open("http://www.cinemania.es/noticias/feed/");

            parser.setInput(input, null);
            Integer status = parser.getEventType();
            while (status != XmlPullParser.END_DOCUMENT) {
                switch (status) {
                    case XmlPullParser.START_DOCUMENT:
                        break;

                    case XmlPullParser.START_TAG:


                        if (parser.getName().equals("item")) {
                            noticiaPelicula= new NoticiaPelicula();
                            listaNoticiasPeliculas= new ArrayList<>();
                        }

                        if (parser.getName().equals("title")) {
                            String title = parser.nextText();
                            noticiaPelicula.setTitle(title);
                        }

                        if (parser.getName().equals("comments")) {
                            String comments = parser.nextText();
                            noticiaPelicula.setComments(comments);
                        }
                        if (parser.getName().equals("description")) {
                            String description = parser.nextText();
                            noticiaPelicula.setDescription(description);
                        }

                        if (parser.getName().equals("encoded")) {
                            String description = parser.nextText();
                            noticiaPelicula.setDescription(description);


                        }

                        break;

                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("noticiaPelicula")) {
                            listaNoticiasPeliculas.add(noticiaPelicula);
                        }
                        break;
                }
                status = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Log.d("tag", listaNoticiasPeliculas.toString());
        return listaNoticiasPeliculas ;

    }
}
*/
