import javax.swing.*;
import java.awt.*;

public class VistaPizzeria extends JFrame {
    private Cocina cocina;
    private int numeroOrden = 1;
    private int pendientes = 0;

    private JTextField campoCliente;
    private JComboBox<Tamano> listaTamano;
    private JComboBox<Masa> listaMasa;
    private JComboBox<Ingredientes> listaIngredientes;
    private JTextArea resultado;

    public VistaPizzeria() {
        cocina = new Cocina("Jozuem");
        cocina.abrirCocina();

        setTitle("Jack Pizza pero de Jozuem");
        setSize(500, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10,10));
    

     JLabel titulo = new JLabel("Jack Pizza Chef", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setForeground(new Color(170, 55, 39));
        add(titulo, BorderLayout.NORTH);

        JPanel formulario = new JPanel(new GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        campoCliente = new JTextField();
        listaTamano = new JComboBox<>(Tamano.values());
        listaMasa = new JComboBox<>(Masa.values());
        listaIngredientes = new JComboBox<>(Ingredientes.values());

        formulario.add(new JLabel("Cliente:"));
        formulario.add(campoCliente);

        formulario.add(new JLabel("Tamano:"));
        formulario.add(listaTamano);

        formulario.add(new JLabel("Masa:"));
        formulario.add(listaMasa);

        formulario.add(new JLabel("Ingrediente:"));
        formulario.add(listaIngredientes);

        JButton botonRegistrar = new JButton("Registrar y cobrar");
        JButton botonCocinar = new JButton("Cocinar pendientes");

        formulario.add(botonRegistrar);
        formulario.add(botonCocinar);

        add(formulario, BorderLayout.CENTER);

        resultado = new JTextArea(9, 30);
        resultado.setEditable(false);
        resultado.setLineWrap(true);
        resultado.setWrapStyleWord(true);
        resultado.setText("Bienvenido. Puedes registrar hasta 5 ordenes pendientes.\n");

        add(new JScrollPane(resultado), BorderLayout.SOUTH);

        botonRegistrar.addActionListener(e -> registrarOrden());
        botonCocinar.addActionListener(e -> cocinarOrdenes());
    }

    private void registrarOrden() {
        String cliente = campoCliente.getText().trim();

        if (cliente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escribe el nombre del cliente.");
            return;
        }

        if (pendientes >= 5) {
            JOptionPane.showMessageDialog(this, "Primero cocina las ordenes pendientes.");
            return;
        }

        Tamano tamano = (Tamano) listaTamano.getSelectedItem();
        Masa masa = (Masa) listaMasa.getSelectedItem();
        Ingredientes ingrediente = (Ingredientes) listaIngredientes.getSelectedItem();

        Pizza pizza = new Pizza(masa, tamano, ingrediente);
        Orden orden = new Orden(cliente, numeroOrden);

        orden.agregarPizza(pizza);
        orden.cobrar();
        cocina.registrarOrdenPendiente(orden);

        resultado.append("\nOrden #" + numeroOrden + " - " + cliente + "\n");
        resultado.append(pizza + "\n");
        resultado.append("Pagado: Q" + pizza.calcularPrecio() + "\n");

        numeroOrden++;
        pendientes++;

        campoCliente.setText("");
    }

    private void cocinarOrdenes() {
        if (pendientes == 0) {
            JOptionPane.showMessageDialog(this, "No hay ordenes pendientes.");
            return;
        }

        cocina.cocinar();
        resultado.append("\nListo. Se prepararon " + pendientes + " pizzas.\n");
        pendientes = 0;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VistaPizzeria ventana = new VistaPizzeria();
            ventana.setVisible(true);
        });
}
}