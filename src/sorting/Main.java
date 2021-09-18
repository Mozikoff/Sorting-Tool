package sorting;

import java.util.*;

public class Main {
    private static final String DATA_TYPE = "-dataType";
    private static final String SORT_TYPE = "-sortingType";

    interface Statistical<T> {
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

    private static class LongDataType implements Statistical<Long> {
        private final List<Long> lst = new ArrayList<>();
        private final Scanner scanner = new Scanner(System.in);

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
                System.out.println("\"" + scanner.next() + "\" is not a long. It will be skipped.");
            }
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
            System.out.println("Total numbers: " + getTotal() + ".");
        }

        public void printLargest() {
            Long max = getMax();
            System.out.println("The greatest number: " + max + " (" +
                    getCount(max) + " time(s), " + getPercentage(max) + "%).");
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
            System.out.print("Sorted data:");
            List<Long> copy = new ArrayList<>(lst);
            Collections.sort(copy);
            for (Long elem : copy) {
                System.out.print(" " + elem);
            }
        }

        private void printSortedByCount() {
            List<Long> copy = new ArrayList<>(lst);
            Collections.sort(copy, getByCountComparator());
            Set<Long> set = new LinkedHashSet<>(copy);
            for (Long elem : set) {
                System.out.println(elem + ": " + getCount(elem) + " time(s), " + getPercentage(elem) + "%");
            }
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

    public static class LineDataType implements Statistical<String> {
        private final List<String> lst = new ArrayList<>();
        private final Scanner scanner = new Scanner(System.in);

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
            System.out.println("Total lines: " + getTotal() + ".");
        }

        public void printLargest() {
            String max = getMax();
            System.out.println("The longest line: \n" + max + "\n(" +
                    getCount(max) + " time(s), " + getPercentage(max) + "%).");
        }

        public void printSorted(SortingType sortingType) {
            if (sortingType.equals(SortingType.BYCOUNT)) {
                printSortedByCount();
            } else {
                printSortedNatural();
            }
        }

        private void printSortedNatural() {
            System.out.println("Sorted data:");
            List<String> copy = new ArrayList<>(lst);
            Collections.sort(copy);
            for (String elem : copy) {
                System.out.println(elem);
            }
        }

        private void printSortedByCount() {
            List<String> copy = new ArrayList<>(lst);
            Collections.sort(copy, getByCountComparator());
            Set<String> set = new LinkedHashSet<>(copy);
            for (String elem : set) {
                System.out.println(elem + ": " + getCount(elem) + " time(s), " + getPercentage(elem) + "%");
            }
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

    public static class WordDataType implements Statistical<String> {
        private final List<String> lst = new ArrayList<>();
        private final Scanner scanner = new Scanner(System.in);

        public List<String> getList() {
            return lst;
        }

        public boolean hasNext() {
            return scanner.hasNext();
        }

        public void add() {
            lst.add(scanner.next());
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
        };

        public void printTotal() {
            System.out.println("Total words: " + getTotal() + ".");
        }

        public void printLargest() {
            String max = getMax();
            System.out.println("The longest word: " + max + " (" +
                    getCount(max) + " time(s), " + getPercentage(max) + "%).");
        }

        public void printSorted(SortingType sortingType) {
            if (sortingType.equals(SortingType.BYCOUNT)) {
                printSortedByCount();
            } else {
                printSortedNatural();
            }
        }

        private void printSortedNatural() {
            System.out.println("Sorted data:");
            List<String> copy = new ArrayList<>(lst);
            Collections.sort(copy);
            for (String elem : copy) {
                System.out.println(elem);
            }
        }

        private void printSortedByCount() {
            List<String> copy = new ArrayList<>(lst);
            Collections.sort(copy, getByCountComparator());
            Set<String> set = new LinkedHashSet<>(copy);
            for (String elem : set) {
                System.out.println(elem + ": " + getCount(elem) + " time(s), " + getPercentage(elem) + "%");
            }
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

    public static class DataTypeFactory {
        public static Statistical getDataType(DataType d) {
            return switch (d) {
                case LONG -> new LongDataType();
                case LINE -> new LineDataType();
                default -> new WordDataType();
            };
        }
    }

    private enum DataType {
        LONG("long"),
        WORD("word"),
        LINE("line");
        private final String type;
        DataType(String type) {
            this.type = type;
        }
    }

    private enum SortingType {
        NATURAL,
        BYCOUNT;
    }

    public static void main(final String[] args) {
        DataType dataType = DataType.WORD;
        SortingType sortingType = SortingType.NATURAL;
        Statistical stat;
        List<String> argsLst = Arrays.asList(args);

        for (String arg : argsLst) {
            switch (arg) {
                case SORT_TYPE:

            }
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case SORT_TYPE:
                    try {
                        sortingType = SortingType.valueOf(args[++i].toUpperCase());
                    } catch (Exception e) {
                        System.out.println("No sorting type defined!");
                        System.exit(1);
                    }
                    break;
                case DATA_TYPE:
                    try {
                        dataType = DataType.valueOf(args[++i].toUpperCase());
                    } catch (Exception e) {
                        System.out.println("No data type defined!");
                        System.exit(2);
                    }
                    break;
                default:
                    System.out.println("\"" + args[i] + "\" is not a valid parameter. It will be skipped.");
            }
        }

        stat = DataTypeFactory.getDataType(dataType);
        while (stat.hasNext()) {
            stat.add();
        }
        stat.printTotal();
        stat.printSorted(sortingType);
    }
}
