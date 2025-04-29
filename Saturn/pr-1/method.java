public class method {
    static void myMethodnoReturn(int x) {
        System.out.println(x % 3 == 0);
    }
    static int myMethodwithReturn(int y) {
        return (y % 3);
    }
    public static void main(String[] args) {
        int x = 7;
        myMethodnoReturn(x);
        System.out.println(myMethodwithReturn(x));
    }
}
