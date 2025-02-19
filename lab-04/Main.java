import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter kAlpha (0-4): ");
		int kAlpha = scanner.nextInt();
		System.out.print("Enter kBeta (0-5): ");
		int kBeta = scanner.nextInt();
		scanner.close();

		double epsilon = 1e-5;
		double alpha = 0.2 * kAlpha;
		double beta = 0.2 * kBeta;
		double[][] A = {
				{ 24.21 + alpha, 2.42, 3.85 },
				{ 2.31, 31.49, 1.52 },
				{ 3.49, 4.85, 28.72 + alpha }
		};
		double[] b = { 30.24, 40.95 - beta, 42.81 };

		int iterations = 0;
		double[] solution = solveSeidel(A, b, epsilon, iterations);
		System.out.println("Solution for alpha=" + alpha + ", beta=" + beta + ": " + Arrays.toString(solution));
	}

	public static double[] solveSeidel(double[][] A, double[] b, double epsilon, int iterations) {
		int n = b.length;
		double[] x = new double[n];
		double[] xNew = new double[n];
		Arrays.fill(x, 0);

		while (true) {
			for (int i = 0; i < n; i++) {
				double sum1 = 0, sum2 = 0;
				for (int j = 0; j < i; j++)
					sum1 += A[i][j] * xNew[j];
				for (int j = i + 1; j < n; j++)
					sum2 += A[i][j] * x[j];
				xNew[i] = (b[i] - sum1 - sum2) / A[i][i];
			}

			iterations++;
			if (converged(x, xNew, epsilon))
				break;
			System.arraycopy(xNew, 0, x, 0, n);
		}

		System.out.println("Iterations: " + iterations);
		return xNew;
	}

	public static boolean converged(double[] x, double[] xNew, double epsilon) {
		for (int i = 0; i < x.length; i++) {
			if (Math.abs(xNew[i] - x[i]) > epsilon) {
				return false;
			}
		}
		return true;
	}
}