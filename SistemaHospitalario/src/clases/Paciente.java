package clases;

public class Paciente {
    private int codigo;
    private String nombres;
    private String apellidos;
    private String dni;
    private int edad;
    private int estado; // 0 = Registrado, 1 = Internado/Atendido

    public Paciente(int codigo, String nombres, String apellidos, String dni, int edad, int estado) {
        this.codigo = codigo;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.dni = dni;
        this.edad = edad;
        this.estado = estado;
    }

    // Getters y Setters
    public int getCodigo() { return codigo; }
    public void setCodigo(int codigo) { this.codigo = codigo; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDni() { return dni; }
    public void setDni(String dni) { this.dni = dni; }

    public int getEdad() { return edad; }
    public void setEdad(int edad) { this.edad = edad; }

    public int getEstado() { return estado; }
    public void setEstado(int estado) { this.estado = estado; }
}