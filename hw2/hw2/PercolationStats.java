package hw2;
import edu.princeton.cs.introcs.StdRandom;

import java.security.spec.PSSParameterSpec;

public class PercolationStats {
    private int[] result;
    private int times;

    public PercolationStats(int N, int T, PercolationFactory pf){
        if (N * T <= 0) {
            throw new IllegalArgumentException();
        }
        result = new int[T];
        times = T;
        for (int i = 0; i < T; i++) {
            Percolation perc = pf.make(N);
            while (!perc.percolates()) {
                int randomPos = StdRandom.uniform(N * N);
                perc.open(randomPos / N, randomPos % N);
            }
            result[i] = perc.numberOfOpenSites();
        }

    }

    public double mean() {
        double sum = 0;
        for (int i: result) {
            sum += i;
        }
        return sum / times;
    }

    public double stddev() {
        double sigmaSquare = 0;
        double mean = mean();
        for (int i: result) {
            sigmaSquare += (i - mean) * (i - mean);
        }
        sigmaSquare /= (times - 1);
        return Math.sqrt(sigmaSquare);
    }

    public double confidenceLow() {
        double mu = mean();
        double sigma = stddev();
        return (mu - 1.96 * sigma / Math.sqrt(times));
    }

    public double confidenceHigh() {
        double mu = mean();
        double sigma = stddev();
        return (mu + 1.96 * sigma / Math.sqrt(times));
    }

//    public static void main(String[] args) {
//        PercolationStats perc = new PercolationStats(20, 100, new PercolationFactory());
//        System.out.println(perc.mean());
//    }
}
