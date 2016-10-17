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

    public VentanaLogin(Controlador control) {
        /*Crea la ventana con el titulo Login y con la
         *accion de terminar el programa al cerrar laventana*/
        super(control, "Login", JFrame.EXIT_ON_CLOSE, new Dimension(400, 250));
        this.setResizable(false);
    }

    @Override
    protected void IniciarComponentes() {
        Container cp = this.getContentPane();

        //Se crea un JTextArea donde se mostraran los datos del empleado encontrado. Empieza vacio
        JTextArea datos = new JTextArea("Legajo:\n\n\nApellido y nombre:\n\n\nSector:\n\n\n");
        //Se impide que se pueda modificar manualmente los datos
        datos.setEditable(false);
        //Se añade al ContentPane de la ventana
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
            /* Se busca el empleado que corresponda al legajo ingresado
             * Si se encuentra se muestran sus datos por pantalla y se guarda la referencia en
             * el atributo empleadoLogeado de la VentanaLogin
             * Si no se encuentra se avisa del error por pantalla*/
            public void actionPerformed(ActionEvent actionEvent) {
                String ingresado = legajo.getText();
                String leg = "LEG";
                leg += ingresado;
                Empleado empleado = VentanaLogin.this.control.buscarEmpleado(leg);
                if (empleado == null) {
                    VentanaLogin.this.empleadoLogeado = null;
                    legajo.setText("");
                    datos.setText("Legajo:\n\n\nApellido y nombre:\n\n\nSector:\n\n\n");
                    JOptionPane.showMessageDialog(VentanaLogin.this,
                                                  "No existe un empleado que coincida con dicho legajo");
                } else {
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
        JButton ingresar = new JButton("Ingresar al sistema");
        //TODO agregar la accion correspondiente al empleado que intenta ingresar al sistema
        ingresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String sector = VentanaLogin.this.empleadoLogeado.getSector().toUpperCase();

                if (sector.compareTo(Controlador.VENTAS.toUpperCase()) == 0) {
                    control.setEmpeladoActual(VentanaLogin.this.empleadoLogeado);
                    new VentanaVentas(VentanaLogin.this.control);
                    VentanaLogin.this.dispose();
                } else if (sector.compareTo(Controlador.PRODUCCION.toUpperCase()) == 0) {
                    control.setEmpeladoActual(VentanaLogin.this.empleadoLogeado);
                    new VentanaProduccion(VentanaLogin.this.control);
                    VentanaLogin.this.dispose();
                } else
                    JOptionPane.showMessageDialog(VentanaLogin.this,
                                                  "No hay acceso para un empleado de dicha categoria");
            }
        });


        //Se añade al ContentPane de la ventana
        cp.add(ingresar, BorderLayout.SOUTH);
    }
}
