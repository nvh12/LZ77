import java.util.*;

class lz77Set {
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

class lz77 {
    static List<lz77Set> compress(String input, int searchBufferSize, int lookAheadBufferSize) {
        List<lz77Set> res = new ArrayList<>();
        int index = 0;
        while (index < input.length()) {
            int maxLength = 0;
            int maxOffset = 0;
            for (int j = Math.max(0, index - searchBufferSize); j < index; j++) {
                int length = 0;
                while (index + length < input.length()
                        && input.charAt(j + length) == input.charAt(index + length)
                        && length < lookAheadBufferSize) {
                    length++;
                }
                if (length > maxLength) {
                    maxLength = length;
                    maxOffset = index - j;
                }
            }
            if (maxLength > 1) {
                char nextChar = (index + maxLength < input.length()) ? input.charAt(index + maxLength) : '\0';
                res.add(new lz77Set(maxOffset, maxLength, nextChar));
                index += maxLength + 1;
            } else {
                res.add(new lz77Set(0, 0, input.charAt(index)));
                index++;
            }
        }
        return res;
    }
}

public class Main {
    public static void main(String[] args) {
        String str = "the_cat_sat_on_the_table";
        List<lz77Set> res = lz77.compress(str, 16, 16);
        for (lz77Set r : res) {
            System.out.print(r.toString() + " ");
        }
    }
}
