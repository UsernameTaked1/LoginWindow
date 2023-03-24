import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;             
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ProgramaMuestra {
    private int metodo = 0;
    private final String[] nombresbtns = {"Iteración", "Biseccion", "Falsa Posicion", "Newton-Raphson", "Secante", "Calcular", "Regresar", "Clear"};
    private final String [] nombrestxts = {"Tiempo","Masa", "Velocidad", "Limite menor", "Limite mayor", "Cifras Significativas"};
    private JPanel botones, valores, resultado;
    private MA m;
    private JFrame f;
    private JPanel p;
    private JButton[] btns;
    private JTextArea[] txts;
    public JTextArea r;

    public ProgramaMuestra() {
        //ventana
        botones = new JPanel();
        valores = new JPanel();
        resultado = new JPanel();
        m = new MA();
        f = new JFrame();
        p = new JPanel();
        //botones
        btns = new JButton[nombresbtns.length];
        for (int i = 0; i < nombresbtns.length; i++) {
            btns[i] = new JButton(nombresbtns[i]);
            btns[i].addActionListener(new menu());
        }
        //calcular
        txts = new JTextArea[nombrestxts.length];
        for (int i = 0; i < nombrestxts.length; i++) {
            txts[i] = new JTextArea(nombrestxts[i]);
        }
        r = new JTextArea();
        //métodos
        atributos();
        armado();
        mostrar();
    }
    private void atributos() {
        f.setDefaultCloseOperation(EXIT_ON_CLOSE);
        f.setSize(700, 300);
        p.setBackground(new Color(255, 248, 231));
        r.setSize(300, 300);
        r.setEditable(false);
        r.setEditable(true);
        botones.setBackground(new Color(226, 217, 195));
        valores.setBackground(new Color(226, 217, 195));
        resultado.setBackground(new Color(226, 217, 195));
        r.setEditable(false);
        r.setText("RESULTADO");
    }
    private void armado() {
        f.add(p);
        p.add(valores, BorderLayout.NORTH);
        p.add(botones, BorderLayout.CENTER);
        p.add(resultado, BorderLayout.SOUTH);

        for (int i = 0; i < nombresbtns.length - 3; i++) {
            botones.add(btns[i], BorderLayout.CENTER);
        }
        for (int i = 0; i < nombrestxts.length; i++) {
            valores.add(txts[i], BorderLayout.CENTER);
        }
        resultado.add(r, BorderLayout.CENTER);
        for (int i = nombresbtns.length - 3; i < nombresbtns.length; i++) {
            resultado.add(btns[i], BorderLayout.SOUTH);
        }
    }
    private void mostrar() {
        resultado.setVisible(false);
        valores.setVisible(false);
        f.show(true);

    }
    public void calcular(String t) {
        r.setText(t);
    }
    private class menu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for (int i = 0; i < btns.length - 3; i++) {
                if (e.getSource() == btns[i]) {
                    metodo = i;
                }
            }
            if ((e.getSource() == btns[0])) {
                System.out.println("4");
                txts[0].setVisible(false);
                txts[1].setVisible(false);
                txts[2].setVisible(false);
                txts[3].setVisible(false);
                txts[4].setText("Exponencial");
                botones.setVisible(false);
                valores.setVisible(true);
                resultado.setVisible(true);
            }
            if ((e.getSource() == btns[4])) {
                System.out.println("4");
                txts[0].setVisible(false);
                txts[1].setVisible(false);
                txts[2].setVisible(false);
                botones.setVisible(false);
                valores.setVisible(true);
                resultado.setVisible(true);
            }
            if ((e.getSource() != btns[3]) && (e.getSource() != btns[6]) && (e.getSource() != btns[7]) && (e.getSource() != btns[5])) {
                botones.setVisible(false);
                valores.setVisible(true);                
                resultado.setVisible(true);
            } else {
                if ((e.getSource() == btns[3])) {
                    txts[4].setVisible(false);
                    txts[0].setVisible(false);
                    txts[1].setVisible(false);
                    txts[2].setVisible(false);
                    botones.setVisible(false);
                    valores.setVisible(true);
                    resultado.setVisible(true);
                } else {
                    if (e.getSource() == btns[6]) {
                        txts[0].setVisible(true);
                        txts[1].setVisible(true);
                        txts[2].setVisible(true);
                        txts[3].setVisible(true);
                        txts[4].setVisible(true);
                        botones.setVisible(true);
                        valores.setVisible(false);
                        resultado.setVisible(false);
                    } else {
                        if (e.getSource() == btns[7]) {
                            r.setText("RESULTADO");
                            for (int i = 0; i < nombrestxts.length; i++) {
                                txts[i].setText(nombrestxts[i]);
                            }
                        } else {
                            System.out.println(metodo);
                            switch (metodo) {
                                case 0:
                                    m.r = "términos         resultado           Et%             Ea%";
                                    try{
                                    calcular(m.it(Double.parseDouble(txts[4].getText()), Integer.parseInt(txts[5].getText())));
                                }catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Por favor revise que sus parámetros sean válidos o que no contengan caracteres cómo espacios en blanco\nError:  " + ex.getMessage());                                    
                                }
                                    break;
                                case 1:
                                    m.r = "Iteración         xl           xu            xr             Ea%";
                                try {
                                    calcular(m.bsc(Double.parseDouble(txts[3].getText()), Double.parseDouble(txts[4].getText()), Double.parseDouble(txts[1].getText()), Double.parseDouble(txts[2].getText()), Double.parseDouble(txts[0].getText()),Integer.parseInt(txts[5].getText())));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Por favor revise que sus parámetros sean válidos o que no contengan caracteres cómo espacios en blanco\nError:  " + ex.getMessage());                                    
                                }
                                break;
                                case 2:
                                    m.r = "Iteración         xl           xu            xr             Ea%";
                                try {
                                    calcular(m.fPs(Double.parseDouble(txts[3].getText()), Double.parseDouble(txts[4].getText()), Double.parseDouble(txts[1].getText()), Double.parseDouble(txts[2].getText()), Double.parseDouble(txts[0].getText()),Integer.parseInt(txts[5].getText())));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Por favor revise que sus parámetros sean válidos o que no contengan caracteres cómo espacios en blanco\nError:  " + ex.getMessage());
                                }
                                break;
                                case 3:
                                    m.r = "i         xi           Ea%";
                                try {
                                    calcular(m.nR(Double.parseDouble(txts[3].getText()),Integer.parseInt(txts[5].getText())));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Por favor revise que sus parámetros sean válidos o que no contengan caracteres cómo espacios en blanco\nError:  " + ex.getMessage());
                                }
                                break;
                                case 4:
                                    m.r = "i         xi           Ea%";
                                try {
                                    calcular(m.sec(Double.parseDouble(txts[3].getText()), Double.parseDouble(txts[4].getText()),Integer.parseInt(txts[5].getText())));
                                } catch (Exception ex) {
                                    JOptionPane.showMessageDialog(null, "Por favor revise que sus parámetros sean válidos o que no contengan caracteres cómo espacios en blanco\nError:  " + ex.getMessage());
                                }
                                break;
                            }
                        }
                    }
                }
            }
            f.pack();
        }
    }    
    public static void main(String[] args) {
        ProgramaMuestra i = new ProgramaMuestra();
    }
}
