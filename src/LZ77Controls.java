
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

class LZ77Controls extends JPanel implements ActionListener {
    private final LZ77Display ld;
    private final JButton resetbutton;
    private final JButton tracebutton;
    private final JButton gobutton;

    public LZ77Controls( LZ77Display var2) {
        this.ld = var2;
        this.setPreferredSize(new Dimension(700, 40));
        JPanel var3 = new JPanel(new BorderLayout());
        JPanel var4 = new JPanel();
        this.tracebutton = new JButton("Шаг");
        this.tracebutton.setBackground(Color.white);
        this.tracebutton.setForeground(MyColor.dark_blue);
        this.tracebutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.tracebutton.addActionListener(this);
        this.gobutton = new JButton("Запуск");
        this.gobutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.gobutton.setBackground(Color.white);
        this.gobutton.setForeground(MyColor.dark_blue);
        this.gobutton.addActionListener(this);
        this.resetbutton = new JButton("Сброс");
        this.resetbutton.setBackground(Color.white);
        this.resetbutton.setForeground(MyColor.dark_blue);
        this.resetbutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.resetbutton.addActionListener(this);
        var4.add(this.tracebutton);
        var4.add(this.gobutton);
        var4.add(this.resetbutton);
        var3.add(var4, "Center");
        this.add(var3, "South");
    }

    public void actionPerformed(ActionEvent var1) {
        if (var1.getSource() == this.tracebutton) {
            this.ld.drawBuffer();
        } else if (var1.getSource() == this.gobutton) {
            this.ld.drawGoBuffer();
        } else if (var1.getSource() == this.resetbutton) {
            this.ld.resetAll();
        }

    }
}
