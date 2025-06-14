package com.practicasesfe.Formularios;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class FormularioPrincipal {
    private JPanel rootPanel;
    private JPanel contentPanel;

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
        menuBar.add(menuPerfil);
        menuBar.add(menuUsuarios);
        menuBar.add(menuEstudiantes);
        menuBar.add(menuDocentes);
        menuBar.add(menuMateria);
        menuBar.add(menuHorarios);
        menuBar.add(menuEstudianteHorario);
        menuBar.add(menuCiclos);
        menuBar.add(menuAulas);
        menuBar.add(menuProgramas);

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
