public class Work1 {
    public static void main(String[] args) {
        int x = 0;
        while (x < 10){
            System.out.println("values"+x);
            x++;
        }
        int y = 0;
        //while循环
        do{
            System.out.println("values"+y);
            y++;
        }while(y<10);
        // do while循环
        for (int i = 0; i <4; i++) {

            for (int j = 0; j <i ; j++) {
                System.out.print("*");
            }
            System.out.println("\n");
        }
        for (int i = 0; i <4; i++) {

            for (int j = 4; j >i ; j--) {
                System.out.print("*");
            }
            System.out.println("\n");
        }


    }
}
