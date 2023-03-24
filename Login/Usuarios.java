import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
public class Usuarios extends JFrame {

    private JTextField usuario, contraseña;
    private JButton crear, regresar;
    private Archivos file;
    private JPanel txt, btns;
    private JLabel u, c;

    public Usuarios() {
        super("Creación de Usuarios");
        u = new JLabel("Usuario:");
        c = new JLabel("Contraseña:");
        file = new Archivos();
        usuario = new JTextField("Usuario");
        contraseña = new JTextField("Contraseña");
        crear = new JButton("Crear");
        regresar = new JButton("Regresar");
        txt = new JPanel();
        btns = new JPanel();
        atributos();
        armado();
        mostrar();
        escuchas();
    }

    public void atributos() {
        setSize(300,300);
    }

    public void armado() {
        add(txt, BorderLayout.NORTH);
        add(btns,BorderLayout.SOUTH);
        txt.add(u,BorderLayout.NORTH);
        txt.add(usuario, BorderLayout.NORTH);
        txt.add(c,BorderLayout.NORTH);
        txt.add(contraseña, BorderLayout.NORTH);
        btns.add(crear, BorderLayout.SOUTH);
        btns.add(regresar, BorderLayout.SOUTH);
    }

    public void mostrar() {
        setVisible(true);
        repaint();
    }

    public void escuchas() {
        crear.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    crearUsuario(usuario.getText(), contraseña.getText());
                } catch (Exception error) {
                    JOptionPane.showMessageDialog(null, error.getMessage());
                }
            }
        });
        regresar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Login l = new Login();
                setVisible(false);
            }
            
        });
    }

    public void crearUsuario(String u, String c) {
        file.writeDataToFile(file.hashPassword(u) + "@" + file.hashPassword(c));
        String r[] = file.readDataFromFile().split("@");
        JOptionPane.showMessageDialog(null, "Usuario:   " + r[0] + "\nContraseña:      " + r[1]);
    }
public static void main(String[] args) {
    Usuarios u = new Usuarios();
}
}