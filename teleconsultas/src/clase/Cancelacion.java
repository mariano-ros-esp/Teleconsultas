package clase;

public class Cancelacion {
	private int codigo;
	private int hora;
	private int fecha;
	private String motivo;
	
	public Cancelacion(int codigo, int hora, int fecha, String motivo) {
		super();
		this.codigo = codigo;
		this.hora = hora;
		this.fecha = fecha;
		this.motivo = motivo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}
}
