package DesigningClassesProject;

class ExpressionTesting {
    static void testValueOfAndToString(){
        System.out.println(Expression.valueOf("3x + 4")); // 3x + 4
        System.out.println(Expression.valueOf("4/3y + 12/2")); // 1 1/3y + 6
        System.out.println(Expression.valueOf("18Z - 45")); // 18Z - 45
        System.out.println(Expression.valueOf("-12/3h - 3/3")); // -4h - 1
        System.out.println(Expression.valueOf("-6/2x + 9/10")); // -3t + 9/10
        System.out.println(Expression.valueOf("-18/-6C - 4/-2")); // 3C + 2
        System.out.println(Expression.valueOf("43/2i - 487/4")); // 21 1/2i - 121 3/4
        System.out.println(Expression.valueOf("34/4b + 92/100")); // 8 1/2b + 23/25
        System.out.println(Expression.valueOf("-5/65P + 4")); // -1/13P + 4
        System.out.println(Expression.valueOf("63W - -93/-3")); // 63W - 31
    }
    
    public static void main(String[] args) {
        testValueOfAndToString();
    }
}
