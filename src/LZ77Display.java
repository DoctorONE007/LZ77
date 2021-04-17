
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JComponent;

class LZ77Display extends JComponent {
    private final LZ77 lz77;
    private int len;
    private int index = -1;
    private final TripleShow ts;
    private final Inst77Display id;
    private final Encoding77 encoding;
    private String inputString;
    private char[] inputArray;
    private Vector vec;
    private boolean error = false;
    private int offset;
    private int match_len;
    private int lookbufstart;
    private final int startpoint = 400;
    private boolean encodemode;
    private int bufsize;
    private int check;

    public LZ77Display(LZ77 var1, TripleShow var2, Inst77Display var3, Encoding77 var4, String var5) {
        this.lz77 = var1;
        this.ts = var2;
        this.id = var3;
        this.encoding = var4;
        this.encodemode = true;
        this.inputString = var5;
        this.inputArray = new char[100];
        this.inputArray = this.inputString.toCharArray();
        this.len = this.inputString.length();
        var4.newEncoding77(this.inputString);
        this.vec = var4.getDataVector();
        var2.setDataVector(this.vec);
        var3.setDataVector(this.vec);
        this.bufsize = 10;
        this.check = 0;
    }

    public void drawBuffer() {
        if (this.encodemode) {
            if (this.check == 0) {
                if (this.index < this.vec.size()) {
                    ++this.index;
                }

                if (this.index < this.vec.size()) {
                    map();
                    this.check = 1;
                }
            } else if (this.check == 1) {
                if (this.index < this.vec.size()) {
                    map();
                    this.check = 0;
                }
            }
        } else {
            if (this.index < this.vec.size()) {
                ++this.index;
            }

            if (this.index < this.vec.size()) {
                map();
            }

        }

        this.repaint();
    }

    private void map() {
        MappingLZ77 var1;
        var1 = (MappingLZ77) this.vec.elementAt(this.index);
        this.offset = var1.getOffset();
        this.match_len = var1.getLength();
        this.lookbufstart = var1.getLookbufstart();
        this.lz77.setHScrollValue(this.lookbufstart * 20 - 50);
        this.lz77.setVScrollValue(this.index / 7 * 140 - 140);
        this.ts.reDraw(this.index);
        this.id.reDraw(this.index);
    }

    public void drawGoBuffer() {
        for(int var1 = 0; var1 < this.vec.size() * 2; ++var1) {
            this.drawBuffer();
        }

        this.repaint();
    }

    public void paint(Graphics var1) {
        Dimension var2 = this.getSize();
        var1.setColor(Color.white);
        var1.fillRect(0, 0, var2.width, var2.height);
        int var3;
        if (this.encodemode) {
            if (this.index != -1) {
                var1.setColor(MyColor.light_blue);
                var1.fillRect(this.startpoint + 20 * this.lookbufstart, 30, this.bufsize * 20, 20);
                var1.setColor(MyColor.green1);
                var1.drawRect(this.startpoint + 20 * this.lookbufstart, 30, this.bufsize * 20, 20);
                var1.setColor(MyColor.yellow);
                var1.fillRect(this.startpoint + 20 * this.lookbufstart - this.bufsize * 20, 30, this.bufsize * 20, 20);
                var1.setColor(MyColor.light_orange);
                var1.drawRect(this.startpoint + 20 * this.lookbufstart - this.bufsize * 20, 30, this.bufsize * 20, 20);
                var1.setColor(MyColor.purple);
                var1.drawLine(this.startpoint + 20 * this.lookbufstart, 30, this.startpoint + 20 * this.lookbufstart + 15, 20);
                var1.drawLine(this.startpoint + 20 * this.lookbufstart + this.bufsize * 20, 30, this.startpoint + 20 * this.lookbufstart + this.bufsize * 20 - 15, 20);
                var1.drawString("буфер опереж. просмотра", this.startpoint + 20 * this.lookbufstart + 20 * this.bufsize / 4-20, 20);
                var1.setColor(MyColor.dark_purple);
                var1.drawLine(this.startpoint + 20 * this.lookbufstart - this.bufsize * 20, 30, this.startpoint + 20 * this.lookbufstart + 15 - this.bufsize * 20, 20);
                var1.drawLine(this.startpoint + 20 * this.lookbufstart, 30, this.startpoint + 20 * this.lookbufstart - 15, 20);
                var1.drawString("буфер поиска", this.startpoint + 20 * this.lookbufstart + this.bufsize * 20 / 3 - this.bufsize * 20-20, 20);
            }

            var1.setColor(MyColor.dark_blue);
            var1.setFont(new Font("Helvetica", Font.BOLD, 14));

            for(var3 = 0; var3 < this.len; ++var3) {
                var1.drawString(Character.toString(this.inputArray[var3]), this.startpoint + 7 + 20 * var3, 45);
            }

            if (this.index != -1) {
                var1.setColor(Color.red);
                var1.fillRect(this.startpoint + 20 * this.lookbufstart, 30, 20, 20);
                var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                var1.setColor(MyColor.yellow);
                var1.drawString(Character.toString(this.inputArray[this.lookbufstart]), this.startpoint + 7 + 20 * this.lookbufstart, 45);
                if (this.offset == 0 && this.check == 0) {
                    var1.setColor(MyColor.dark_purple);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 16));
                    var1.drawString("Нет совпадений", this.startpoint + 20 * this.lookbufstart + this.bufsize * 20 + 40+20, 22);
                } else if (this.check == 0) {
                    var1.setColor(MyColor.dark_purple);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 16));
                    var1.drawString("длина совпадения : " + this.match_len, this.startpoint + 20 * this.lookbufstart + this.bufsize * 20 + 30+30, 22);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                    var1.drawLine(this.startpoint + 20 * (this.lookbufstart - this.offset) + 10, 55, this.startpoint + 20 * (this.lookbufstart - this.offset) + 10, 62);
                    var1.drawString("смещение : " + this.offset, this.startpoint + 20 * (this.lookbufstart - this.offset) - 20-30, 75);
                    var1.drawLine(this.startpoint + 20 * (this.lookbufstart + this.match_len) + 10, 55, this.startpoint + 20 * (this.lookbufstart + this.match_len) + 10, 72);
                    var1.drawString("кодирование", this.startpoint + 20 * (this.lookbufstart + this.match_len) - 5-30, 85);
                    var1.setColor(MyColor.green1);
                    var1.fillRect(this.startpoint + 20 * (this.lookbufstart - this.offset), 30, 20 * this.match_len, 20);
                    var1.setColor(MyColor.green1);
                    var1.fillRect(this.startpoint + 20 * this.lookbufstart, 30, 20 * this.match_len, 20);
                    setFont(var1);
                }
            }

            setColor(var1);

            if (this.error) {
                var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                var1.setColor(Color.red);
                var1.drawString("The length of the input string is limited to 100. Please try again.(Reset)", 10, 20);
            }
        } else {
            if (this.index != -1) {
                if (this.offset != 0) {
                    var1.setColor(MyColor.dark_purple);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                    var1.drawLine(this.startpoint + 20 * (this.lookbufstart - this.offset) + 10, 55, this.startpoint + 20 * (this.lookbufstart - this.offset) + 10, 62);
                    var1.drawString("смещение : " + this.offset, this.startpoint + 20 * (this.lookbufstart - this.offset) - 20-20, 75);
                    var1.setColor(MyColor.green1);
                    var1.fillRect(this.startpoint + 20 * (this.lookbufstart - this.offset), 30, 20 * this.match_len, 20);
                    var1.setColor(Color.red);
                    var1.fillRect(this.startpoint + 20 * this.lookbufstart, 30, 20 * this.match_len, 20);
                    var1.setColor(Color.red);
                    var1.drawLine(this.startpoint + 20 * (this.lookbufstart - this.offset) + 20 * this.match_len / 2, 25, this.startpoint + 20 * (this.lookbufstart - this.offset) + 20 * this.match_len / 2, 20);
                    var1.drawLine(this.startpoint + 20 * this.lookbufstart + 20 * this.match_len / 2, 25, this.startpoint + 20 * this.lookbufstart + 20 * this.match_len / 2, 20);
                    var1.drawLine(this.startpoint + 20 * (this.lookbufstart - this.offset) + 20 * this.match_len / 2, 20, this.startpoint + 20 * this.lookbufstart + 20 * this.match_len / 2, 20);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                    var1.drawString("Копирование", (this.startpoint + 20 * (this.lookbufstart - this.offset) + 20 * this.match_len / 2 + 20 * this.match_len / 2 + this.startpoint + 20 * this.lookbufstart) / 2 - 50, 18);
                }

                var1.setColor(MyColor.light_blue);
                var1.fillRect(this.startpoint + 20 * (this.lookbufstart + this.match_len), 30, 20, 20);
                var1.setColor(MyColor.green1);
                var1.drawRect(this.startpoint + 20 * (this.lookbufstart + this.match_len), 30, 20, 20);
                var1.setColor(MyColor.dark_blue);
                var1.setFont(new Font("Helvetica", Font.BOLD, 14));

                for(var3 = 0; var3 < this.lookbufstart + this.match_len + 1; ++var3) {
                    var1.drawString(Character.toString(this.inputArray[var3]), this.startpoint + 7 + 20 * var3, 45);
                }

                var1.setColor(MyColor.dark_purple);
                var1.drawLine(this.startpoint + 20 * (this.lookbufstart + this.match_len) + 10, 55, this.startpoint + 20 * (this.lookbufstart + this.match_len) + 10, 72);
                var1.drawString("Декодирование " + this.inputArray[this.lookbufstart + this.match_len], this.startpoint + 20 * (this.lookbufstart + this.match_len) - 20 - 2-30, 90);
                setFont(var1);
            }

            setColor(var1);
        }

    }

    private void setFont(Graphics var1) {
        int var4;
        var1.setFont(new Font("Helvetica", Font.BOLD, 14));
        var1.setColor(MyColor.yellow);

        for(var4 = 0; var4 < this.match_len; ++var4) {
            var1.drawString(Character.toString(this.inputArray[this.lookbufstart + var4]), this.startpoint + 7 + 20 * (this.lookbufstart + var4), 45);
            var1.drawString(Character.toString(this.inputArray[this.lookbufstart - this.offset + var4]), this.startpoint + 7 + 20 * (this.lookbufstart - this.offset + var4), 45);
        }
    }

    private void setColor(Graphics var1) {
        int var3;
        var1.setColor(MyColor.light_gray);
        var1.drawLine(this.startpoint, 30, this.startpoint + 20 * this.len, 30);
        var1.drawLine(this.startpoint, 50, this.startpoint + 20 * this.len, 50);

        for(var3 = 0; var3 <= this.len; ++var3) {
            var1.drawLine(this.startpoint + 20 * var3, 30, this.startpoint + 20 * var3, 50);
        }
    }

    public void reDraw(String var1) {
        this.inputString = var1;
        this.inputArray = this.inputString.toCharArray();
        this.len = this.inputString.length();
        this.encoding.newEncoding77(this.inputString);
        this.vec = this.encoding.getDataVector();
        this.ts.setDataVector(this.vec);
        this.id.setDataVector(this.vec);
        this.lz77.setPaneSize(this.inputString.length() * 20 + 1000);
        this.lz77.setPane1Size(this.vec.size() * 30 + 600);
        this.repaint();
    }

    public void resetAll() {
        this.error = false;
        this.lz77.setHScrollValue(0);
        this.lz77.setVScrollValue(0);
        this.index = -1;
        this.ts.resetAll();
        this.id.resetAll();
        this.check = 0;
        this.repaint();
    }

    public void setBufSize(int var1) {
        this.bufsize = var1;
        this.encoding.setBufSize(var1);
        this.id.setBufSize(var1);
    }

    public void setMode(boolean var1) {
        this.encodemode = var1;
        this.ts.setMode(var1);
        this.id.setMode(var1);
        this.repaint();
    }

}
