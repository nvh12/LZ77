import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder document = new StringBuilder();
        System.out.println("Document input (end with an empty line):");
        while (scanner.hasNextLine()) {
            String next = scanner.nextLine();
            if (next.isEmpty())
                break;
            document.append(next).append("\n");
        }
        String str = document.toString();
        System.out.println("Choose compress method:");
        System.out.println("1. Linear Scan");
        System.out.println("2. Hash Map Search");
        System.out.println("3. Substring Search");
        System.out.println("4. Test and compare");
        int option = scanner.nextInt();
        scanner.nextLine();
        List<lz77Set> res;
        long start, end;
        switch (option) {
            case 1:
                start = System.nanoTime();
                res = lz77.linearCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString());
                }
                break;
            case 2:
                start = System.nanoTime();
                res = lz77.hashMapCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString());
                }
                break;
            case 3:
                start = System.nanoTime();
                res = lz77.substringSearchCompress(str, 16, 16);
                end = System.nanoTime();
                System.out.println("Runtime: " + (end - start) + "ns");
                for (lz77Set r : res) {
                    System.out.print(r.toString());
                }
                break;
            case 4:
                System.out.println("Test output: ");
                String test = scanner.nextLine();
                String res1 = "", res2 = "", res3 = "";
                start = System.nanoTime();
                res = lz77.linearCompress(str, 16, 16);
                end = System.nanoTime();
                long time1 = end - start;
                for (lz77Set r : res) {
                    res1 = res1.concat(r.toString());
                }
                start = System.nanoTime();
                res = lz77.hashMapCompress(str, 16, 16);
                end = System.nanoTime();
                long time2 = end - start;
                for (lz77Set r : res) {
                    res2 = res2.concat(r.toString());
                }
                start = System.nanoTime();
                res = lz77.substringSearchCompress(str, 16, 16);
                end = System.nanoTime();
                long time3 = end - start;
                for (lz77Set r : res) {
                    res3 = res3.concat(r.toString());
                }
                boolean check1 = res1.equals(test), check2 = res2.equals(test), check3 = res3.equals(test);
                System.out.println("------TÍNH CHÍNH XÁC ------");
                System.out.println(
                        "Kiểm tra kết quả mã hóa của duyệt tuần tự: " + (check1 ? "CHÍNH XÁC" : "KHÔNG CHÍNH XÁC"));
                System.out.println(
                        "Kiểm tra kết quả mã hóa của dùng bảng: " + (check2 ? "CHÍNH XÁC" : "KHÔNG CHÍNH XÁC"));
                System.out.println(
                        "Kiểm tra kết quả mã hóa của tìm chuỗi: " + (check3 ? "CHÍNH XÁC" : "KHÔNG CHÍNH XÁC"));
                System.out.println("------TÍNH HIỆU QUẢ ------");
                System.out.println("Độ dài chuỗi gốc:  " + str.length());
                System.out.println("Độ dài chuỗi nén:  " + test.length());
                System.out.print("Kết luận tính hiệu quả: ");
                if (str.length() > test.length()) {
                    double reduced = (1 - 1.0 * test.length() / str.length()) * 100;
                    System.out.println("giảm được " + Math.round(reduced * 100) / 100 + " % kích thước của chuỗi gốc");
                } else {
                    System.out.println("Không giảm được kích thước của chuỗi gốc");
                }
                System.out.println("------THỜI GIAN THỰC HIỆN ------");
                System.out.println("Thời gian thực hiện duyệt tuần tự: " + time1 + " ns");
                System.out.println("Thời gian thực hiện tìm kiếm bảng băm: " + time2 + " ns");
                System.out.println("Thời gian thực hiện tìm kiếm chuỗi con: " + time3 + " ns");
                break;
            default:
                break;
        }
        scanner.nextLine();
        scanner.close();
    }
}
