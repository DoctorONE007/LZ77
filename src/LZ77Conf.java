

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

class LZ77Conf extends JPanel implements ActionListener {
    private final JButton Selectbutton;
    private final JButton Inputbutton;
    private final JButton optionbutton;
    private final JRadioButton Encodebutton;
    private final JRadioButton Decodebutton;
    private final LZ77Display ld;
    private final LZ77 lz77;
    private String inputString;
    private final String[] stringList = new String[]{"abacabacabadaca", "cat caught a lizard a lizard a lizard", "ratatatat a rat at a rat", "she sells sea shells by the seashore"};
    private final int[] BufferList = new int[]{6, 8, 10, 12, 14};

    public LZ77Conf(LZ77 var1, LZ77Display var2) {
        this.lz77 = var1;
        this.ld = var2;
        this.inputString = var1.inputProcess(this.stringList[0]);
        this.setLayout(new FlowLayout());
        this.setPreferredSize(new Dimension(700, 40));
        this.Selectbutton = new JButton("Строки для демонстрации");
        this.Selectbutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.Selectbutton.addActionListener(this);
        this.Inputbutton = new JButton("Ввести свою строку");
        this.Inputbutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.Inputbutton.addActionListener(this);
        this.optionbutton = new JButton("Изменить размер буфера");
        this.optionbutton.setFont(new Font("Helvetica", Font.BOLD, 14));
        this.optionbutton.addActionListener(this);
        this.Encodebutton = new JRadioButton("Кодирование");
        this.Encodebutton.setActionCommand("Encoding");
        this.Encodebutton.setSelected(true);
        this.Encodebutton.addActionListener(this);
        this.Decodebutton = new JRadioButton("Декодирование");
        this.Decodebutton.setActionCommand("Decoding");
        this.Decodebutton.addActionListener(this);
        ButtonGroup group = new ButtonGroup();
        group.add(this.Encodebutton);
        group.add(this.Decodebutton);
        this.add(this.Encodebutton);
        this.add(this.Decodebutton);
        this.add(this.Selectbutton);
        this.add(this.Inputbutton);
        this.add(this.optionbutton);
    }

    public void actionPerformed(ActionEvent var1) {
        Object[] var2;
        JRadioButton var3;
        JRadioButton var4;
        JRadioButton var5;
        JRadioButton var6;
        if (var1.getSource() == this.Selectbutton) {
            var2 = new Object[6];
            var3 = new JRadioButton(this.stringList[0]);
            var4 = new JRadioButton(this.stringList[1]);
            var5 = new JRadioButton(this.stringList[2]);
            var6 = new JRadioButton(this.stringList[3]);
            var3.setActionCommand("In1");
            var4.setActionCommand("In2");
            var5.setActionCommand("In3");
            var6.setActionCommand("In4");
            ButtonGroup var7 = new ButtonGroup();
            var7.add(var3);
            var7.add(var4);
            var7.add(var5);
            var7.add(var6);
            var2[0] = "";
            var2[1] = var3;
            var2[2] = var4;
            var2[3] = var5;
            var2[4] = var6;
            var3.addActionListener(var119 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.inputString = LZ77Conf.this.lz77.inputProcess(LZ77Conf.this.stringList[0]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var4.addActionListener(var118 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.inputString = LZ77Conf.this.lz77.inputProcess(LZ77Conf.this.stringList[1]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var5.addActionListener(var117 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.inputString = LZ77Conf.this.lz77.inputProcess(LZ77Conf.this.stringList[2]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var6.addActionListener(var116 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.inputString = LZ77Conf.this.lz77.inputProcess(LZ77Conf.this.stringList[3]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            JOptionPane.showOptionDialog(this.lz77, var2, "Выберете строку для демонстрации", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        } else if (var1.getSource() == this.Inputbutton) {
            String var9 = JOptionPane.showInputDialog(this.lz77, "", "Введите строку для кодирования", JOptionPane.PLAIN_MESSAGE);
            if (var9 != null) {
                if (var9.length() < 60) {
                    this.ld.resetAll();
                    this.inputString = this.lz77.inputProcess(var9);
                    this.ld.reDraw(this.inputString);
                } else {
                    JOptionPane.showMessageDialog(this.lz77, "Ошибка ввода строки. \nДлина строки не может привышать 60 символов.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (var1.getSource() == this.Encodebutton) {
            this.ld.resetAll();
            this.ld.setMode(true);
        } else if (var1.getSource() == this.Decodebutton) {
            this.ld.resetAll();
            this.ld.setMode(false);
        } else if (var1.getSource() == this.optionbutton) {
            var2 = new Object[7];
            var3 = new JRadioButton((Integer.valueOf(this.BufferList[0])).toString());
            var4 = new JRadioButton((Integer.valueOf(this.BufferList[1])).toString());
            var5 = new JRadioButton((Integer.valueOf(this.BufferList[2])).toString());
            var6 = new JRadioButton((Integer.valueOf(this.BufferList[3])).toString());
            JRadioButton var10 = new JRadioButton((Integer.valueOf(this.BufferList[4])).toString());
            var3.setActionCommand("In1");
            var4.setActionCommand("In2");
            var5.setActionCommand("In3");
            var6.setActionCommand("In4");
            var10.setActionCommand("In5");
            ButtonGroup var8 = new ButtonGroup();
            var8.add(var3);
            var8.add(var4);
            var8.add(var5);
            var8.add(var6);
            var8.add(var10);
            var2[0] = "Выберете размер буфера:";
            var2[1] = var3;
            var2[2] = var4;
            var2[3] = var5;
            var2[4] = var6;
            var2[5] = var10;
            var3.addActionListener(var115 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.ld.setBufSize(LZ77Conf.this.BufferList[0]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var4.addActionListener(var114 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.ld.setBufSize(LZ77Conf.this.BufferList[1]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var5.addActionListener(var113 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.ld.setBufSize(LZ77Conf.this.BufferList[2]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var6.addActionListener(var112 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.ld.setBufSize(LZ77Conf.this.BufferList[3]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            var10.addActionListener(var11 -> {
                LZ77Conf.this.ld.resetAll();
                LZ77Conf.this.ld.setBufSize(LZ77Conf.this.BufferList[4]);
                LZ77Conf.this.ld.reDraw(LZ77Conf.this.inputString);
            });
            JOptionPane.showOptionDialog(this.lz77, var2, "", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, null, null);
        }

    }
}
