public class Pizza {
    private Masa masa;
    private Tamano tamano;
    private Salsa salsa;
    private Ingredientes ingredientes;

    public Pizza(Masa masa, Tamano tamano) {
        // Aca el constructor tambien estaba vacio y no guardaba los datos recibidos
        this.masa = masa;
        this.tamano = tamano;

        // Como este constructor no recibe salsa ni ingrediente les di valores iniciales
        this.salsa = Salsa.NORMAL;
        this.ingredientes = Ingredientes.QUESO;
    }

    public Pizza(Masa masa, Tamano tamano, Ingredientes ingredientes) {
        this.masa = masa;
        this.tamano = tamano;
        this.salsa = Salsa.NORMAL;
        this.ingredientes = ingredientes;
    }

    public Pizza(Tamano tamano, Ingredientes ingredientes, Salsa salsa) {
        //Como no se recibe una masa, como en dominos se deja la masa fina por defecto
        this.masa = Masa.FINA;
        this.tamano = tamano;
        this.ingredientes = ingredientes;
        this.salsa = salsa;
    }

    public double calcularPrecio() {
        // Puse precios para los tamaños
        switch (tamano) {
            case PERSONAL:
                return 30.0;
            case MEDIANA:
                return 50.0;
            case GRANDE:
                return 70.0;
            default:
                return 0.0;
        }
    }

    public String toString() {
        return "Pizza " + tamano
                + ", masa " + masa
                + ", salsa " + salsa
                + ", ingrediente " + ingredientes;
    }
}