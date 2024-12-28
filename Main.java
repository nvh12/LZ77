import java.util.*;

public class Main {
    public static void main(String[] args) {
        String str = "the_cat_sat_on_the_table";
        List<lz77Set> res = lz77.substringSearchCompress(str, 16, 16);
        for (lz77Set r : res) {
            System.out.print(r.toString() + " ");
        }
    }
}
