package prNotas;

public class AlumnosErasmus extends Alumno {
	private double notaPractica;
	
	public AlumnosErasmus(String nombre, String dni, double nota, double notaP) throws AlumnoException {
		super(nombre, dni, nota);
		
		notaPractica = notaP;
	}
	
	@Override
	public double getNota() {
		double maximo = -1;
		if (notaPractica > super.getNota()) {
			maximo = notaPractica;
		} else {
			maximo = super.getNota();
		}
		return maximo;
	}
	
	@Override
	public String toString() {
		return "Erasmus " + super.toString();
	}
}
