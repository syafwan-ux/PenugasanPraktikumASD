public class Main2 {
    //next lesson for java

    public static void main(String[] args) {
        int randomNumber = (int)(Math.random() * 101);
        int x = 5, y = 7;

        System.out.println(x >= y);

        System.out.println(randomNumber);
        System.out.println(Math.max(100,10));
        System.out.println(Math.sqrt(65));
        System.out.println(Math.abs(-100));

        int z = 0;
        switch (z) {
            case 1:
                System.out.println("what the fuck");
                break;
        
            case 2:
                System.out.println("What the hell");
                break;

            default:
                System.out.println("javanese");
        }

        int p = 1;
        do {
            System.out.println(p);
            p++;
        }
        while (p < 3);

        for (int i = 1; i < 10; i++) {
            System.out.println(i);
        }

    }
}
