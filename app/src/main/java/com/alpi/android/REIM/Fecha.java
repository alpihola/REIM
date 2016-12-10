package com.alpi.android.REIM;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Fecha {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    String fechaActual = (dateFormat.format(Calendar.getInstance().getTime()));

}