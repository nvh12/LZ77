import java.sql.Time;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder document = new StringBuilder();
        System.out.println("Document input (end with an empty line):");
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            if (next.isEmpty()) break;
            document.append(next).append("\n");
        }
        String str = document.toString();
        System.out.println("Choose compress method:");
        System.out.println("1. Linear Scan");
        System.out.println("2. Hash Map Search");
        System.out.println("3. Substring Search");
        System.out.println("4. Test and compare");
        int option = scanner.nextInt();
        List<lz77Set> res;
        long start, end;
        switch (option) {
            case 1:
                start = System.nanoTime();
                res = lz77.linearCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                break;
            case 2:
                start = System.nanoTime();
                res = lz77.hashMapCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                break;
            case 3:
                start = System.nanoTime();
                res = lz77.substringSearchCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                break;
            case 4:
                start = System.nanoTime();
                res = lz77.linearCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                System.out.println();
                start = System.nanoTime();
                res = lz77.hashMapCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                System.out.println();
                start = System.nanoTime();
                res = lz77.substringSearchCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString() + " ");
                }
                break;
            default:
                break;
        }
        scanner.nextLine();
        scanner.nextLine();
        scanner.close();
    }
}
