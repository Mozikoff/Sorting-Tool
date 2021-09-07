package sorting;

import java.util.*;

public class Main {
    private static final String TYPE = "-dataType";
    private static final String SORT = "-sortIntegers";

    interface Statistical<T> {
        abstract List<T> getList();

        abstract boolean hasNext();

        abstract void add();

        abstract int getTotal();

        abstract T getMax();

        abstract int getCount();

        abstract int  getPercentage();

        abstract void printTotal();

        abstract void printLargest();
    }

    private static class LongDataType implements Statistical<Long> {
        private final List<Long> lst = new ArrayList<>();
        private final Scanner scanner = new Scanner(System.in);

        public List<Long> getList() {
            return lst;
        }

        public boolean hasNext() {
            return scanner.hasNextLong();
        }

        public void add() {
            lst.add(scanner.nextLong());
        }

        public int getTotal() {
            return lst.size();
        };

        public Long getMax() {
            return Collections.max(lst);
        };

        public int getCount() {
            Long max = getMax();
            return (int) lst.stream().filter(a -> a.equals(max)).count();
        }

        public int getPercentage() {
            double result = (double) getCount() / lst.size();
            return (int) (result * 100);
        };

        public void printTotal() {
            System.out.println("Total numbers: " + getTotal() + ".");
        }

        public void printLargest() {
            System.out.println("The greatest number: " + getMax() + " (" +
                    getCount() + " time(s), " + getPercentage() + "%).");
        }

        public void printSorted() {
            System.out.print("Sorted data: ");
            List<Long> copy = new ArrayList<>(lst);
            Collections.sort(copy);
            for (Long elem : copy) {
                System.out.print(elem + " ");
            }
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

        public int getCount() {
            String max = getMax();
            return (int) lst.stream().filter(line -> line.equals(max)).count();
        }

        public int getPercentage() {
            double result = (double) getCount() / lst.size();
            return (int) (result * 100);
        };

        public void printTotal() {
            System.out.println("Total lines: " + getTotal() + ".");
        }

        public void printLargest() {
            System.out.println("The longest line: \n" + getMax() + "\n(" +
                    getCount() + " time(s), " + getPercentage() + "%).");
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

        public int getCount() {
            String max = getMax();
            return (int) lst.stream().filter(line -> line.equals(max)).count();
        }

        public int getPercentage() {
            double result = (double) getCount() / lst.size();
            return (int) (result * 100);
        };

        public void printTotal() {
            System.out.println("Total words: " + getTotal() + ".");
        }

        public void printLargest() {
            System.out.println("The longest word: " + getMax() + " (" +
                    getCount() + " time(s), " + getPercentage() + "%).");
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

    public static void main(final String[] args) {
        DataType dataType;
        Statistical stat;
        if (args.length > 0 && Arrays.asList(args).contains(SORT)) {
            LongDataType longDataType = new LongDataType();
            while (longDataType.hasNext()) {
                longDataType.add();
            }
            longDataType.printTotal();
            longDataType.printSorted();
        }
        else {
            if (args.length > 0 && args[0].equals(TYPE)) {
                dataType = DataType.valueOf(args[1].toUpperCase());
            } else {
                dataType = DataType.WORD;
            }
            stat = DataTypeFactory.getDataType(dataType);
            while (stat.hasNext()) {
                stat.add();
            }
            stat.printTotal();
            stat.printLargest();
        }
    }
}
