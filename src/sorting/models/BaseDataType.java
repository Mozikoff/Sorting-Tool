package sorting.models;

import java.io.PrintWriter;
import java.util.Scanner;

public abstract class BaseDataType {
    protected PrintWriter writer;
    protected Scanner scanner;

    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public void closeResources() {
        scanner.close();
    }
}
