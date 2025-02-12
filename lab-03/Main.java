public class Main {
	public static void main(String[] args) {
		double alpha = 0.5;
		double beta = 0.7;

		double[][] A = {
				{ 5.18 + alpha, 1.12, 0.95, 1.32, 0.83 },
				{ 1.12, 4.28 - alpha, 2.12, 0.57, 0.91 },
				{ 0.95, 2.12, 6.13 + alpha, 1.29, 1.57 },
				{ 1.32, 0.57, 1.29, 4.57 - alpha, 1.25 },
				{ 0.83, 0.91, 1.57, 1.25, 5.21 + alpha }
		};
		double[] b = { 6.19 + beta, 3.21, 4.28 - beta, 6.25, 4.95 + beta };

		double[] solution = squareRoot(A, b);

		for (double x : solution) {
			System.out.printf("%.4f ", x);
		}
	}

	public static double[] squareRoot(double[][] A, double[] b) {
		int n = A.length;
		double[][] S = new double[n][n];
		double[] D = new double[n];

		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int k = 0; k < i; k++) {
				sum += S[k][i] * S[k][i] * D[k];
			}
			D[i] = Math.signum(A[i][i] - sum);
			S[i][i] = Math.sqrt(Math.abs(A[i][i] - sum));

			for (int j = i + 1; j < n; j++) {
				sum = 0;
				for (int k = 0; k < i; k++) {
					sum += S[k][i] * S[k][j] * D[k];
				}
				S[i][j] = (A[i][j] - sum) / (S[i][i] * D[i]);
			}
		}
		double[] y = new double[n];
		for (int i = 0; i < n; i++) {
			double sum = 0;
			for (int k = 0; k < i; k++) {
				sum += S[k][i] * y[k] * D[k];
			}
			y[i] = (b[i] - sum) / (S[i][i] * D[i]);
		}
		double[] x = new double[n];
		for (int i = n - 1; i >= 0; i--) {
			double sum = 0;
			for (int k = i + 1; k < n; k++) {
				sum += S[i][k] * x[k];
			}
			x[i] = (y[i] - sum) / S[i][i];
		}
		return x;
	}
}