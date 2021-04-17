
import java.util.Vector;

class Encoding77 {
    private final Vector<MappingLZ77> data;
    private String input;
    private char[] inputArray = new char[100];
    private int len = 0;
    private final searchBuffer sbuf;
    private final lookaheadBuffer lbuf;
    private int bufsize;

    public Encoding77(String var1) {
        this.input = var1;
        this.data = new Vector<>();
        this.sbuf = new searchBuffer();
        this.lbuf = new lookaheadBuffer();
        this.newEncoding77(var1);
        this.bufsize = 10;
    }

    public void encodeProcess() {
        int var2 = 0;
        int var4 = 0;
        int var5 = 0;
        char var6 = this.inputArray[this.lbuf.get_lookstartbuf()];

        int var9;
        do {
            var9 = this.input.lastIndexOf(var6, this.lbuf.get_lookstartbuf() - var5 - 1);
            var5 = this.lbuf.get_lookstartbuf() - var9;
            if (var9 >= this.sbuf.get_searchstartbuf()) {
                int var8 = this.get_length_of_match(var9, this.lbuf);
                if (var2 < var8) {
                    var2 = var8;
                    var4 = this.lbuf.get_lookstartbuf() - var9;
                }
            }
        } while (var9 >= this.sbuf.get_searchstartbuf());

        char var7 = this.inputArray[this.lbuf.get_lookstartbuf() + var2];
        this.updateResultData(var4, var2, var7, this.lbuf.get_lookstartbuf());
        this.lbuf.setlookbufstart(this.lbuf.get_lookstartbuf() + var2 + 1);
        if (this.lbuf.get_lookstartbuf() < this.bufsize) {
            this.sbuf.setsbufstart(0);
        } else {
            this.sbuf.setsbufstart(this.lbuf.get_lookstartbuf() - this.bufsize);
        }

        this.sbuf.setsbufend();
    }

    public Vector<MappingLZ77> getDataVector() {
        return this.data;
    }

    public int get_length_of_match(int var1, lookaheadBuffer var2) {
        boolean var3 = true;
        int var4 = 0;

        for (int var5 = var2.get_lookstartbuf(); var3 && var5 < this.len; ++var4) {
            var3 = false;
            if (this.inputArray[var1] == this.inputArray[var5]) {
                var3 = true;
                ++var1;
                ++var5;
            }
        }

        return var4 - 1;
    }

    public void newEncoding77(String var1) {
        if (!this.data.isEmpty()) {
            this.data.removeAllElements();
        }

        this.input = var1;
        this.inputArray = this.input.toCharArray();
        this.len = this.input.length();
        this.lbuf.setlookbufstart(0);
        this.lbuf.setlookbufend();

        while (this.lbuf.get_lookstartbuf() < this.len) {
            this.encodeProcess();
        }

    }

    public void setBufSize(int var1) {
        this.bufsize = var1;
    }

    public void updateResultData(int var1, int var2, char var3, int var4) {
        this.data.addElement(new MappingLZ77(var1, var2, var4, var3));
    }
}
