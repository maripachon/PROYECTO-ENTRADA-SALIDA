public class PatinetaElectrica extends Vehiculo implements VehiculoConSerial {
    private String serial;

    public PatinetaElectrica(Persona persona, String color, String serial) {
        super(persona, color);
        this.serial = serial;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }
}
