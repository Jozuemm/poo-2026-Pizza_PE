public class Pizza {
    private Masa masa;
    private Tamano tamano;
    private Salsa salsa;
    private Ingredientes ingredientes;

    public Pizza(Masa masa, Tamano tamano){
        this.masa = masa;
        this.tamano = tamano;

    }

    public Pizza(Masa masa, Tamano tamano, Ingredientes ingredientes){
        this.masa = masa;
        this.tamano = tamano;
        this.ingredientes = ingredientes;

    }

    public Pizza(Tamano tamano, Ingredientes ingredientes, Salsa salsa){
        this.tamano = tamano;
        this.ingredientes = ingredientes;
        this.salsa = salsa;

    }


}