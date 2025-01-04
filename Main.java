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
        int option = scanner.nextInt();
        List<lz77Set> res;
        switch (option) {
            case 1:
                res = lz77.linearCompress(str, 16, 16);
                break;
            case 2:
                res = lz77.hashMapCompress(str, 16, 16);
                break;
            case 3:
                res = lz77.substringSearchCompress(str, 16, 16);
                break;
            default:
                res = new ArrayList<>();
                break;
        }
        for (lz77Set r : res) {
            System.out.print(r.toString() + " ");
        }
        scanner.nextLine();
        scanner.nextLine();
        scanner.close();
    }
}
