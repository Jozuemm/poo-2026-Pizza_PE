public class Cocina {
    // Antes Cocina solo tenia un main vacio, entonces agregué atributos y metodos
    private String chef;
    private boolean abierta;

    // En el diagrama habia numeros de orden
    // Sooo usé Orden[] para guardar tambien la pizza y los datos de cada orden
    private Orden[] ordenesPendientes;

    public Cocina(String chef) {
        this.chef = chef;
        this.abierta = false;
        this.ordenesPendientes = new Orden[5];
    }

    public void abrirCocina() {
        abierta = true;
        System.out.println("Cocina abierta. Chef: " + chef);
    }

    public void cerrarCocina() {
        abierta = false;
        System.out.println("Cocina cerrada.");
    }

    public void registrarOrdenPendiente(Orden orden) {
        if (!abierta) {
            System.out.println("La cocina esta cerrada.");
            return;
        }

        if (orden == null || orden.getPizza() == null) {
            System.out.println("Debes enviar una orden con pizza.");
            return;
        }

        if (!orden.ordenPendiente()) {
            System.out.println("Esa orden ya fue preparada.");
            return;
        }

        for (int i = 0; i < ordenesPendientes.length; i++) {
            if (ordenesPendientes[i] != null
                    && ordenesPendientes[i].getNumeroOrden() == orden.getNumeroOrden()) {
                System.out.println("Ese numero de orden ya esta registrado.");
                return;
            }
        }

        for (int i = 0; i < ordenesPendientes.length; i++) {
            if (ordenesPendientes[i] == null) {
                ordenesPendientes[i] = orden;
                System.out.println("Orden #" + orden.getNumeroOrden() + " registrada.");
                return;
            }
        }

        System.out.println("La cocina esta llena.");
    }

    public void cocinar() {
        if (!abierta) {
            System.out.println("La cocina esta cerrada.");
            return;
        }

        boolean hayOrdenes = false;

        for (int i = 0; i < ordenesPendientes.length; i++) {
            if (ordenesPendientes[i] != null) {
                Orden orden = ordenesPendientes[i];

                System.out.println("\nPreparando orden #" + orden.getNumeroOrden());
                System.out.println(orden.getPizza());

                orden.marcarPreparada();
                ordenesPendientes[i] = null;

                System.out.println("Pizza lista.");
                hayOrdenes = true;
            }
        }

        if (!hayOrdenes) {
            System.out.println("No hay ordenes pendientes.");
        }
    }

    public static void main(String[] args) {
        // Como el main estaba vacio entonces no pasaba nada
        // Agregué dos ordenes ya dadas para probar el programa
        Cocina cocina = new Cocina("Josue");
        cocina.abrirCocina();

        Pizza pizza1 = new Pizza(Masa.GRUESA, Tamano.GRANDE);
        Pizza pizza2 = new Pizza(
                Tamano.MEDIANA, Ingredientes.PEPERONI, Salsa.PICANTE
        );

        Orden orden1 = new Orden("Monks", 1);
        Orden orden2 = new Orden("Marks", 2, 67676767);

        orden1.agregarPizza(pizza1);
        orden2.agregarPizza(pizza2);

        orden1.cobrar();
        orden2.cobrar();

        cocina.registrarOrdenPendiente(orden1);
        cocina.registrarOrdenPendiente(orden2);

        cocina.cocinar();

        orden1.mostrarOrden();
        orden2.mostrarOrden();

        cocina.cerrarCocina();
    }
}