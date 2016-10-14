package visual;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import personal.Empleado;

public class VentanaLogin extends VentanaBase {

    private Empleado empleadoLogeado = null;

    public VentanaLogin() {
        /*Crea la ventana con el titulo Login y con la
         *accion de terminar el programa al cerrar laventana*/
        super("Login", JFrame.EXIT_ON_CLOSE, new Dimension(400, 250));
        this.setResizable(false);
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();


        JTextArea datos = new JTextArea("Legajo:\n\n\nApellido y nombre:\n\n\nSector:\n\n\n");
        datos.setEditable(false);
        cp.add(datos, BorderLayout.CENTER);

        //Se crea un panel donde estaran todos los elementos que permiten buscar un empleado
        JPanel panelIngreso = new JPanel();
        panelIngreso.setLayout(new BorderLayout());
        //Se agrega un JLabel que indica lo que hay que ingresar
        panelIngreso.add(new JLabel("Ingrese su legajo:  "), BorderLayout.WEST);
        //Se agrega un JTextField donde se ingresara el legajo
        JTextField legajo = new JTextField();
        panelIngreso.add(legajo, BorderLayout.CENTER);
        //Se agrega un boton que permite buscar el empleado que corresponda al legajo ingresado
        JButton buscar = new JButton("Buscar");
        buscar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String ingresado = legajo.getText();
                String leg = "LEG";
                leg += ingresado;
                Empleado empleado = Controlador.buscarEmpleado(leg);
                if (empleado == null)
                    JOptionPane.showMessageDialog(VentanaLogin.this,
                                                  "No existe un empleado que coincida con dicho legajo");
                else {
                    VentanaLogin.this.empleadoLogeado = empleado;
                    datos.setText("Legajo: " + empleado.getLegajo() + "\n\n\nApellido y nombre: " + empleado.getNya() +
                                  "\n\n\nSector: " + empleado.getSector() + "\n\n\n");
                }
            }
        });
        panelIngreso.add(buscar, BorderLayout.EAST);
        //Se agrega el panel al ContentPane de la ventana
        cp.add(panelIngreso, BorderLayout.NORTH);

        //Se agrega el boton que permite ingresar al sistema
        JButton ingresar = new JButton("Ingresar");

        cp.add(ingresar, BorderLayout.SOUTH);
    }
}
