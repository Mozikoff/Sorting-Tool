package sorting.models;

import java.util.List;

public interface Statistical<T> {
    abstract List<T> getList();

    abstract boolean hasNext();

    abstract void add();

    abstract int getTotal();

    abstract T getMax();

    abstract long getCount(T elem);

    abstract int getPercentage(T elem);

    abstract void printTotal();

    abstract void printLargest();

    abstract void printSorted(SortingType sortingType);
}
