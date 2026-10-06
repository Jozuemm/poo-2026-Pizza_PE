public class Orden {
    private String cliente;
    private int numeroOrden;
    private double precio;
    private boolean pagado;
    private int nit;

    // La orden no tenia nada, entonces agregue una pizza para saber que se pidio
    private Pizza pizza;

    //Este dato es porque una orden pagada todavia puede estar sin preparar
    private boolean preparada;

    public Orden(int numeroOrden) {
        // Se llama al constructor completo para no repetir las mismas asignaciones
        this("Consumidor final", numeroOrden, 0);
    }

    public Orden(String cliente, int numeroOrden) {
        this(cliente, numeroOrden, 0);
    }

    public Orden(String cliente, int numeroOrden, int nit) {
        this.cliente = cliente;
        this.numeroOrden = numeroOrden;
        this.nit = nit;
        this.precio = 0;
        this.pagado = false;
        this.preparada = false;
    }

    public void agregarPizza(Pizza pizza) {
        if (pizza == null || pagado || preparada) {
            return;
        }

        this.pizza = pizza;

        // Antes el precio no se actualizaba, ahora se obtiene de la pizza
        this.precio = pizza.calcularPrecio();
    }

    public void cobrar() {
        if (pizza == null) {
            System.out.println("Primero debes agregar una pizza.");
            return;
        }

        if (pagado) {
            System.out.println("La orden ya esta pagada.");
            return;
        }

        pagado = true;
        System.out.println("Pago registrado: Q" + precio);
    }

    public boolean ordenPendiente() {
        // Antes este no tenia return
        // Ahora devuelve true si la orden todavia no esta preparada

        return !preparada;
    }

    public void marcarPreparada() {
        if (pizza != null) {
            preparada = true;
        }
    }

    // Como los atributos son privados se usan estos metodos para consultarlos desde Cocina
    public int getNumeroOrden() {
        return numeroOrden;
    }

    public Pizza getPizza() {
        return pizza;
    }

    public void mostrarOrden() {
        System.out.println("\nOrden #" + numeroOrden);
        System.out.println("Cliente: " + cliente);

        if (nit == 0) {
            System.out.println("NIT: CF");
        } else {
            System.out.println("NIT: " + nit);
        }

        if (pizza == null) {
            System.out.println("La orden no tiene pizza.");
        } else {
            System.out.println(pizza);
        }

        System.out.println("Total: Q" + precio);
        System.out.println("Pagada: " + pagado);
        System.out.println("Preparada: " + preparada);
    }
}