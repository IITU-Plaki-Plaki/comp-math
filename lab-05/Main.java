public class Main {
    static final double alpha = 1;
    static final double k = 0.6;
    static final double e = 1e-6;
    static final int maxIterations = 100;

    public static void main(String[] args) {
        double x = 0.5, y = 0.5;
        newtonMethod(x, y);
    }

    static void newtonMethod(double x, double y) {
        for (int iter = 0; iter < maxIterations; iter++) {
            double f1 = Math.tan(x * y + k) - x * x; // tan(xy + k) - x^2 = 0
            double f2 = alpha * x * x + 2 * y * y - 1; // ax^2 + 2y^2 - 1 = 0

            double df1dx = y / (Math.cos(x * y + k) * Math.cos(x * y + k)) - 2 * x; // df1/dx
            double df1dy = x / (Math.cos(x * y + k) * Math.cos(x * y + k)); // df1/dy
            double df2dx = 2 * alpha * x; // df2/dx
            double df2dy = 4 * y; // df2/dy

            double jacobian = df1dx * df2dy - df1dy * df2dx;

            if (Math.abs(jacobian) < 1e-12) {
                System.out.println("matrix is singular");
                return;
            }

            double deltaX = f1 * df2dy - f2 * df1dy;
            double deltaY = df1dx * f2 - df2dx * f1;

            double dx = deltaX / jacobian;
            double dy = deltaY / jacobian;

            x -= dx;
            y -= dy;

            System.out.printf("Iter %d:\n x = %.6f, y = %.6f\n", iter + 1, x, y);

            if (Math.abs(dx) < e && Math.abs(dy) < e) {
                System.out.printf("Answer is x = %.6f, y = %.6f\n", x, y);
                return;
            }
        }
        System.out.println("Maximum iterations reached");
    }
}
