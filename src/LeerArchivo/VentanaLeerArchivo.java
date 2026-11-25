package LeerArchivo;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class VentanaLeerArchivo extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel lblRuta;
    private JTextField campoRuta;
    private JButton btnBuscar, btnLeer, btnLimpiar;
    private JTextArea areaTexto;
    private JScrollPane scrollTexto;

    public VentanaLeerArchivo() {
        inicio();
        setTitle("Ejercicio 6.8 - Lectura de Archivos de Texto");
        setSize(520, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        lblRuta = new JLabel("Ruta del Archivo (.txt):");
        lblRuta.setBounds(20, 15, 150, 23);

        campoRuta = new JTextField();
        campoRuta.setBounds(160, 15, 210, 23);

        btnBuscar = new JButton("Buscar...");
        btnBuscar.setBounds(380, 15, 100, 23);
        btnBuscar.addActionListener(this);

        btnLeer = new JButton("Leer Archivo");
        btnLeer.setBounds(20, 50, 220, 28);
        btnLeer.addActionListener(this);

        btnLimpiar = new JButton("Limpiar Visor");
        btnLimpiar.setBounds(260, 50, 220, 28);
        btnLimpiar.addActionListener(this);

        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        scrollTexto = new JScrollPane(areaTexto);
        scrollTexto.setBounds(20, 90, 460, 270);

        contenedor.add(lblRuta);
        contenedor.add(campoRuta);
        contenedor.add(btnBuscar);
        contenedor.add(btnLeer);
        contenedor.add(btnLimpiar);
        contenedor.add(scrollTexto);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnBuscar) {
            JFileChooser selector = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de Texto (*.txt)", "txt");
            selector.setFileFilter(filtro);

            int resultado = selector.showOpenDialog(this);
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File archivoSeleccionado = selector.getSelectedFile();
                campoRuta.setText(archivoSeleccionado.getAbsolutePath());
            }
        }

        if (evento.getSource() == btnLeer) {
            String ruta = campoRuta.getText().trim();
            if (ruta.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor ingrese o busque la ruta de un archivo .txt",
                    "Ruta Vacía", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                String texto = LeerArchivo.leerContenido(ruta);
                areaTexto.setText(texto);
                JOptionPane.showMessageDialog(this,
                    "Archivo leído exitosamente.",
                    "Lectura Correcta", JOptionPane.INFORMATION_MESSAGE);

            } catch (FileNotFoundException e) {
                areaTexto.setText("");
                JOptionPane.showMessageDialog(this,
                    "Excepción FileNotFoundException: El archivo especificado no existe o no fue encontrado.\nRuta: " + ruta,
                    "Archivo No Encontrado", JOptionPane.ERROR_MESSAGE);
            } catch (IOException e) {
                areaTexto.setText("");
                JOptionPane.showMessageDialog(this,
                    "Excepción IOException: Ocurrió un error al leer el archivo de texto.",
                    "Error de E/S", JOptionPane.ERROR_MESSAGE);
            }
        }

        if (evento.getSource() == btnLimpiar) {
            campoRuta.setText("");
            areaTexto.setText("");
        }
    }
}
