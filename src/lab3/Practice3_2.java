package lab3;

public class Practice3_2 {
    public static void main(String[] args) {
        printResults(1.0, 2); // ln(t)
        printResults(2.0, 4); // сума
        printResults(-1.0, 1); // некоректно: ln(-1)
    }

    static void printResults(double t, int i) {
        System.out.print("t:" + t + " i:" + i + " result:");
        try {
            System.out.println(x(t, i));
        } catch (IllegalArgumentException e) {
            System.out.println("EXCEPTION! " + e.getMessage());
        }
    }

    public static double x(double t, int i) {
        if (i < 1) throw new IllegalArgumentException("i: " + i);
        if (i == 1 || i == 2) {
            if (t <= 0) throw new IllegalArgumentException("t <= 0");
            return Math.log(t);
        } else {
            double sum = 0.0;
            for (int k = 1; k <= i; k++) sum += Math.sin(t) / k;
            return sum;
        }
    }
}
