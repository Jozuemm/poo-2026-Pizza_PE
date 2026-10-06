public class Orden {
    private String cliente;
    private int numeroOrden;
    private double precio;
    private boolean pagado;
    private int nit;
    private Pizza pizza;

    public Orden(int numeroOrden){
        this.numeroOrden = numeroOrden;
        this.pagado = false;

    }

    public Orden(String cliente, int numeroOrden){
        this.cliente = cliente;
        this.numeroOrden = numeroOrden;
        this.pagado = false;
    }

    public Orden(String cliente, int numeroOrden, int nit){
        this.cliente = cliente;
        this.numeroOrden = numeroOrden;
        this.nit = nit;
        this.pagado = false;
    }

    private boolean Cobrar (boolean pagado){
        this.pagado = pagado;
        return pagado;
    }

    public boolean ordenPendiente(int numeroOrden){

    }

}