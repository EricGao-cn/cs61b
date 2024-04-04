/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    public static String[] sort(String[] asciis) {
        String[] sorted = new String[asciis.length];
        int digits = Integer.MIN_VALUE;
        for (int i = 0; i < asciis.length; i++) {
            sorted[i] = asciis[i];
            digits = digits > asciis[i].length() ? digits : asciis[i].length();
        }
        for (int i = digits - 1; i >= 0; i--) {
            sortHelperLSD(sorted, i);
        }
        return sorted;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        // Optional LSD helper method for required LSD radix sort
        String[] newList = new String[asciis.length];
        int[] counts = new int[260];
        int shortWords = 0;

        for (String item: asciis) {
            if (item.length() > index) {
                counts[(int) item.charAt(index)]++;
            } else {
                shortWords++;
            }
        }
        counts[0] += shortWords;
        for (int i = 1; i < 128; i++) {
            counts[i] += counts[i-1];
        }
        for (int i = asciis.length - 1; i >= 0; i--) {
            String item = asciis[i];
            int changePos;
            if (item.length() > index) {
                changePos = (int) item.charAt(index);
            } else {
                changePos = 0;
            }
            counts[changePos]--;
            int pos = counts[changePos];
            newList[pos] = item;
        }
        System.arraycopy(newList, 0, asciis, 0, newList.length);
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] names = new String[4];
        names[0] = "dig";
        names[1] = "cats";
        names[2] = "dog";
        names[3] = "dogs";
        for (String name: names) {
            System.out.print(name + " ");
        }

        String[] sortedNames = sort(names);
        for (String name: sortedNames) {
            System.out.print(name + " ");
        }
    }
}
