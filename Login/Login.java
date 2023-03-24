import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame {
    private JLabel u, c;
    private final JTextField usuario;
    private final JTextField contraseña;
    private JButton login;
    private JButton crear;
    private final JPanel p;
    private Archivos a;

    public Login() {
        super("Login");
        u = new JLabel("Usuario:");
        c = new JLabel("Contraseña:");
        a = new Archivos();
        p = new JPanel();
        usuario = new JTextField("Usuario");
        contraseña = new JTextField("Contraseña");
        login = new JButton("Ingresar");
        crear = new JButton("Crear Usaurio");
        atributos();
        armado();
        escuchas();
        mostrar();
    }

    public void atributos() {
        setSize(300, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void armado() {
        add(p, BorderLayout.CENTER);
        p.add(u,BorderLayout.NORTH);
        p.add(usuario, BorderLayout.NORTH);
        p.add(c,BorderLayout.NORTH);
        p.add(contraseña, BorderLayout.NORTH);
        p.add(login, BorderLayout.PAGE_END);
        p.add(crear, BorderLayout.PAGE_END);
    }

    public void mostrar() {
        setVisible(true);
        repaint();
    }

    public void escuchas() {
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] c = a.readDataFromFile().split("@");
                for (int i = 0; i < c.length; i=i+2) {
                    if (c[i].equals(a.hashPassword(usuario.getText())) && c[(i+1)].equals(a.hashPassword(contraseña.getText()))) {
                        ProgramaMuestra m = new ProgramaMuestra();
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                    }
                }
            }
        });

        crear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Usuarios u = new Usuarios();
                setVisible(false);
            }
        });
    }

    public static void main(String[] args) {
        Login l = new Login();
    }
}