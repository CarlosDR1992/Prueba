package prNotas;

import java.util.Comparator;

public class OrdenAlternativoAlumno implements Comparator<Alumno> {

	@Override
	public int compare(Alumno al1, Alumno al2) {
		int res = 0;
		
		res = al1.getDni().compareToIgnoreCase(al2.getDni());
		if (res == 0) {
			res = al1.getNombre().compareToIgnoreCase(al2.getNombre());
		}
		
		return res;
	}

}
