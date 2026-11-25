package Vendedor;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaVendedor extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel lblNombre, lblApellidos, lblEdad, lblResultado;
    private JTextField campoNombre, campoApellidos, campoEdad;
    private JButton btnVerificar, btnLimpiar;

    public VentanaVendedor() {
        inicio();
        setTitle("Ejercicio 6.5 - Registro de Vendedor");
        setSize(320, 280);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 23);
        campoNombre = new JTextField();
        campoNombre.setBounds(110, 20, 170, 23);

        lblApellidos = new JLabel("Apellidos:");
        lblApellidos.setBounds(20, 55, 100, 23);
        campoApellidos = new JTextField();
        campoApellidos.setBounds(110, 55, 170, 23);

        lblEdad = new JLabel("Edad:");
        lblEdad.setBounds(20, 90, 100, 23);
        campoEdad = new JTextField();
        campoEdad.setBounds(110, 90, 170, 23);

        btnVerificar = new JButton("Verificar");
        btnVerificar.setBounds(20, 130, 120, 28);
        btnVerificar.addActionListener(this);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(160, 130, 120, 28);
        btnLimpiar.addActionListener(this);

        lblResultado = new JLabel("Estado: Esperando datos...");
        lblResultado.setBounds(20, 180, 270, 30);

        contenedor.add(lblNombre);
        contenedor.add(campoNombre);
        contenedor.add(lblApellidos);
        contenedor.add(campoApellidos);
        contenedor.add(lblEdad);
        contenedor.add(campoEdad);
        contenedor.add(btnVerificar);
        contenedor.add(btnLimpiar);
        contenedor.add(lblResultado);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnVerificar) {
            String nom = campoNombre.getText().trim();
            String ape = campoApellidos.getText().trim();
            String edadTexto = campoEdad.getText().trim();

            if (nom.isEmpty() || ape.isEmpty() || edadTexto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios.",
                    "Campos Vacíos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                int edadInt = Integer.parseInt(edadTexto);
                Vendedor vendedor = new Vendedor(nom, ape);
                vendedor.verificarEdad(edadInt);

                lblResultado.setText("<html><font color='green'>Vendedor registrado con éxito. Edad: " + vendedor.getEdad() + " años.</font></html>");
                JOptionPane.showMessageDialog(this,
                    "Vendedor " + nom + " " + ape + " registrado correctamente.",
                    "Registro Exitoso", JOptionPane.INFORMATION_MESSAGE);

            } catch (NumberFormatException e) {
                lblResultado.setText("<html><font color='red'>Error: Edad no válida.</font></html>");
                JOptionPane.showMessageDialog(this,
                    "La edad debe ser un número entero válido.",
                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException e) {
                lblResultado.setText("<html><font color='red'>Error: " + e.getMessage() + "</font></html>");
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Excepción de Argumento Ilegal", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (evento.getSource() == btnLimpiar) {
            campoNombre.setText("");
            campoApellidos.setText("");
            campoEdad.setText("");
            lblResultado.setText("Estado: Esperando datos...");
        }
    }
}
