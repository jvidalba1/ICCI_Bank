package com.infy.icci.converter;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

public class SimpleDateConverter implements Converter {

	public SimpleDateConverter() {
		// TODO Auto-generated constructor stub
	}

	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
	     Date date = (Date) arg2;
         SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yy");

         return format.format(date);
   }

}
