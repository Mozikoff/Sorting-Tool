package sorting.models;

import java.util.*;

public class LongDataType extends BaseDataType implements Statistical<Long> {
    private final List<Long> lst = new ArrayList<>();

    public List<Long> getList() {
        return lst;
    }

    public boolean hasNext() {
        return scanner.hasNext();
    }

    public void add() {
        try {
            lst.add(scanner.nextLong());
        } catch (InputMismatchException e) {
            writer.println("\"" + scanner.next() + "\" is not a long. It will be skipped.");
        }
        writer.flush();
    }

    public int getTotal() {
        return lst.size();
    };

    public Long getMax() {
        return Collections.max(lst);
    };

    public long getCount(Long elem) {
        return lst.stream().filter(a -> a.equals(elem)).count();
    }

    public int getPercentage(Long elem) {
        double result = (double) getCount(elem) / lst.size();
        return (int) (result * 100);
    }

    public void printTotal() {
        writer.println("Total numbers: " + getTotal() + ".");
        writer.flush();
    }

    public void printLargest() {
        Long max = getMax();
        writer.println("The greatest number: " + max + " (" +
                getCount(max) + " time(s), " + getPercentage(max) + "%).");
        writer.flush();
    }

    public void printSorted(SortingType sortingType) {
        //TODO: same for all types
        if (sortingType.equals(SortingType.BYCOUNT)) {
            printSortedByCount();
        } else {
            printSortedNatural();
        }
    }

    private void printSortedNatural() {
        writer.print("Sorted data:");
        List<Long> copy = new ArrayList<>(lst);
        Collections.sort(copy);
        for (Long elem : copy) {
            writer.print(" " + elem);
        }
        writer.flush();
    }

    private void printSortedByCount() {
        List<Long> copy = new ArrayList<>(lst);
        Collections.sort(copy, getByCountComparator());
        Set<Long> set = new LinkedHashSet<>(copy);
        for (Long elem : set) {
            writer.println(elem + ": " + getCount(elem) + " time(s), " + getPercentage(elem) + "%");
        }
        writer.flush();
    }

    private Comparator<Long> getByCountComparator() {
        return new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                int result = Long.compare(getCount(o1), getCount(o2));
                return result == 0 ? Long.compare(o1, o2) : result;
            }
        };
    }
}
