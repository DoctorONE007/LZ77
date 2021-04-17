import java.awt.Cursor;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JRadioButtonMenuItem;

final class LZ$8 implements ItemListener {
    private final Main this$0;

    LZ$8(Main lz) {
        this.this$0 = lz;
    }

    public void itemStateChanged(ItemEvent itemEvent) {
        Main.access$0(this.this$0).setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JRadioButtonMenuItem jRadioButtonMenuItem = (JRadioButtonMenuItem) itemEvent.getSource();
        try {
            if (jRadioButtonMenuItem.isSelected()) {
                if (jRadioButtonMenuItem.getText().equals("LZ77")) {
                    Main.access$2(this.this$0).removeAll();
                    Main.access$2(this.this$0).add(Main.access$3(this.this$0), "Center");
                    Main.frame.setTitle("LZ77");
                }
            }
        } catch (Exception ignored) {
        }
        Main.access$0(this.this$0).invalidate();
        Main.access$0(this.this$0).validate();
        Main.access$0(this.this$0).repaint();
        Main.access$0(this.this$0).setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }
}