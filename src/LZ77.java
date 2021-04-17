import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

class LZ77 extends JPanel {
    private final JScrollBar scheduleSB;
    private final JScrollBar scheduleSB2;
    private final JPanel pane;
    private final JPanel pane2;
    private final TripleShow ts;
    private final LZ77Display ld;

    public LZ77() {
        this.setLayout(new BorderLayout());
        String default_input = this.inputProcess("abacabacabadaca");
        Encoding77 encoding = new Encoding77(default_input);
        LZ77 lz77 = this;
        this.ts = new TripleShow();
        Inst77Display id = new Inst77Display();
        this.ld = new LZ77Display(lz77, this.ts, id, encoding, default_input);
        TitledBorder idBorder = new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Обозначения", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Helvetica", Font.BOLD, 12), MyColor.dark_blue);
        TitledBorder tsBorder = new TitledBorder(new EmptyBorder(0, 0, 0, 0), "Кодирование", TitledBorder.LEFT, TitledBorder.ABOVE_TOP, new Font("Helvetica", Font.BOLD, 12), MyColor.dark_blue);
        this.pane = new JPanel(new BorderLayout());
        this.pane.add(this.ld, "Center");
        this.pane.setPreferredSize(new Dimension(default_input.length() * 20 + 800, 70));
        JPanel pane1 = new JPanel(new BorderLayout());
        pane1.add(id, "Center");
        pane1.setPreferredSize(new Dimension(230, 225));
        this.pane2 = new JPanel(new BorderLayout());
        this.pane2.add(this.ts, "Center");
        this.pane2.setPreferredSize(new Dimension(1200, 230));
        JScrollPane sp = new JScrollPane(this.pane);
        this.scheduleSB = sp.getHorizontalScrollBar();
        this.scheduleSB.setValues(0, 10, 0, default_input.length() * 20 + 600);
        JScrollPane sp1 = new JScrollPane();
        JScrollBar scheduleSB1 = sp1.getVerticalScrollBar();
        scheduleSB1.setValues(0, 10, 0, 250);
        JScrollPane sp2 = new JScrollPane();
        this.scheduleSB2 = sp2.getHorizontalScrollBar();
        this.scheduleSB2.setValues(0, 10, 0, 1200);
        sp.setPreferredSize(new Dimension(700, 110));
        sp1.setPreferredSize(new Dimension(300, 270));
        sp2.setPreferredSize(new Dimension(620, 270));
        sp.setViewportView(this.pane);
        sp1.setViewportView(pane1);
        sp2.setViewportView(this.pane2);
        JPanel var1 = new JPanel(new BorderLayout());
        JPanel var2 = new JPanel(new BorderLayout());
        var1.add(sp1, "Center");
        var2.add(sp2, "Center");
        JPanel var3 = new JPanel(new BorderLayout());
        JPanel var4 = new JPanel(new BorderLayout());
        JPanel var5 = new JPanel(new BorderLayout());
        var4.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(idBorder), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        var4.add(var1, "Center");
        var5.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createTitledBorder(tsBorder), BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        var5.add(var2, "Center");
        var3.add("North", sp);
        var3.add("West", var4);
        var3.add("East", var5);
        LZ77Controls lz77Controls = new LZ77Controls(this.ld);
        LZ77Conf lz77Conf = new LZ77Conf(lz77, this.ld);
        this.add("North", lz77Conf);
        this.add("Center", var3);
        this.add("South", lz77Controls);
    }



    public String inputProcess(String var1) {
        return var1.replace(' ', '_') + "$";
    }

    public void setHScrollValue(int var1) {
        this.scheduleSB.setValue(var1);
    }

    public void setPane1Size(int var1) {
        this.pane2.removeAll();
        this.pane2.setPreferredSize(new Dimension(var1, 300));
        this.pane2.add(this.ts, "Center");
    }

    public void setPaneSize(int var1) {
        this.pane.removeAll();
        this.pane.setPreferredSize(new Dimension(var1, 70));
        this.pane.add(this.ld, "Center");
    }

    public void setVScrollValue(int var1) {
        this.scheduleSB2.setValue(var1);
    }
}