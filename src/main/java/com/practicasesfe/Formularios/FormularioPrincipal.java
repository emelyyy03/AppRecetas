package com.practicasesfe.Formularios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import com.formdev.flatlaf.FlatDarkLaf;


public class FormularioPrincipal {

    private JPanel rootPanel;
    private JPanel contentPanel;
    private JMenu menuSeleccionado = null;


    public FormularioPrincipal() {
        // Configurar CardLayout manualmente
        contentPanel.setLayout(new CardLayout());

        // Agregar formularios
        contentPanel.add(new PerfilForm(), "perfil");
        contentPanel.add(new UsuariosForm(), "usuarios");
        contentPanel.add(new EstudiantesForm(), "estudiantes");
        contentPanel.add(new DocentesForm(), "docentes");
        contentPanel.add(new MateriaForm(), "materia");
        contentPanel.add(new HorarioForm(), "horarios");
        contentPanel.add(new EstudianteHorarioForm(), "estudianteHorario");
        contentPanel.add(new PruebaCicloForm().getRootPanel(), "ciclos");
        contentPanel.add(new AulasForm(), "aulas");
        contentPanel.add(new ProgramasForm(), "programas");
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }

    public void agregarMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(245, 245, 245)); // Fondo oscuro
        menuBar.setOpaque(true);

        // Menús
        JMenu menuPerfil = new JMenu("Mi Perfil");
        JMenu menuUsuarios = new JMenu("Usuarios");
        JMenu menuEstudiantes = new JMenu("Estudiantes");
        JMenu menuDocentes = new JMenu("Docentes");
        JMenu menuMateria = new JMenu("Materia");
        JMenu menuHorarios = new JMenu("Horario");
        JMenu menuEstudianteHorario = new JMenu("Estudiante Horario");
        JMenu menuCiclos = new JMenu("Ciclos");
        JMenu menuAulas = new JMenu("Aulas");
        JMenu menuProgramas = new JMenu("Programas Académicos");


        // Agregar menús
        menuBar.add(menuUsuarios);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuDocentes);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuMateria);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuEstudiantes);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuEstudianteHorario);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuHorarios);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuCiclos);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuAulas);
        menuBar.add(Box.createHorizontalStrut(5)); // separador
        menuBar.add(menuProgramas);
        menuBar.add(Box.createHorizontalGlue()); //empuja el boton al final
        menuBar.add(menuPerfil);


        // Estilos
        Color fondoClaro = new Color(255, 250, 250);
        Color fondoHoverClaro = new Color(230, 230, 250); // más elegante
        Color textoOscuro = Color.BLACK;
        Font fuente = new Font("Times New Roman", Font.PLAIN, 13);

        JMenu[] menus = {
                menuUsuarios, menuEstudiantes, menuDocentes, menuMateria,
                menuHorarios, menuEstudianteHorario, menuCiclos, menuAulas, menuProgramas
        };

        for (JMenu menu : menus) {
            menu.setFont(fuente);
            menu.setForeground(textoOscuro);
            menu.setOpaque(true);
            menu.setBackground(fondoClaro);
            menu.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                    BorderFactory.createEmptyBorder(5, 15, 5, 15)
            ));

            menu.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    menu.setBackground(fondoHoverClaro);
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    menu.setBackground(fondoClaro);
                }
            });
        }

        // Estilo para "Mi Perfil"
        menuPerfil.setFont(new Font("Times New Roman", Font.BOLD, 13));
        menuPerfil.setForeground(Color.BLACK);
        menuPerfil.setOpaque(true);
        menuPerfil.setBackground(new Color(255, 250, 250));
        menuPerfil.setBorder(BorderFactory.createEmptyBorder(6, 18, 6, 18));

        menuPerfil.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                menuPerfil.setBackground(new Color(230, 230, 250));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                menuPerfil.setBackground(new Color(255, 250, 250));
            }
        });


        // Asociar eventos
        menuPerfil.addMouseListener(menuClick("perfil"));
        menuUsuarios.addMouseListener(menuClick("usuarios"));
        menuEstudiantes.addMouseListener(menuClick("estudiantes"));
        menuDocentes.addMouseListener(menuClick("docentes"));
        menuMateria.addMouseListener(menuClick("materia"));
        menuHorarios.addMouseListener(menuClick("horario"));
        menuEstudianteHorario.addMouseListener(menuClick("estudianteHorario"));
        menuCiclos.addMouseListener(menuClick("ciclos"));
        menuAulas.addMouseListener(menuClick("aulas"));
        menuProgramas.addMouseListener(menuClick("programas"));

        frame.setJMenuBar(menuBar);
    }

    private MouseAdapter menuClick(String name) {
        return new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                CardLayout cl = (CardLayout) contentPanel.getLayout();
                cl.show(contentPanel, name);
            }
        };
    }


    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.FlatDarkLaf()); // o FlatLightLaf
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        JFrame frame = new JFrame("Pantalla Principal");
        FormularioPrincipal mainForm = new FormularioPrincipal();

        frame.setContentPane(mainForm.getRootPanel());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // Agregar menú dinámicamente
        mainForm.agregarMenu(frame);

        frame.setVisible(true);
    }

}
