import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HornerPolynomial.main(args);
    }
}

class HornerPolynomial {
    // Function that returns value of poly[0]x^(n-1) +
    // poly[1]x^(n-2) + ... + poly[n-1]
    static int horner(int poly[], int n, int x) {
        // Initialize result
        int result = poly[0];

        // Evaluate value of polynomial using Horner's method
        for (int i = 1; i < n; i++) {
            result = result * x + poly[i];
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of terms in the polynomial:");
        int n = sc.nextInt();

        int[] poly = new int[n];
        System.out.println("Enter the coefficients of the polynomial starting from the highest degree:");
        for (int i = 0; i < n; i++) {
            poly[i] = sc.nextInt();
        }

        System.out.println("Enter the value of x:");
        int x = sc.nextInt();

        System.out.println("Value of the polynomial is: " + horner(poly, n, x));

        sc.close();
    }
}
