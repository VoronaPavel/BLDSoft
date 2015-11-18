import java.util.Arrays;

public class Main {

    static int steps(int startPoint, int endPoint, int [] excludedDigits) {
        int closestJump = closestJump(endPoint, excludedDigits);
        int stepsByClicking = Math.abs(endPoint - startPoint);
        int stepsByJumpAndClicking = 1 + Math.abs(endPoint - closestJump);

        return stepsByJumpAndClicking < stepsByClicking ? stepsByJumpAndClicking : stepsByClicking;
    }

    static int closestJump(int endPoint, int[] excludedDigits) {
        if (!containsExcluded(endPoint, excludedDigits)) return endPoint;
        int leftJump = searchLeft(endPoint - 1, excludedDigits);
        int rightJump = searchRight(endPoint + 1, excludedDigits);
        int leftDistance = Math.abs(leftJump - endPoint);
        int rightDistance = Math.abs(rightJump - endPoint);

        return leftDistance < rightDistance ? leftJump : rightJump;
    }

    static int searchLeft(int currentPoint, int [] excludedDigits) {
        if (!containsExcluded(currentPoint, excludedDigits))
            return currentPoint;
        return searchLeft(currentPoint - 1, excludedDigits);
    }

    static int searchRight(int currentPoint, int [] excludedDigits) {
        if (!containsExcluded(currentPoint, excludedDigits))
            return currentPoint;
        return searchRight(currentPoint + 1, excludedDigits);
    }

    static boolean containsExcluded(int number, int [] excludedDigits) {
        while (number > 0) {
            final int currentNumber = number;
            if (Arrays.stream(excludedDigits).anyMatch(digit -> currentNumber % 10 == digit))
                return true;
            number /= 10;
        }
        return false;
    }

    public static void main(String[] args) {
        int [] excluded = {9};
        int solve = steps(100, 95, excluded);
        System.out.println("steps = " + solve);
    }
}
