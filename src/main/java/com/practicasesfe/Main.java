package com.practicasesfe;

import com.practicasesfe.Formularios.FormularioPrincipal;
import com.practicasesfe.Formularios.PrincipalForm;

import javax.swing.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(() ->{
            PrincipalForm principalForm = new PrincipalForm();
            principalForm.setVisible(true);
        });*/


        // Recomendado: correr en el hilo de eventos de Swing
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Pantalla Principal");

            // Crear instancia del formulario generado
            FormularioPrincipal mainForm = new FormularioPrincipal();

            // Establecer el contenido de la ventana principal
            frame.setContentPane(mainForm.getRootPanel());

            // Agregar la barra de menú desde código
            mainForm.agregarMenu(frame);

            // Configurar propiedades de la ventana
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.setLocationRelativeTo(null); // Centrar
            frame.setVisible(true);
        });
    }

        }

