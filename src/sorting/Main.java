package sorting;

import java.io.*;
import java.util.*;

public class Main {
    private static final String DATA_TYPE = "-dataType";
    private static final String SORT_TYPE = "-sortingType";
    private static final String INPUT_FILE = "-inputFile";
    private static final String OUTPUT_FILE = "-outputFile";

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

    private static abstract class BaseDataType {
        protected PrintWriter writer;
        protected Scanner scanner;

        void setWriter(PrintWriter writer) {
            this.writer = writer;
        }

        void setScanner(Scanner scanner) {
            this.scanner = scanner;
        }

        void closeResources() {
            scanner.close();
        }
    }

    private static class LongDataType extends BaseDataType implements Statistical<Long> {
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

    public static class LineDataType extends BaseDataType implements Statistical<String> {
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

    public static class WordDataType extends BaseDataType implements Statistical<String> {
        private final List<String> lst = new ArrayList<>();

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
            writer.println("Total words: " + getTotal() + ".");
            writer.flush();
        }

        public void printLargest() {
            String max = getMax();
            writer.println("The longest word: " + max + " (" +
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

    public static class DataTypeFactory {
        public static Statistical getDataType(DataType d, String inputFileName, String outputFileName) {
            PrintWriter writer = new PrintWriter(System.out);
            Scanner scanner = new Scanner(System.in);
            try {
                if (Objects.nonNull(inputFileName)) {
                    scanner = new Scanner(new File(inputFileName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (Objects.nonNull(outputFileName)) {
                    writer = new PrintWriter(new File(outputFileName));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (d) {
                case LONG:
                    LongDataType longDataType = new LongDataType();
                    longDataType.setScanner(scanner);
                    longDataType.setWriter(writer);
                    return longDataType;
                case LINE:
                    LineDataType lineDataType = new LineDataType();
                    lineDataType.setScanner(scanner);
                    lineDataType.setWriter(writer);
                    return lineDataType;
                default:
                    WordDataType wordDataType = new WordDataType();
                    wordDataType.setScanner(scanner);
                    wordDataType.setWriter(writer);
                    return wordDataType;
            }
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
        String inputFileName = null;
        String outputFileName = null;
        Statistical stat;
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
                case INPUT_FILE:
                    inputFileName = args[++i];
                    break;
                case OUTPUT_FILE:
                    outputFileName = args[++i];
                    break;
                default:
                    System.out.println("\"" + args[i] + "\" is not a valid parameter. It will be skipped.");
            }
        }

        stat = DataTypeFactory.getDataType(dataType, inputFileName, outputFileName);
        while (stat.hasNext()) {
            stat.add();
        }
        stat.printTotal();
        stat.printSorted(sortingType);
        BaseDataType baseDataType = (BaseDataType) stat;
        baseDataType.closeResources();
    }
}
