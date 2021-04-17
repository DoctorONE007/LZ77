
class MappingLZ77 {
    private final int offset;
    private final int match_length;
    private final int lookbuf_start;
    private final char nextchar;

    public MappingLZ77(int var1, int var2, int var3, char var4) {
        this.offset = var1;
        this.match_length = var2;
        this.lookbuf_start = var3;
        this.nextchar = var4;
    }

    public int getLength() {
        return this.match_length;
    }

    public int getLookbufstart() {
        return this.lookbuf_start;
    }

    public int getNextchar() {
        return this.nextchar;
    }

    public int getOffset() {
        return this.offset;
    }
}
