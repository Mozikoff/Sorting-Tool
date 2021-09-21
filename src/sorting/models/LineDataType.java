package sorting.models;

import java.util.*;

public class LineDataType extends BaseDataType implements Statistical<String> {
    private final List<String> lst = new ArrayList<>();

    public List<String> getList() {
        return lst;
    }

    public boolean hasNext() {
        return scanner.hasNextLine();
    }

    public void add() {
        lst.add(scanner.nextLine());
    }

    public int getTotal() {
        return lst.size();
    };

    public String getMax() {
        return lst.stream().max(Comparator.comparingInt(String::length)).orElse("");
    };

    public long getCount(String elem) {
        return lst.stream().filter(a -> a.equals(elem)).count();
    }

    public int getPercentage(String elem) {
        double result = (double) getCount(elem) / lst.size();
        return (int) (result * 100);
    }

    public void printTotal() {
        writer.println("Total lines: " + getTotal() + ".");
        writer.flush();
    }

    public void printLargest() {
        String max = getMax();
        writer.println("The longest line: \n" + max + "\n(" +
                getCount(max) + " time(s), " + getPercentage(max) + "%).");
        writer.flush();
    }

    public void printSorted(SortingType sortingType) {
        if (sortingType.equals(SortingType.BYCOUNT)) {
            printSortedByCount();
        } else {
            printSortedNatural();
        }
    }

    private void printSortedNatural() {
        writer.println("Sorted data:");
        List<String> copy = new ArrayList<>(lst);
        Collections.sort(copy);
        for (String elem : copy) {
            writer.println(elem);
        }
        writer.flush();
    }

    private void printSortedByCount() {
        List<String> copy = new ArrayList<>(lst);
        Collections.sort(copy, getByCountComparator());
        Set<String> set = new LinkedHashSet<>(copy);
        for (String elem : set) {
            writer.println(elem + ": " + getCount(elem) + " time(s), " + getPercentage(elem) + "%");
        }
        writer.flush();
    }

    private Comparator<String> getByCountComparator() {
        return new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int result = Long.compare(getCount(o1), getCount(o2));
                return result == 0 ? o1.compareTo(o2) : result;
            }
        };
    }
}
