
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JComponent;

class TripleShow extends JComponent {
    private Vector vec = new Vector();
    private int index = -1;
    private boolean encodemode;
    private int check;

    public TripleShow() {
        this.encodemode = true;
        this.check = 0;
    }

    public void paint(Graphics var1) {
        Dimension var2 = this.getSize();
        var1.setColor(Color.white);
        var1.fillRect(0, 0, var2.width, var2.height);
        int var3;
        MappingLZ77 var4;
        if (this.encodemode) {
            if (this.index != -1) {
                var1.setFont(new Font("Helvetica", Font.BOLD, 16));
                var1.setColor(MyColor.dark_purple);

                for (var3 = 0; var3 < this.index; ++var3) {
                    var4 = (MappingLZ77) this.vec.elementAt(var3);
                    var1.drawString("< " + var4.getOffset() + " , " + var4.getLength() + " , " + (char) var4.getNextchar() + " >", 20 + 140 * (var3 / 7), 30 + 30 * (var3 % 7));
                }

                if (this.check == 0) {
                    setColor(var1);
                }
            }
        } else {
            var1.setFont(new Font("Helvetica", Font.BOLD, 16));
            var1.setColor(MyColor.dark_purple);

            for (var3 = 0; var3 < this.vec.size(); ++var3) {
                var4 = (MappingLZ77) this.vec.elementAt(var3);
                var1.drawString("< " + var4.getOffset() + " , " + var4.getLength() + " , " + (char) var4.getNextchar() + " >", 20 + 140 * (var3 / 7), 30 + 30 * (var3 % 7));
            }

            if (this.index != -1) {
                setColor(var1);
            }
        }

    }

    private void setColor(Graphics var1) {
        MappingLZ77 var4;
        var1.setColor(Color.red);
        var4 = (MappingLZ77) this.vec.elementAt(this.index);
        var1.drawString("< " + var4.getOffset() + " , " + var4.getLength() + " , " + (char) var4.getNextchar() + " >", 20 + 140 * (this.index / 7), 30 + 30 * (this.index % 7));
    }

    public void reDraw(int var1) {
        if (this.index == var1) {
            this.check = 0;
        } else {
            this.check = 1;
        }

        this.index = var1;
        this.repaint();
    }

    public void resetAll() {
        this.index = -1;
        this.check = 0;
        this.repaint();
    }

    public void setDataVector(Vector var1) {
        this.vec = var1;
    }

    public void setMode(boolean var1) {
        this.encodemode = var1;
        this.repaint();
    }
}
