package mathGeometry.medium;

// https://leetcode.com/problems/powx-n/description/
public class PowXN_50 {
    public double myPow(double x, int n) {
        // time O(logn)
        // space O(logn)

        double res = pow(x, Math.abs(n));

        return n < 0 ? 1 / res : res;
    }

    private double pow(double x, int n) {
        if (x == 0) return 0;
        if (n == 0) return 1;

        double res = pow(x, n / 2);
        if (n % 2 == 0) {
            return res * res;
        } else {
            return res * res * x;
        }
    }
}
