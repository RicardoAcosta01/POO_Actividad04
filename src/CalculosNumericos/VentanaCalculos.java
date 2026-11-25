package CalculosNumericos;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaCalculos extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel lblValor, lblResultado;
    private JTextField campoValor;
    private JButton btnLogaritmo, btnRaiz, btnLimpiar;

    public VentanaCalculos() {
        inicio();
        setTitle("Ejercicio 6.6 - Cálculos Numéricos");
        setSize(360, 240);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        lblValor = new JLabel("Valor Numérico:");
        lblValor.setBounds(20, 20, 120, 23);
        campoValor = new JTextField();
        campoValor.setBounds(140, 20, 180, 23);

        btnLogaritmo = new JButton("Logaritmo (ln)");
        btnLogaritmo.setBounds(20, 65, 145, 28);
        btnLogaritmo.addActionListener(this);

        btnRaiz = new JButton("Raíz Cuadrada (√)");
        btnRaiz.setBounds(175, 65, 145, 28);
        btnRaiz.addActionListener(this);

        btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(100, 105, 140, 28);
        btnLimpiar.addActionListener(this);

        lblResultado = new JLabel("Resultado: -");
        lblResultado.setBounds(20, 150, 300, 25);

        contenedor.add(lblValor);
        contenedor.add(campoValor);
        contenedor.add(btnLogaritmo);
        contenedor.add(btnRaiz);
        contenedor.add(btnLimpiar);
        contenedor.add(lblResultado);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        String texto = campoValor.getText().trim();

        if (evento.getSource() == btnLogaritmo || evento.getSource() == btnRaiz) {
            if (texto.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese un valor numérico.",
                    "Campo Vacío", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                double val = Double.parseDouble(texto);

                if (evento.getSource() == btnLogaritmo) {
                    double res = CalculosNumericos.calcularLogaritmoNeperiano(val);
                    lblResultado.setText(String.format("ln(%.2f) = %.4f", val, res));
                } else if (evento.getSource() == btnRaiz) {
                    double res = CalculosNumericos.calcularRaizCuadrada(val);
                    lblResultado.setText(String.format("√(%.2f) = %.4f", val, res));
                }

            } catch (NumberFormatException e) {
                lblResultado.setText("Resultado: Error de entrada");
                JOptionPane.showMessageDialog(this,
                    "Excepción InputMismatchException / NumberFormat: El valor ingresado debe ser un número real.",
                    "Error de Formato", JOptionPane.ERROR_MESSAGE);
            } catch (ArithmeticException e) {
                lblResultado.setText("Resultado: Excepción Aritmética");
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Excepción Aritmética", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (evento.getSource() == btnLimpiar) {
            campoValor.setText("");
            lblResultado.setText("Resultado: -");
        }
    }
}
