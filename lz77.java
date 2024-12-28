import java.util.*;

public class lz77 {
    static List<lz77Set> linearCompress(String input, int searchBufferSize, int lookAheadBufferSize) {
        List<lz77Set> res = new ArrayList<>();
        int index = 0;
        int inputLength = input.length();
        while (index < inputLength) {
            int maxLength = 0;
            int maxOffset = 0;
            for (int j = Math.max(0, index - searchBufferSize); j < index; j++) {
                int length = 0;
                while (index + length < inputLength
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
                char nextChar = (index + maxLength < inputLength) ? input.charAt(index + maxLength) : '\0';
                res.add(new lz77Set(maxOffset, maxLength, nextChar));
                index += maxLength + 1;
            } else {
                res.add(new lz77Set(0, 0, input.charAt(index)));
                index++;
            }
        }
        return res;
    }

    static List<lz77Set> hashMapCompress(String input, int searchBufferSize, int lookAheadBufferSize) {
        List<lz77Set> res = new ArrayList<>();
        int index = 0;
        int inputLength = input.length();
        while (index < inputLength) {
            int maxLength = 0;
            int maxOffset = 0;
            Map<Character, List<Integer>> charMap = new HashMap<>();
            for (int j = Math.max(0, index - searchBufferSize); j < index; j++) {
                char currBehind = input.charAt(j);
                charMap.putIfAbsent(currBehind, new ArrayList<>());
                charMap.get(currBehind).add(j);
            }
            char curr = input.charAt(index);
            if (charMap.containsKey(curr)) {
                for (int position : charMap.get(curr)) {
                    int length = 0;
                    while (length < lookAheadBufferSize && index + length < inputLength 
                            && input.charAt(index + length) == input.charAt(position + length)
                            && position + length < index) {
                        length++;
                    }
                    if (length > maxLength) {
                        maxLength = length;
                        maxOffset = index - position;
                    }
                }
            }
            if (maxLength > 1) {
                char nextChar = (index + maxLength < inputLength) ? input.charAt(index + maxLength) : '\0';
                res.add(new lz77Set(maxOffset, maxLength, nextChar));
                index += maxLength + 1;
            } else {
                res.add(new lz77Set(0, 0, input.charAt(index)));
                index++;
            }
        }
        return res;
    }

    static List<lz77Set> substringSearchCompress(String input, int searchBufferSize, int lookAheadBufferSize) {
        List<lz77Set> res = new ArrayList<>();
        int index = 0;
        int inputLength = input.length();
        while (index < inputLength) {
            int maxLength = 0;
            int maxOffset = 0;
            int start = Math.max(0, index- searchBufferSize);
            String searchBuffer = input.substring(start, index);
            for (int i = 1; i <= lookAheadBufferSize && index + i < inputLength; i++) {
                String targeString = input.substring(index, index + i);
                int position = searchBuffer.indexOf(targeString);
                if (position != -1) {
                    maxLength = i;
                    maxOffset = searchBuffer.length() - position;
                }
            }
            if (maxLength > 1) {
                char nextChar = (index + maxLength < inputLength) ? input.charAt(index + maxLength) : '\0';
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


