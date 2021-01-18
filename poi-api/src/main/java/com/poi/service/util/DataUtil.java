package com.poi.service.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DataUtil {

	public final static String FORMATO_DIA_MES_ANO = "dd/MM/yyyy";
	public final static String FORMATO_ANO_MES_DIA = "yyyy-MM-dd";
	
	public static Date simpleDateFormat(String strDate) throws ParseException {
		return parseDate(strDate, FORMATO_DIA_MES_ANO);
	}
	
	public static Date parseDate(String strDate, String format) throws ParseException {

		if(strDate == null || "".equals(strDate)) {
			return null;
		}
		
		SimpleDateFormat formatter = new SimpleDateFormat(format);

		Date date = formatter.parse(strDate);

		return date;
	}
	
	public static String intervaloEntreDatas(Date dtaInicial, Date dtaFinal) {
		
		if(dtaFinal == null) {
			return null;
		}
		
		LocalDateTime  localDateAntigo = dtaInicial.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		LocalDateTime  localDateNovo = dtaFinal.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();

		Duration periodo = Duration.between(localDateAntigo, localDateNovo);
		
		long dias = periodo.toDays();
		long horas = periodo.toHoursPart();
		long minutos = periodo.toMinutesPart();
		long segundos = periodo.toSecondsPart();
		
		NumberFormat formatter = new DecimalFormat("00");
		String descricaoPeriodo = (dias > 0 ? dias + " Dias " : "").concat(formatter.format(horas) + ":").concat(formatter.format(minutos) + ":").concat(formatter.format(segundos));
		
		return descricaoPeriodo;
	}

}
