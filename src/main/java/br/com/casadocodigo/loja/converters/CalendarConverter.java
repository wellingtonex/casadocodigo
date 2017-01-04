package br.com.casadocodigo.loja.converters;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.DateTimeConverter;
import javax.faces.convert.FacesConverter;

@FacesConverter(forClass = Calendar.class)
public class CalendarConverter implements Converter {

	private DateTimeConverter converter = new DateTimeConverter();

	public CalendarConverter() {
		converter.setPattern("dd/MM/yyyy");
		converter.setTimeZone(TimeZone.getTimeZone("America/Fortaleza"));
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String dataTexto) {
		Date data = (Date) converter.getAsObject(context, component, dataTexto);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(data);
		return calendar;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object calendar) {
		if(calendar == null) {
			return null;
		}		
		
		return converter.getAsString(context, component, ((Calendar) calendar).getTime());
	}

}
