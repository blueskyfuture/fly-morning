package Maths;

public class TwoNumMultiply {

    public static void main(String[] args) {

        String str1 = "13";

        String str2 = "233434";



        System.out.printf("The result is : %s\n", multiply(str1, str2));

    }



    private static String multiply(String num1, String num2) {

        int m = num1.length(), n = num2.length();

        // at most (m + n) digits

        int[] res = new int[m + n];

        // start from the rightmost digit

        for (int i = m - 1; i >= 0; i--) {

            for (int j = n - 1; j >= 0; j--) {

                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');

                // the index of multiply at res

                int p1 = i + j, p2 = i + j + 1;

                // add results to res

                int sum = mul + res[p2];

                res[p2] = sum % 10;

                res[p1] += sum / 10;

            }

        }



        // in case zeros at prefix

        int i = 0;

        while (i < res.length && res[i] == 0) {

            i++;

        }

        // convert result to String

        StringBuilder sb = new StringBuilder();

        for (; i < res.length; i++) {

            sb.append(res[i]);

        }



        return sb.toString().length() == 0 ? "0" : sb.toString();

    }
}
