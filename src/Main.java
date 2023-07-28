import java.net.InterfaceAddress;
import java.util.ArrayList;
import java.util.List;

public class Main {


    public static void main(String[] args) {
        int[] daysInYear = new int[366];
        compare(1, daysInYear);
        compare(2, daysInYear);
        compare(5, daysInYear);
        compare(15, daysInYear);


    }

    public static void compare(int day, int[] daysInYear) {
        System.out.println("=== Day " + day + " ===");
        int[] startNumbers = {21, 1, 20, 23};
        int iterative = chooseHobbyIterative(startNumbers, day);
        int recursive = chooseHobbyRecursive(startNumbers, day, daysInYear);
        System.out.println("Iterative = " + iterative + " | Recursive = " + recursive);
        System.out.println();
    }

    public static int chooseHobbyRecursive(int[] startNumbers, int day, int[] daysInYear) {
        if (daysInYear[day - 1] != 0) return daysInYear[day - 1];
        if (day <= 3 && daysInYear[day] == 0) {
            daysInYear[0] = (startNumbers[3] * startNumbers[0]) % 10 + 1;
            daysInYear[1] = (daysInYear[0] * startNumbers[2]) % 10 + 1;
            daysInYear[2] = (daysInYear[1] * startNumbers[3]) % 10 + 1;
            return daysInYear[day - 1];
        } else {
            if (daysInYear[day - 2] != 0) {
                int index = day - 1; // индексы дней в массиве сдвинуты на 4
                int prev = daysInYear[index - 1]; // предыдущее значение
                int prePrePrev = daysInYear[index - 3]; // пре-пре-предыдущее значение
                daysInYear[day - 1] = (prev * prePrePrev) % 10 + 1;
            } else {
                daysInYear[day - 2] = chooseHobbyRecursive(startNumbers, day - 1, daysInYear);
                daysInYear[day - 1] = chooseHobbyRecursive(startNumbers, day, daysInYear);
            }
        }
        return daysInYear[day - 1];
    }

    public static int chooseHobbyIterative(int[] startNumbers, int day) {
        List<Integer> numbers = new ArrayList<>();

        numbers.add(startNumbers[0]);
        numbers.add(startNumbers[1]);
        numbers.add(startNumbers[2]);
        numbers.add(startNumbers[3]);

        for (int d = 0; d < day; d++) {
            int index = d + 4; // индексы дней в массиве сдвинуты на 4
            int prev = numbers.get(index - 1); // предыдущее значение
            int prePrePrev = numbers.get(index - 3); // пре-пре-предыдущее значение
            numbers.add((prev * prePrePrev) % 10 + 1);
        }

        return numbers.get(numbers.size() - 1);
    }

}