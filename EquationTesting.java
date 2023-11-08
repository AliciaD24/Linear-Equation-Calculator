package DesigningClassesProject;

class EquationTesting {
    static void testValueOfAndToString(){
        System.out.println(Equation.valueOf("-2x + 5x - 7 = 4 + 3x")); // 3x - 7 = 3x + 4
        System.out.println(Equation.valueOf("2T - 5 = 3T + 7")); // 2T - 5 = 3T + 7
        System.out.println(Equation.valueOf("2l = 0")); // 2l = 0
        System.out.println(Equation.valueOf("h * 3 - 4/5 + 18h = 2h + 4 - 18 * 2 / 8")); // 21h - 4/5 = 2h - 1/2
        System.out.println(Equation.valueOf("2.9 * 3.4n - 18 + 2n = 3n - 2n")); // 11 43/50n - 18 = n
        System.out.println(Equation.valueOf("15 = 3m * 2 - 12 * 3.4 / 4m + m - 2/4m")); // 15 = -3 7/10m
        System.out.println(Equation.valueOf("14 + 0.3b - 14/6 * 4 / 2/33b = -24.6b * 3 / 7 + 6/7b")); // -153 7/10b + 14 = -9 24/35b
        System.out.println(Equation.valueOf("12g - 5 + 7g * 11 = 18.2g")); // 89g - 5 = 18 1/5g
        System.out.println(Equation.valueOf("27.3J - 3.4J - 8/12J + 63J - 4.2J + 8J - 2/3J = 3J + 2")); // 89 11/30J = 3J + 2
        System.out.println(Equation.valueOf("2.45 - 3.8 / 2/3 - 4 = 63 - 49/2 + 12 * 3 / 2 - 2.7 + k")); // -7 1/4 = k + 53 4/5
        System.out.println(Equation.valueOf("2 / x = 4")); //FIX
    }

    static void testSolve(){
        System.out.println(Equation.solve(Equation.valueOf("-2x + 5x - 7 = 4 + 4x"))); // x = -11
        System.out.println(Equation.solve(Equation.valueOf("2T - 5 = 3T + 7"))); // T = -12
        System.out.println(Equation.solve(Equation.valueOf("2l = 0"))); // l = 0
        System.out.println(Equation.solve(Equation.valueOf("h * 3 - 4/5 + 18h * 0 = 2h + 4 - 18 * 2 / 8"))); // h = 3/10
        System.out.println(Equation.solve(Equation.valueOf("2.9 * 3.4n - 18 + 2n = 3n - 2n"))); // n = 1 119/181
        System.out.println(Equation.solve(Equation.valueOf("15 = 3m * 2 - 12 * 3.4 / 4m + m - 2/4m"))); // m = -4 2/37
        System.out.println(Equation.solve(Equation.valueOf("14 + 0.3b - 14/6 * 4 / 2/33b = -24.6b * 3 / 7 + 6/7b"))); // b = 980/10081
        System.out.println(Equation.solve(Equation.valueOf("12g - 5 + 7g * 11 = 18.2g"))); // g = 25/354
        System.out.println(Equation.solve(Equation.valueOf("27.3J - 3.4J - 8/12J + 63J - 4.2J + 8J - 2/3J = 3J + 2"))); // J = 60/2591
        System.out.println(Equation.solve(Equation.valueOf("2.45 - 3.8 / 2/3 - 4 = 63 - 49/2 + 12 * 3 / 2 - 2.7 + k"))); // k = -61 1/20
        System.out.println(Equation.solve(Equation.valueOf("2 / x = 4"))); //FIX
    }

    public static void main(String[] args) {
        testValueOfAndToString();
        testSolve();
    }
}
