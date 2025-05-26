import java.time.LocalDateTime;

public class Computador {
    private Persona persona;
    private String marca;
    private String serial;
    private LocalDateTime horaIngreso;
    private LocalDateTime horaSalida;

    public Computador(Persona persona, String marca, String serial) {
        this.persona = persona;
        this.marca = marca;
        this.serial = serial;
        this.horaIngreso = LocalDateTime.now();
        this.horaSalida = null;
    }

    public Persona getPersona() {
        return persona;
    }

    public String getMarca() {
        return marca;
    }

    public String getSerial() {
        return serial;
    }

    public LocalDateTime getHoraIngreso() {
        return horaIngreso;
    }

    public LocalDateTime getHoraSalida() {
        return horaSalida;
    }

    public void registrarSalida() {
        this.horaSalida = LocalDateTime.now();
    }
}

