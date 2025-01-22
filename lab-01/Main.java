import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HornerPolynomial.main(args);
    }
}

class HornerPolynomial {
    // Метод Горнера для работы с double
    static double horner(double[] poly, int n, double x) {
        double result = poly[0];

        for (int i = 1; i < n; i++) {
            result = result * x + poly[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of terms in the polynomial:");
        int n = sc.nextInt();

        double[] poly = new double[n];
        System.out.println("Enter the coefficients of the polynomial starting from the highest degree:");
        for (int i = 0; i < n; i++) {
            poly[i] = sc.nextDouble();
        }

        System.out.println("Enter the value of x:");
        double x = sc.nextDouble();

        System.out.println("Value of the polynomial is: " + horner(poly, n, x));

        sc.close();
    }
}
