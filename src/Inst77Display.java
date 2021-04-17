

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Vector;
import javax.swing.JComponent;

class Inst77Display extends JComponent {
    private Vector vec = new Vector();
    private int index = -1;
    private boolean encodemode;
    private int bufsize;
    private int check;

    public Inst77Display() {
        this.encodemode = true;
        this.bufsize = 10;
        this.check = 0;
    }

    public void paint(Graphics var1) {
        Dimension var2 = this.getSize();
        var1.setColor(Color.white);
        var1.fillRect(0, 0, var2.width, var2.height);
        if (this.index == -1) {
            var1.setColor(MyColor.purple);
            var1.setFont(new Font("Helvetica", Font.BOLD, 16));
            var1.drawString("Кодирование:", 15, 40);
            var1.drawString("< o, l, n >", 35, 70);
            var1.setFont(new Font("Helvetica", Font.BOLD, 14));
            var1.setColor(MyColor.dark_purple);
            var1.drawString("o : offset - смещение", 12, 100);
            var1.drawString("l : length - длина совпадающей", 12, 120);
            var1.drawString("подстроки", 85, 140);
            var1.drawString("n : next - следующий символ", 12, 160);
            var1.setColor(MyColor.purple1);
            var1.drawString("Размер буфера - "+ this.bufsize, 12, 190);
            var1.setColor(MyColor.dark_blue);
            var1.drawString("'_' - обозначение пробела", 20, 220);
            var1.drawString("'$' - обозначение конца строки", 20, 240);
        } else {
            MappingLZ77 var3;
            if (this.encodemode) {
                var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                var1.setColor(MyColor.dark_purple);
                if (this.check == 1) {
                    var3 = (MappingLZ77)this.vec.elementAt(this.index);
                    var1.drawString("Смещение : " + var3.getOffset(), 20, 40);
                    var1.drawString("Длина совпадающей строки : " + var3.getLength(), 20, 60);
                    var1.drawString("Символ кодирования : " + (char) var3.getNextchar(), 20, 80);
                    if (var3.getLength() == 0) {
                        var1.drawString("Совпадений в буфере не найдено", 20, 120);
                    } else {
                        var1.drawString("Найдено максимальное совпадение", 20, 120);
                        var1.drawString("в буфере - ", 20, 140);
                        var1.setColor(MyColor.green1);
                        var1.fillRect(110, 125, 20, 20);
                        var1.setColor(MyColor.light_gray);
                        var1.drawRect(110, 125, 20, 20);
                    }
                } else {
                    var1.setColor(Color.red);
                    var1.fillRect(20, 40, 20, 20);
                    var1.setColor(MyColor.light_gray);
                    var1.drawRect(20, 40, 20, 20);
                    var1.setColor(MyColor.dark_blue);
                    var1.drawString(": первый элемент в буфере ", 50, 55);
                    var1.drawString("  опережающего просмотра", 50, 75);
                    var1.setColor(MyColor.purple1);
                    var1.setFont(new Font("Dialog", Font.BOLD, 14));
                    var1.drawString("Ищем максимальное совпадение", 8, 110);
                }
            } else {
                var1.setFont(new Font("Helvetica", Font.BOLD, 16));
                var1.setColor(Color.red);
                var3 = (MappingLZ77)this.vec.elementAt(this.index);
                var1.drawString("Декодирование  < " + var3.getOffset() + " , " + var3.getLength() + " , " + (char) var3.getNextchar() + " >", 20, 40);
                var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                var1.setColor(MyColor.dark_blue);
                var1.drawString("Смещение : " + var3.getOffset(), 20, 80);
                var1.drawString("Длина совпадающей строки : " + var3.getLength(), 20, 100);
                var1.drawString("Символ кодирования : " + (char) var3.getNextchar(), 20, 120);
                if (var3.getLength() != 0) {
                    var1.setColor(MyColor.green1);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                    var1.drawString("1. Перейдем к смещению " + var3.getOffset(), 20, 160);
                    var1.drawString("2. Скопируем " + var3.getLength() + " элементов", 20, 180);
                    var1.drawString("    из             в  ", 20, 205);
                    var1.setColor(MyColor.green1);
                    var1.fillRect(72, 190, 20, 20);
                    var1.setColor(MyColor.light_gray);
                    var1.drawRect(72, 190, 20, 20);
                    var1.setColor(Color.red);
                    var1.fillRect(122, 190, 20, 20);
                    var1.setColor(MyColor.light_gray);
                    var1.drawRect(122, 190, 20, 20);
                    var1.setColor(MyColor.green1);
                    var1.drawString("3. Символ декодирования :  " + (char) var3.getNextchar(), 20, 230);
                } else {
                    var1.setColor(MyColor.green1);
                    var1.setFont(new Font("Helvetica", Font.BOLD, 14));
                    var1.drawString("Символ декодирования :  " + (char) var3.getNextchar(), 20, 180);
                }
            }
        }

    }

    public void reDraw(int var1) {
        if (this.index == var1) {
            this.check = 1;
        } else {
            this.check = 0;
        }

        this.index = var1;
        this.repaint();
    }

    public void resetAll() {
        this.index = -1;
        this.check = 0;
        this.repaint();
    }

    public void setBufSize(int var1) {
        this.bufsize = var1;
    }

    public void setDataVector(Vector var1) {
        this.vec = var1;
    }

    public void setMode(boolean var1) {
        this.encodemode = var1;
        this.repaint();
    }
}
