package com.example.cm2019pf.helpers;


/**
 * Created by Pedro on 16/05/2017.
 */

public class Common {



    public static final String URL_HOSPITAL_PRINCIPAL = "http://tempos.min-saude.pt/api.php/";
    public static final String URL_API_LISTA_INSTITUICAO_TEMPO_ESPERA = "http://tempos.min-saude.pt/api.php/";
    //url show nearby places
    public static final String URL_API_EXIBE_HOSPITAIS_PROXIMOS = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=-33.8670522,151.1957362&radius=1500&type=restaurant&keyword=cruise&key=YOUR_API_KEY";
    public static final int REQUEST_LOCATION = 2;
    public static final int REQUEST_CALL = 1;

    public static final String URL_GET_DISTANCE_API = "https://maps.googleapis.com/maps/api/distancematrix/json?units=metric&origins=-23.562984,-46.654261&destinations=-22.951669,-43.210466&key=KEY_CODE";






    public static boolean isNullOrEmptyString(String content) {
        return (content != null && !content.trim().isEmpty() ? false : true);
    }


}
