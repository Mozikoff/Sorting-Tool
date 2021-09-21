package sorting;

import sorting.models.*;
import sorting.utils.DataTypeFactory;


public class Main {
    private static final String DATA_TYPE = "-dataType";
    private static final String SORT_TYPE = "-sortingType";
    private static final String INPUT_FILE = "-inputFile";
    private static final String OUTPUT_FILE = "-outputFile";

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
