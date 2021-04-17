import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;


final class LZ$7 implements ActionListener {
    private final Main this$0;

    LZ$7(Main lz) {
        this.this$0 = lz;
    }

    public void actionPerformed(ActionEvent actionEvent) {
        JOptionPane.showMessageDialog(Main.access$0(this.this$0), "LZ77 v1.0", "О программе", JOptionPane.INFORMATION_MESSAGE);
    }
}