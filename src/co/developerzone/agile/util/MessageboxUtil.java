package co.developerzone.agile.util;

import java.util.Date;

import org.zkoss.zul.Messagebox;

public class MessageboxUtil {
	public static void showGenericException(Exception e) {
		try {
			StringBuilder errores = new StringBuilder();
			Throwable ex = e;
			while(ex.getCause() != null) {
				errores.append(ex.toString());
				errores.append("\n");
				ex = ex.getCause();
			}
			Messagebox.show(new Date() + "\n" + errores.toString(), "Error al intentar realizar la operación", Messagebox.OK, Messagebox.ERROR);
			e.printStackTrace();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	
	public static void showAlert(String titulo, String contenido) {
		try {
			Messagebox.show(contenido, titulo, Messagebox.OK, Messagebox.EXCLAMATION);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
