package ejemploproyecto4to;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class ventana extends JFrame {

    JPanel panelInicioSesion, panelNuevoUsuario, panelClientes;
    JTextField txtusuario;
    JPasswordField txtcontra;
    usuario misUsuarios[] = new usuario[10];

    public ventana() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
        misUsuarios[0] = new usuario("Admin", "123");
        misUsuarios[1] = new usuario("otro", "321");
    }

    public void iniciarComponentes() {
        panelInicioSesion = new JPanel();
        panelInicioSesion.setLayout(null);
        this.getContentPane().add(panelInicioSesion);
        componetesInicioSesion();

       
        
        panelClientes = new JPanel();
        panelClientes.setLayout(null);
        this.getContentPane().add(panelClientes);
    }

    public void componetesInicioSesion() {
         this.setTitle("Inicio Sesion");
        JLabel lblUsuario = new JLabel("Ingrese su usuario");
        lblUsuario.setBounds(50, 15, 150, 15);
        panelInicioSesion.add(lblUsuario);

        JLabel lblcontra = new JLabel("Ingrese su password");
        lblcontra.setBounds(50, 50, 150, 15);
        panelInicioSesion.add(lblcontra);

        txtusuario = new JTextField();
        txtusuario.setBounds(210, 15, 150, 25);
        panelInicioSesion.add(txtusuario);

        txtcontra = new JPasswordField();
        txtcontra.setBounds(210, 50, 150, 25);
        panelInicioSesion.add(txtcontra);

        JButton btnIniciar = new JButton("Ingresar");
        btnIniciar.setBounds(200, 150, 100, 30);
        panelInicioSesion.add(btnIniciar);

        ActionListener iniciarSesion = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                String contra = txtcontra.getText();
                if(buscarUsuario(usuario, contra)){
                    panelInicioSesion.setVisible(false);
                    panelClientes.setVisible(true);
                    componentesClientes();
                }


            }
        };
        btnIniciar.addActionListener(iniciarSesion);
        //registrar usuario
        JButton btnNuevoUsuario = new JButton("Agregar");
        btnNuevoUsuario.setBounds(340, 150, 100, 30);
        panelInicioSesion.add(btnNuevoUsuario);
        ActionListener nuevoUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               componentesNuevoUsuario();
                panelInicioSesion.setVisible(false);
                panelNuevoUsuario.setVisible(true);
            }
        };
        btnNuevoUsuario.addActionListener(nuevoUsuario);

        panelInicioSesion.repaint();
    }

    public boolean buscarUsuario(String usuario, String contra) {
        boolean encontrado = false;
        String Name = "";
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
                if (misUsuarios[i].getName().equals(usuario) && misUsuarios[i].getPass().equals(contra)) {
                    encontrado = true;
                    Name = misUsuarios[i].getName();
                    break;
                }
            }
        }

        if (encontrado) {
            JOptionPane.showMessageDialog(null, "Bienvenido " + Name);
        } else {
            JOptionPane.showMessageDialog(null, "Datos Incorrectos");
        }
        return encontrado;
    }

    public void componentesNuevoUsuario() {
        this.setTitle("Crear nueva cuenta");

        JLabel nuevoNombre = new JLabel("ingrese nombre de usurio: ");
        nuevoNombre.setBounds(80, 25, 170, 20);
        panelNuevoUsuario.add(nuevoNombre);
        //agregar nombre de usuario
        JTextField txtNombre = new JTextField();
        txtNombre.setBounds(250, 25, 150, 25);
        panelNuevoUsuario.add(txtNombre);

        JLabel nuevaContra = new JLabel("ingrese una contraseña: ");
        nuevaContra.setBounds(80, 65, 170, 20);
        panelNuevoUsuario.add(nuevaContra);

        JPasswordField txtNuevaContra = new JPasswordField();
        txtNuevaContra.setBounds(250, 65, 150, 25);
        panelNuevoUsuario.add(txtNuevaContra);
        
        JButton btnguardar = new JButton("Guardar");
        btnguardar.setBounds(200, 150, 100, 30);
        panelNuevoUsuario.add(btnguardar);
        ActionListener almacenar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = txtNombre.getText();
                String contra = txtNuevaContra.getText();

                if (guardarUsuario(nombre, contra)) {
                    txtNombre.setText("");
                    txtNuevaContra.setText("");
                    
                }

            }
        };
        btnguardar.addActionListener(almacenar);

        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(80, 150, 100, 30);
        panelNuevoUsuario.add(btnVolver);
        ActionListener volver = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               componetesInicioSesion();
                panelInicioSesion.setVisible(true);
                panelNuevoUsuario.setVisible(false);
            }
        };
        btnVolver.addActionListener(volver);
    }

    public boolean guardarUsuario(String nombre, String contra) {
        boolean guardado = false;
        if (nombre.equals("") || contra.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
        } else {
            // funcion para comprobar duplicados
            if(comprobarDuplicadosUsuarios(nombre)){
                JOptionPane.showMessageDialog(null, "Ya existe el usuario");
            } else {
                 boolean vacio = false;
            int posicion = -1;
            for (int i = 0; i <= misUsuarios.length - 1; i++) {
                if (misUsuarios[i] != null) {
                    vacio = true;
                    posicion = i;
                    break;
                }
            }
            if (vacio) {
                misUsuarios[posicion] = new usuario(nombre, contra);
                JOptionPane.showMessageDialog(null, "Usuario almacenado exitosamente");
            } else {
                JOptionPane.showMessageDialog(null, "Ya no se pueden guardar usuarios");
            }
            }
           
        }
        return guardado;
    }

    public boolean comprobarDuplicadosUsuarios(String nombre) {
        boolean duplicado = false;
        for (int i = 0; i <= misUsuarios.length - 1; i++) {
            if (misUsuarios[i] != null) {
               if(misUsuarios[i].getName().equals(nombre)){
                   duplicado = true;
                   break;
               }
            }
        }
        return duplicado;
    }
    //------------------------------Componentes para mostrar clientes-----------------------------------------
    
    public void componentesClientes(){
        this.setTitle("Dashboard de clientes");
        JLabel lblClientes = new JLabel("Clientes Amacenados");
        lblClientes.setBounds(10, 10, 150, 15);
        panelClientes.add(lblClientes);
    }
}
