package lab3;

public class Practice3_1 {
    public static void main(String[] args) {
        printResults(3, 2.0, 4.0);
        printResults(10, -1.0, 0.5);
        printResults(2, 1.0, 4.0); // некоректно: k
    }

    static void printResults(int k, double t, double s) {
        System.out.print("k:" + k + " t:" + t + " s:" + s + " result:");
        try {
            System.out.println(sumSeries2(k, t, s));
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }

    public static double sumSeries2(int k, double t, double s) {
        if (k <= 2 || k > 25) throw new IllegalArgumentException("k: " + k);
        double sum = 0.0;
        for (int i = 1; i <= k; i++) {
            double lnArg = -t * i;
            if (lnArg <= 0) throw new IllegalArgumentException("Некоректний ln аргумент при i=" + i);
            sum += Math.log(lnArg) * Math.cos(Math.sqrt(s / (i * i)));
        }
        return sum;
    }
}
