public class Main {
    int x = 5;

//this is a comment
/*this is a comment */
//  \" \' \\
// 

    public static void main (String[] args) {
        int little = 9;
        double bigger = little;

        double uglier = 9.777E5d;
        int  tidier = (int) uglier;

        String firstName = "\"Ifham\" ", lastName = "Syafwan Fikri";
        String megatron = "Kamu suka kucing dan anjing\rSaya";
        // \r menggantikan kalimat setelahnya menjadi kalimat awal

        double x = 25E5d;
        System.out.println(firstName + lastName);
        System.out.println(x);

        System.out.println(bigger);

        System.out.println(uglier);
        System.out.println(tidier);

        System.out.println(firstName.toUpperCase());
        System.out.println(lastName.toLowerCase());

        System.out.println(firstName.indexOf(" "));
        System.out.println(megatron);
    }
}

