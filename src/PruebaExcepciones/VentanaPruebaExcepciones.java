package PruebaExcepciones;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class VentanaPruebaExcepciones extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel titulo;
    private JButton btnPrueba1, btnPrueba2, btnLimpiar;
    private JTextArea areaSalida;
    private JScrollPane scroll;

    public VentanaPruebaExcepciones() {
        inicio();
        setTitle("Ejercicio 6.4 - Prueba de Excepciones");
        setSize(420, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        titulo = new JLabel("Manejo de Excepciones (try - catch - finally):");
        titulo.setBounds(20, 15, 360, 23);

        btnPrueba1 = new JButton("Prueba 1 (Div por Cero)");
        btnPrueba1.setBounds(20, 45, 180, 28);
        btnPrueba1.addActionListener(this);

        btnPrueba2 = new JButton("Prueba 2 (NullPointer)");
        btnPrueba2.setBounds(210, 45, 180, 28);
        btnPrueba2.addActionListener(this);

        areaSalida = new JTextArea();
        areaSalida.setEditable(false);
        scroll = new JScrollPane(areaSalida);
        scroll.setBounds(20, 85, 370, 180);

        btnLimpiar = new JButton("Limpiar Salida");
        btnLimpiar.setBounds(140, 275, 130, 28);
        btnLimpiar.addActionListener(this);

        contenedor.add(titulo);
        contenedor.add(btnPrueba1);
        contenedor.add(btnPrueba2);
        contenedor.add(scroll);
        contenedor.add(btnLimpiar);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnPrueba1) {
            String log = PruebaExcepciones.ejecutarPrueba1();
            areaSalida.append("=== EJECUCIÓN PRUEBA 1 ===\n" + log + "\n");
            JOptionPane.showMessageDialog(this, "Prueba 1 ejecutada con éxito.\nRevisa la consola visual.", "Prueba 1", JOptionPane.INFORMATION_MESSAGE);
        }

        if (evento.getSource() == btnPrueba2) {
            String log = PruebaExcepciones.ejecutarPrueba2();
            areaSalida.append("=== EJECUCIÓN PRUEBA 2 ===\n" + log + "\n");
            JOptionPane.showMessageDialog(this, "Prueba 2 ejecutada con éxito.\nRevisa la consola visual.", "Prueba 2", JOptionPane.WARNING_MESSAGE);
        }

        if (evento.getSource() == btnLimpiar) {
            areaSalida.setText("");
        }
    }
}
