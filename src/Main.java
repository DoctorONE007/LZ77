import java.awt.*;
import javax.swing.*;

public class Main extends JPanel {
    public static JFrame frame;
    private final LZ77 lz77;
    private final JPanel panel;
    private final Main press = this;

    static Main access$0(Main lz) {
        return lz.press;
    }

    static JPanel access$2(Main lz) {
        return lz.panel;
    }

    static LZ77 access$3(Main lz) {
        return lz.lz77;
    }

    public static void main(String[] strArr) {
        frame = new JFrame("LZ77");
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.show();
        frame.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());
        JLabel jLabel = new JLabel("Loading.. Please wait...");
        jLabel.setHorizontalAlignment(0);
        frame.getContentPane().add(jLabel, "Center");
        frame.validate();
        frame.repaint();
        Main lz = new Main();
        frame.getContentPane().removeAll();
        frame.getContentPane().add(lz, "Center");
        frame.validate();
        frame.repaint();
        frame.addWindowListener(new LZ$1());
    }

    public Main() {
        super(true);
        JMenuBar constructMenuBar = constructMenuBar();
        setLayout(new BorderLayout());
        this.panel = new JPanel(new BorderLayout());
        JPanel createLogo = createLogo();
        this.lz77 = new LZ77();
        this.panel.add(createLogo, "Center");
        add(constructMenuBar, "North");
        add(this.panel, "Center");
        frame.setTitle("LZ77");
    }

    public JPanel createLogo() {
        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel(new ImageIcon("F:/HSE/CourseWork/Курсач2021/LZ77/images/LOGO1.png")), "Center");
        return jPanel;
    }

    private JMenuBar constructMenuBar() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu add = jMenuBar.add(new JMenu("Алгоритмы"));
        JMenu jMenu2 = new JMenu("Помощь");
        jMenuBar.add(jMenu2);

        jMenu2.add(new JSeparator());
        jMenu2.add(new JMenuItem("О программе")).addActionListener(new LZ$7(this));
        ButtonGroup buttonGroup = new ButtonGroup();
        LZ$8 r4 = new LZ$8(this);
        JRadioButtonMenuItem add2 = (JRadioButtonMenuItem) add.add(new JRadioButtonMenuItem("LZ77"));
        buttonGroup.add(add2);
        add2.addItemListener(r4);

        return jMenuBar;
    }
}