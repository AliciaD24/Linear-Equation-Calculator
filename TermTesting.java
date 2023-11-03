package DesigningClassesProject;

class TermTesting {
    
    static void testValueOf(){
        System.out.println(Term.valueOf("x")); // x
        System.out.println(Term.valueOf("8")); // 8
        System.out.println(Term.valueOf("-l")); // -1l
        System.out.println(Term.valueOf("9.36w")); // 9 9/25w
        System.out.println(Term.valueOf("-18.12s")); // -18 3/25s
        System.out.println(Term.valueOf("-4 8/7t")); // -5 1/7t
        System.out.println(Term.valueOf("2 34/73d")); // 2 34/73d
        System.out.println(Term.valueOf("-2345c")); // -2345c
        System.out.println(Term.valueOf("-2/3")); // -2/3
        System.out.println(Term.valueOf("4/5z")); // 4/5z
    }

    static void testAdd(){
        System.out.println(new Term(9/3, "x").add(new Term(2, "x"))); // 5x
        System.out.println(new Term(121/4, "d").add(new Term(3/4, "d"))); // 31d
        System.out.println(new Term(-62/11, "f").add(new Term(49/2, "f"))); // 18 19/22f
        System.out.println(new Term(-8/7, "t").add(new Term(8/7, "t"))); // t
        System.out.println(new Term(32, "l").add(new Term(-24, "l"))); // 8l
        System.out.println(new Term(4, "y").add(new Term(18/2, "y"))); // 13y
        System.out.println(new Term(-9/3, "z").add(new Term(9/19, "z"))); // -2 10/19z
        System.out.println(new Term(2973, "q").add(new Term(-12/13, "q"))); // 2972 1/13q
        System.out.println(new Term(-87329, "w").add(new Term(-83, "w"))); // -87412w
        System.out.println(new Term(-93/5, "p").add(new Term(56/3, "p"))); // 1/15p
    }
    static void testSubtract(){
        System.out.println(new Term(9/3, "x").subtract(new Term(2, "x"))); // x
        System.out.println(new Term(121/4, "d").subtract(new Term(3/4, "d"))); // 29 1/2d
        System.out.println(new Term(-62/11, "f").subtract(new Term(49/2, "f"))); // -30 3/22f
        System.out.println(new Term(-8/7, "t").subtract(new Term(-8/7, "t"))); // t
        System.out.println(new Term(32, "l").subtract(new Term(-24, "l"))); // 56l
        System.out.println(new Term(4, "y").subtract(new Term(18/2, "y"))); // -5y
        System.out.println(new Term(-9/3, "z").subtract(new Term(9/19, "z"))); // -3 9/19z
        System.out.println(new Term(2973, "q").subtract(new Term(-12/13, "q"))); // 2973 12/13q
        System.out.println(new Term(-87329, "w").subtract(new Term(-83, "w"))); // -87246w
        System.out.println(new Term(-93/5, "p").subtract(new Term(56/3, "p"))); // -37 4/15p
    }
    
    public static void main(String[] args) {
        //testValueOf();
        testAdd();
        testSubtract();
    }

}
