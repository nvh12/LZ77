public class lz77Set {
    int offset;
    int length;
    char character;

    lz77Set(int offset, int length, char character) {
        this.offset = offset;
        this.length = length;
        this.character = character;
    }

    @Override
    public String toString() {
        return "(" + offset + ", " + length + ", " + character + ")";
    }
}