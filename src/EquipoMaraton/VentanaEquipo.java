package EquipoMaraton;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VentanaEquipo extends JFrame implements ActionListener {
    private Container contenedor;
    private JLabel lblNomEquipo, lblUniversidad, lblLenguaje, lblNomProg, lblApeProg, lblIntegrantes;
    private JTextField campoNomEquipo, campoUniversidad, campoLenguaje, campoNomProg, campoApeProg;
    private JButton btnCrearEquipo, btnAnadirProg;
    private JList<String> listaGUI;
    private DefaultListModel<String> modeloLista;
    private JScrollPane scrollLista;

    private EquipoMaratonProgramacion equipo;

    public VentanaEquipo() {
        inicio();
        setTitle("Ejercicio 6.7 - Maratón de Programación");
        setSize(420, 480);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
    }

    private void inicio() {
        contenedor = getContentPane();
        contenedor.setLayout(null);

        // Datos del equipo
        lblNomEquipo = new JLabel("Nombre Equipo:");
        lblNomEquipo.setBounds(20, 15, 120, 23);
        campoNomEquipo = new JTextField();
        campoNomEquipo.setBounds(140, 15, 240, 23);

        lblUniversidad = new JLabel("Universidad:");
        lblUniversidad.setBounds(20, 45, 120, 23);
        campoUniversidad = new JTextField();
        campoUniversidad.setBounds(140, 45, 240, 23);

        lblLenguaje = new JLabel("Lenguaje:");
        lblLenguaje.setBounds(20, 75, 120, 23);
        campoLenguaje = new JTextField();
        campoLenguaje.setBounds(140, 75, 240, 23);

        btnCrearEquipo = new JButton("1. Crear / Inicializar Equipo");
        btnCrearEquipo.setBounds(20, 110, 360, 28);
        btnCrearEquipo.addActionListener(this);

        // Integrantes
        lblNomProg = new JLabel("Nombre Prog.:");
        lblNomProg.setBounds(20, 155, 120, 23);
        campoNomProg = new JTextField();
        campoNomProg.setBounds(140, 155, 240, 23);

        lblApeProg = new JLabel("Apellidos Prog.:");
        lblApeProg.setBounds(20, 185, 120, 23);
        campoApeProg = new JTextField();
        campoApeProg.setBounds(140, 185, 240, 23);

        btnAnadirProg = new JButton("2. Añadir Integrante al Equipo");
        btnAnadirProg.setBounds(20, 220, 360, 28);
        btnAnadirProg.addActionListener(this);

        // Lista de integrantes
        lblIntegrantes = new JLabel("Integrantes del Equipo (Máx 3):");
        lblIntegrantes.setBounds(20, 260, 360, 23);

        modeloLista = new DefaultListModel<>();
        listaGUI = new JList<>(modeloLista);
        scrollLista = new JScrollPane(listaGUI);
        scrollLista.setBounds(20, 290, 360, 120);

        contenedor.add(lblNomEquipo);
        contenedor.add(campoNomEquipo);
        contenedor.add(lblUniversidad);
        contenedor.add(campoUniversidad);
        contenedor.add(lblLenguaje);
        contenedor.add(campoLenguaje);
        contenedor.add(btnCrearEquipo);
        contenedor.add(lblNomProg);
        contenedor.add(campoNomProg);
        contenedor.add(lblApeProg);
        contenedor.add(campoApeProg);
        contenedor.add(btnAnadirProg);
        contenedor.add(lblIntegrantes);
        contenedor.add(scrollLista);
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == btnCrearEquipo) {
            String nomEq = campoNomEquipo.getText().trim();
            String uni = campoUniversidad.getText().trim();
            String leng = campoLenguaje.getText().trim();

            if (nomEq.isEmpty() || uni.isEmpty() || leng.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "Por favor complete todos los datos del equipo.",
                    "Campos Incompletos", JOptionPane.WARNING_MESSAGE);
                return;
            }

            equipo = new EquipoMaratonProgramacion(nomEq, uni, leng);
            modeloLista.clear();
            JOptionPane.showMessageDialog(this,
                "Equipo '" + nomEq + "' creado. Ahora agregue los 3 integrantes.",
                "Equipo Creado", JOptionPane.INFORMATION_MESSAGE);
        }

        if (evento.getSource() == btnAnadirProg) {
            if (equipo == null) {
                JOptionPane.showMessageDialog(this,
                    "Debe crear e inicializar el equipo primero.",
                    "Equipo No Creado", JOptionPane.WARNING_MESSAGE);
                return;
            }

            String nomP = campoNomProg.getText().trim();
            String apeP = campoApeProg.getText().trim();

            try {
                EquipoMaratonProgramacion.validarCampo(nomP);
                EquipoMaratonProgramacion.validarCampo(apeP);

                Programador p = new Programador(nomP, apeP);
                equipo.anadirProgramador(p);

                modeloLista.addElement((modeloLista.size() + 1) + ". " + p.toString());
                campoNomProg.setText("");
                campoApeProg.setText("");

                JOptionPane.showMessageDialog(this,
                    "Programador " + p + " añadido correctamente.",
                    "Éxito", JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Excepción en Validación", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
