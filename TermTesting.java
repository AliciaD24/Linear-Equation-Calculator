package DesigningClassesProject;

class TermTesting {
    
    /**
     * Tests the Term.valueOf() and Term.toString() methods
     */
    static void testValueOfAndToString(){
        System.out.println(Term.valueOf("x")); // x
        System.out.println(Term.valueOf("8")); // 8
        System.out.println(Term.valueOf("-l")); // -l
        System.out.println(Term.valueOf("9.36w")); // 9 9/25w
        System.out.println(Term.valueOf("-18.12s")); // -18 3/25s
        System.out.println(Term.valueOf("-4 8/7t")); // -5 1/7t
        System.out.println(Term.valueOf("2 34/73d")); // 2 34/73d
        System.out.println(Term.valueOf("-2345c")); // -2345c
        System.out.println(Term.valueOf("-2/3")); // -2/3
        System.out.println(Term.valueOf("4/5z")); // 4/5z
    }

    /**
     * Tests the Term.add() method
     */
    static void testAdd(){
        System.out.println(new Term(new Fraction(2, 3, 3), "x").add(new Term(new Fraction(2), "x"))); // 5x
        System.out.println(new Term(new Fraction(121, 4), "d").add(new Term(new Fraction(3, 4), "d"))); // 31d
        System.out.println(new Term(new Fraction(-62, 11), "f").add(new Term(new Fraction(49, 2), "f"))); // 18 19/22f
        System.out.println(new Term(new Fraction(-8, 7), "t").add(new Term(new Fraction(8, 7), "t"))); // t
        System.out.println(new Term(new Fraction(32), "l").add(new Term(new Fraction(-24), "l"))); // 8l
        System.out.println(new Term(new Fraction(4), "y").add(new Term(new Fraction(18, 2), "y"))); // 13y
        System.out.println(new Term(new Fraction(9, -3), "z").add(new Term(new Fraction(9, 19), "z"))); // -2 10/19z
        System.out.println(new Term(new Fraction(2973), "q").add(new Term(new Fraction(-12, 13), "q"))); // 2972 1/13q
        System.out.println(new Term(new Fraction(-87329), "w").add(new Term(new Fraction(-83), "w"))); // -87412w
        System.out.println(new Term(new Fraction(93, -5), "p").add(new Term(new Fraction(-56, -3), "p"))); // 1/15p
    }

    /**
     * Tests the Term.subtract() method
     */
    static void testSubtract(){
        System.out.println(new Term(new Fraction(2, 3, 3), "x").subtract(new Term(new Fraction(2), "x"))); // x
        System.out.println(new Term(new Fraction(121, 4), "d").subtract(new Term(new Fraction(3, 4), "d"))); // 29 1/2d
        System.out.println(new Term(new Fraction(-62, 11), "f").subtract(new Term(new Fraction(49, 2), "f"))); // -30 3/22f
        System.out.println(new Term(new Fraction(-8, 7), "t").subtract(new Term(new Fraction(8, -7), "t"))); // t
        System.out.println(new Term(new Fraction(32), "l").subtract(new Term(new Fraction(-24), "l"))); // 56l
        System.out.println(new Term(new Fraction(4), "y").subtract(new Term(new Fraction(18, 2), "y"))); // -5y
        System.out.println(new Term(new Fraction(9, -3), "z").subtract(new Term(new Fraction(9, 19), "z"))); // -3 9/19z
        System.out.println(new Term(new Fraction(2973), "q").subtract(new Term(new Fraction(-12, 13), "q"))); // 2973 12/13q
        System.out.println(new Term(new Fraction(-87329), "w").subtract(new Term(new Fraction(-83), "w"))); // -87246w
        System.out.println(new Term(new Fraction(93, -5), "p").subtract(new Term(new Fraction(-56, -3), "p"))); // -37 4/15p
        System.out.println(new Term(new Fraction(9, 3), "H").subtract(new Term(new Fraction(3), "H"))); // 0
    }
    
    /**
     * Tests the Term.multiply() method
     */
    static void testMultiply(){
        System.out.println(new Term(new Fraction(2, 3, 3), "x").multiply(new Term(new Fraction(2), ""))); // 6x
        System.out.println(new Term(new Fraction(121, 4), "").multiply(new Term(new Fraction(3, 4), "d"))); // 22 11/16d
        System.out.println(new Term(new Fraction(-62, 11), "").multiply(new Term(new Fraction(49, 2), "f"))); // -138 1/11f
        System.out.println(new Term(new Fraction(-8, 7), "t").multiply(new Term(new Fraction(8, 7), ""))); // -1 15/49t
        System.out.println(new Term(new Fraction(32), "l").multiply(new Term(new Fraction(-24), ""))); // -768l
        System.out.println(new Term(new Fraction(4), "y").multiply(new Term(new Fraction(18, 2), ""))); // 36y
        System.out.println(new Term(new Fraction(9, -3), "z").multiply(new Term(new Fraction(9, 19), ""))); // -1 8/19z
        System.out.println(new Term(new Fraction(2973), "").multiply(new Term(new Fraction(-12, 13), "q"))); // -2744 4/13q
        System.out.println(new Term(new Fraction(-873), "").multiply(new Term(new Fraction(-83), "w"))); // 72459w
        System.out.println(new Term(new Fraction(93, -5), "").multiply(new Term(new Fraction(56, -3), "p"))); // 347 1/5p
    }

    /**
     * Tests the Term.divide() method
     */
    static void testDivide(){
        System.out.println(new Term(new Fraction(2, 3, 3), "x").divide(new Term(new Fraction(2), ""))); // 1 1/2x
        System.out.println(new Term(new Fraction(121, 4), "").divide(new Term(new Fraction(3, 4), "d"))); // 40 1/3d
        System.out.println(new Term(new Fraction(-62, 11), "").divide(new Term(new Fraction(49, 2), "f"))); // -124/539f
        System.out.println(new Term(new Fraction(-8, 7), "t").divide(new Term(new Fraction(8, 7), ""))); // -t
        System.out.println(new Term(new Fraction(32), "l").divide(new Term(new Fraction(-24), ""))); // -1 1/3l
        System.out.println(new Term(new Fraction(4), "y").divide(new Term(new Fraction(18, 2), ""))); // 4/9y
        System.out.println(new Term(new Fraction(9, -3), "z").divide(new Term(new Fraction(9, 19), ""))); // -6 1/3z
        System.out.println(new Term(new Fraction(2973), "").divide(new Term(new Fraction(-12, 13), "q"))); // -3220 3/4q
        System.out.println(new Term(new Fraction(-873), "").divide(new Term(new Fraction(-83), "w"))); // 10 43/83w
        System.out.println(new Term(new Fraction(93, -5), "").divide(new Term(new Fraction(-56, -3), "p"))); // -279/280p
    }

    public static void main(String[] args) {
        testValueOfAndToString();
        testAdd();
        testSubtract();
        testMultiply();
        testDivide();
    }

}
