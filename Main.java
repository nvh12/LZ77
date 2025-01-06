import java.util.*;
import java.time.Instant;
import java.time.Duration;

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
        document.deleteCharAt(document.length()-1);
        String str = document.toString();
        System.out.println("Choose compress method:");
        System.out.println("1. Linear Scan");
        System.out.println("2. Hash Map Search");
        System.out.println("3. Substring Search");
        System.out.println("4. Test and Compare");
        int option = scanner.nextInt();
        scanner.nextLine();
        List<lz77Set> res, res1, res2, res3;
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
            case 4:
            {
                Instant now1 = Instant.now();
                res1 = lz77.linearCompress(str, 16,16);
                Instant now2 = Instant.now();
                res2 = lz77.hashMapCompress(str, 16, 16);
                Instant now3 = Instant.now();
                res3 = lz77.substringSearchCompress(str, 16, 16);
                Instant now4 = Instant.now();

                long linearTime = Duration.between(now1, now2).toNanos();
                long hashMapTime = Duration.between(now2, now3).toNanos();
                long substringSearchTime = Duration.between(now3, now4).toNanos();

                System.out.println("Test output: ");
                String test = scanner.nextLine();

                System.out.println("test: "+ test);

                String stringRes1 = "";
                String stringRes2 = "";
                String stringRes3 = "";
                for(lz77Set each: res1){
                    stringRes1 = stringRes1.concat(each.toString());
                }
                for(lz77Set each: res2){
                    stringRes2 = stringRes2.concat(each.toString());
                }
                for(lz77Set each: res3){
                    stringRes3 = stringRes3.concat(each.toString());
                }

                boolean check1 = stringRes1.equals(test), check2 = stringRes2.equals(test), check3 = stringRes3.equals(test);



                System.out.println("------TÍNH CHÍNH XÁC ------");
                System.out.println("Kiểm tra kết quả mã hóa của duyệt tuần tự: "+ (check1? "CHÍNH XÁC": "KHÔNG CHÍNH XÁC"));
                System.out.println("Kiểm tra kết quả mã hóa của dùng bảng: "+ (check2? "CHÍNH XÁC": "KHÔNG CHÍNH XÁC"));
                System.out.println("Kiểm tra kết quả mã hóa của tìm chuỗi: "+ (check3? "CHÍNH XÁC": "KHÔNG CHÍNH XÁC"));
                System.out.println();
                System.out.println("------TÍNH HIỆU QUẢ ------");
                System.out.println("Độ dài chuỗi gốc:  "+ str.length());
                System.out.println("Độ dài chuỗi nén:  "+ test.length());
                System.out.print("Kết luận tính hiệu quả: ");
                if(str.length()>test.length()){
                    System.out.println("giảm được " + (1 - 1.0*test.length()/str.length())*100 + " % kích thước của chuỗi gốc");
                }
                else{
                    System.out.println("Không giảm được kích thước của chuỗi gốc");
                }

                System.out.println("------THỜI GIAN THỰC HIỆN ------");
                System.out.println("Thời gian thực hiện duyệt tuần tự: "+ linearTime + " ns");
                System.out.println("Thời gian thực hiện tìm kiếm bảng băm: "+ hashMapTime + " ns");
                System.out.println("Thời gian thực hiện tìm kiếm chuỗi con: "+ substringSearchTime + " ns");

            }

            default:
                res = new ArrayList<>();
                break;
        }
        for (lz77Set r : res) {
            System.out.print(r.toString());
        }
        scanner.nextLine();
        scanner.nextLine();
        scanner.close();
    }
}
