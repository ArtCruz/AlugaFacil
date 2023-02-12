package com.example.myapplication_arthur.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateUtil {

    public static final String FORMATO_DIA_MES_ANO = "dd/MM/yyyy";

    public static boolean compararDataAtual(String data){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = dateFormat.parse(data);
            Date now = dateFormat.parse(dateFormat.format(new Date()));
            if(now.equals(date1) || now.before(date1)){
                return true;
            }else{
                return false;
            }
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compararDatas(String dataAtual, String dataComparar){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date1 = dateFormat.parse(dataAtual);
            Date date2 = dateFormat.parse(dataComparar);
            if (date2.before(date1) || date2.equals(date1)) {
                return true;
            } else {
                return false;
            }
        }catch (ParseException e){
            e.printStackTrace();
            return false;
        }
    }

    public static boolean compararDatas(Date date1, Date date2){
        if (date2.before(date1) || date2.equals(date1)) {
            return true;
        } else {
            return false;
        }
    }

    public static Date converteStringToDate(String data, String formato){
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            Date dataFormatada = sdf.parse(data);
            return dataFormatada;
        }catch(ParseException e){
            e.printStackTrace();
            return new Date();
        }
    }

    public static String converteDateToString(Date data, String formato){
        DateFormat dateFormat = new SimpleDateFormat(formato);
        String retorno = dateFormat.format(data);
        return retorno.replace("\"", "");
    }

    public static long diferencaDias(String data1, String data2){
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy"); // dd/MM/yyyy é o formato brasileiro que você está usando, para mais formatos, veja o link de referência

            Date dateUm = simpleDateFormat.parse(data1);
            Date dateDois = simpleDateFormat.parse(data2);

            long diferencaEmMilisegundos = dateDois.getTime() - dateUm.getTime();

            return diferencaEmMilisegundos / 1000 / 60 / 60 / 24;
        }catch(ParseException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static long diferencaDias(Date dateUm, Date dateDois){
        long diferencaEmMilisegundos = dateDois.getTime() - dateUm.getTime();

        return diferencaEmMilisegundos / 1000 / 60 / 60 / 24;
    }

    public static boolean isDataValida(String data, String formato){
        SimpleDateFormat sdf = new SimpleDateFormat(formato);
        try {
            Date dataFormatada = sdf.parse(data);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
