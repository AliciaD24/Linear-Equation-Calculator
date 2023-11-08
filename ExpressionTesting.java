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
        System.out.println(Expression.valueOf("15")); // 15
    }

    static void testSimplifyFully(){
        System.out.println(Expression.simplifyFully("-2x + 5x - 7 + 8 * 4x - 8x + 9")); // 27x + 2
        System.out.println(Expression.simplifyFully("3y * 4 - 12y + 36 / 6 * 2 - 4y * 3 + 18y")); // 6y + 12
        System.out.println(Expression.simplifyFully("8 * 8t - 12 + 34/2t / 2 + t - 8 - 6/2t")); // 70 1/2t - 20
        System.out.println(Expression.simplifyFully("4/3 - 8/2 * 6/9 / 3/7W - 8W * 2")); // -22 2/9W + 1 1/3
        System.out.println(Expression.simplifyFully("12.3 * 4p - 12/2p * 8 / 3/2 + 2.4")); // 17 1/5p + 2 2/5
        System.out.println(Expression.simplifyFully("43l - 18 * 12l - l + 2.4l * 2 / 3")); // -172 2/5l
        System.out.println(Expression.simplifyFully("9/3 + 3 * 4/8 * 12 - 19 + 93 - 2 / 5")); // 94 3/5
        System.out.println(Expression.simplifyFully("63g - 12g + 4.8g - 2/3g - 18/6g + 9/4g")); // 54 23/60g
        System.out.println(Expression.simplifyFully("4.8j * 3/4 + 12.9 - 0j + 18 - 12.4j - 18/4")); // -8 4/5j + 26 2/5
        System.out.println(Expression.simplifyFully("7 + 1/4B * 0.45 + 3/2B - 5/7B * 2.31")); // -3/80B + 7
        System.out.println(Expression.simplifyFully("7 + 1/4B * 0.45 + 3/2B - 5/7B * 2.31 + 3/80B - 7")); // 0
    }
    
    public static void main(String[] args) {
        testValueOfAndToString();
        testSimplifyFully();
    }
}
