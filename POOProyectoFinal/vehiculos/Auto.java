package POOProyectoFinal.vehiculos;

public class Auto extends Vehiculo {
        private boolean tieneAireAcondicionado;
        private boolean esDeLujo;
    
        public Auto(String placa, String marca, double precio, int cilindraje, boolean tieneAireAcondicionado, boolean esDeLujo) {
            super(placa, marca, precio, cilindraje); // Llamamos al constructor de la clase padre (Vehiculo)
            this.tieneAireAcondicionado = tieneAireAcondicionado;
            this.esDeLujo = esDeLujo;
        }
    
        // Métodos adicionales de la clase Auto
    private boolean tieneRadio;
    private boolean tieneNavegador;

    public Auto(String marca, double precio, int cilindraje, boolean tieneRadio, boolean tieneNavegador) {
        super(marca, precio, cilindraje);
        this.tieneRadio = tieneRadio;
        this.tieneNavegador = tieneNavegador;
        calcularImpuestoCirculacion();
        if (cilindraje > 2499) {
            this.cuotaMesGaraje *= 1.2; // Incremento del 20% por cilindraje alto
        }
    }

    @Override
    protected void calcularImpuestoCirculacion() {
        // Implementación para calcular el impuesto de circulación de un auto
        this.impuestoCirculacion = precio * 0.07; // Ejemplo: 7% del precio como impuesto

        this.impuestoCirculacion = this.precio * 0.02;
        if (tieneRadio) {
            this.impuestoCirculacion += this.precio * 0.01; // Incremento del 1% si tiene radio
        }
        if (tieneNavegador) 
            this.impuestoCirculacion += this.precio * 0.02; // Incremento del 2% si tiene navegador
        }
        public boolean isTieneAireAcondicionado() {
            return tieneAireAcondicionado;
        }
    
        public boolean isEsDeLujo() {
            return esDeLujo;
    }
}