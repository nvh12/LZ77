public class lz77Set {
    int offset;
    int length;
    char character = '\0';

    lz77Set(int offset, int length, char character) {
        this.offset = offset;
        this.length = length;
        this.character = character;
    }
    @Override
    public String toString() {
        if(this.character == '\0')
            return "(" + offset + ","+length + ")";
        else if (this.character == '\n')
            return "(" + offset + "," + length + "," + "\\n" + ")";
        else
            return "(" + offset + "," + length + "," + character + ")";
    }
}