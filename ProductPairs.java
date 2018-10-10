/**
 * Challenge ::: A.B.C.D.E.... = N
 * Find number of possible pairs of A,B,C ... such that A.B.C... = N 
 * where N is the number entered provided A,B,C ... ,N belongs to set of 
 * natural numbers(1,2,3....) example ::: 
 * input : 2 4 //product of any 2 variables = 4 , ie A.B=4 
 * output : 3 //(1,4), (2,2), (4,1) 
 * simple approach to the problem is welcome 
 * happy coding & best of luck 👍 ☺ 
 * 
 * edit ::: abcde...k = n , (n+k-1)Ck , solutions for abcde..k belonging to natural no.
 */
package productpairs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProductPairs {
    
    private final int number;
    private final List<Tuple> pairs;

    public static void main(String[] args) {
        ProductPairs pp = new ProductPairs(input());
        pp.solve();
        System.out.println(pp.pairs);
    }

    public ProductPairs(int number) {
        this.number = number;
        this.pairs = new ArrayList<>();
    }
    
    public void solve() {
        int squareRoot = (int) Math.sqrt(number);
        this.pairs.add(new Tuple(1, number));
        if (!isPrime(number)) {
            for (int i = 2; i < squareRoot; i++) {
                if (number % i == 0) {
                    this.pairs.add(new Tuple(i, number / i));
                    this.pairs.add(new Tuple(number / i, i));
                }
            }
            if (Math.pow((int) squareRoot, 2) == number) {
                this.pairs.add(new Tuple(squareRoot, squareRoot));
            }
        }
        if (number != 1) {this.pairs.add(new Tuple(number, 1));}
        Collections.sort(pairs, Tuple::compareTo);
    }
    
    private static boolean isPrime(int number) {
        //in this special case for solve() this method returns true if number == 1
        if (number == 2) return true;
        if (number < 1 || number % 2 == 0) return false;
        for(int i = 3; i * i <= number; i += 2) {
            if(number % i == 0)
                return false;
        }
        return true;
    }
    
    private static int input() {
        int number = 0;
        while (number < 1) {
            System.out.print("Enter the number you looking for:\n");
            try {
                String nextLine = new Scanner(System.in).nextLine();
                number = Integer.parseInt(nextLine);
            } catch(NumberFormatException e) {
                System.err.println(e);
                System.out.println("You have to enter a positive whole number!");
            }
        }
        return number;
    }

    private static class Tuple implements Comparable<Tuple> {
        
        private final int left;
        private final int right;

        public Tuple(int left, int right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return String.format("(%d, %d)", left, right);
        }

        @Override
        public int compareTo(Tuple other) {
            return this.left > other.left ? 1 : 
                    this.left < other.left ? -1 :
                    this.right > other.right ? 1 : 
                    this.right < other.right ? -1 :
                    0;
        }
    }
}