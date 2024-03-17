import org.junit.Test;
import static org.junit.Assert.*;
public class TestArrayDequeGold {
    private String getErrorInfo(int actionNums, String[] actions) {
        String errorInfo = "";
        for(int j = 0; j < actionNums; j++) {
            errorInfo = errorInfo + actions[j] + "\n";
        }
        return errorInfo;
    }
    @Test
    public void testArrayDeque() {
        StudentArrayDeque<Integer> student = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> solution = new ArrayDequeSolution<>();
        String[] actions = new String[10];

        for (int i = 0; i < 10; i++) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.25) {
                Integer num = StdRandom.uniform(10);
                actions[i] = "addFirst(" + num + ")";
                student.addFirst(num);
                solution.addFirst(num);

                String errorInfo = getErrorInfo(i, actions);
                assertEquals(errorInfo, solution.size(), student.size());
                assertEquals(errorInfo, solution.get(0), student.get(0));

            } else if (numberBetweenZeroAndOne < 0.5) {
                Integer num = StdRandom.uniform(10);
                actions[i] = "addLast(" + num + ")";
                student.addLast(num);
                solution.addLast(num);

                String errorInfo = getErrorInfo(i, actions);
                assertEquals(errorInfo, solution.size(), student.size());
                assertEquals(errorInfo, solution.get(solution.size() - 1), student.get(solution.size() - 1));

            } else if (numberBetweenZeroAndOne < 0.75) {
                if (solution.isEmpty()) {
                    continue;
                }
                actions[i] = "removeFirst()";
                Integer stu = student.removeFirst();
                Integer sol = solution.removeFirst();

                String errorInfo = getErrorInfo(i, actions);
                assertEquals(errorInfo, solution.size(), student.size());
                assertEquals(errorInfo, sol, stu);
                if (!solution.isEmpty()) {
                    assertEquals(errorInfo, solution.get(0), student.get(0));
                }

            } else {
                if (solution.isEmpty()) {
                    continue;
                }
                actions[i] = "removeLast()";
                Integer stu = student.removeLast();
                Integer sol = solution.removeLast();

                String errorInfo = getErrorInfo(i, actions);
                assertEquals(errorInfo, solution.size(), student.size());
                assertEquals(errorInfo, sol, stu);
                if (!solution.isEmpty()) {
                    assertEquals(errorInfo, solution.get(solution.size() - 1), student.get(student.size() - 1));
                }

            }

        }
    }

    // @source
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests(TestArrayDequeGold.class);
    }
}
