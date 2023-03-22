import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame{
    private final JTextField usuario;
    private final JTextField contraseña;
    private JButton login;
    private final JPanel p;
    private String password;
    String hash;
    
    public Login(){
        password = "Ladesiempre";
        hash = hashPassword(password);
        p = new JPanel();
        usuario = new JTextField("Usuario");
        contraseña = new JTextField("Contraseña");
        login = new JButton("Ingresar");
        atributos();
        armado();
        escuchas();
        mostrar();
    }
    
    public void atributos(){
        setSize(300,300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void armado(){
        add(p,BorderLayout.CENTER);
        p.add(usuario, BorderLayout.NORTH);
        p.add(contraseña, BorderLayout.NORTH);
        p.add(login,BorderLayout.PAGE_END);
    }

    public void mostrar(){
        setVisible(true);
        repaint();
    }
    
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error al crear el hash de la contraseña.");
            return null;
        }
    }
    
    public void escuchas(){
        login.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (usuario.getText().equals("Tadeo")&& hashPassword(contraseña.getText()).equals(hash)) {
                    ProgramaMuestra i = new ProgramaMuestra();
                    setVisible(false);
                                }else{
                    JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrecto");
                }
                }
        });
    }
    
    public static void main(String[] args) {
        Login l = new Login();
    }
}