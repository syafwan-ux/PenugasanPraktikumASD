public class recursive {
    public static void main(String[] args) {
        int x = 10;
        System.out.println(menurun(x));

    }
    public static int menurun(int x) {
        if (x > 0) {
            return x + menurun(x - 1);
        }
        else {
            return 0;
        }
    }
}
