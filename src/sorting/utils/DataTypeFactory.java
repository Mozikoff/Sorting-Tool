package sorting.utils;

import sorting.Main;
import sorting.models.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;
import java.util.Scanner;

public class DataTypeFactory {
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
