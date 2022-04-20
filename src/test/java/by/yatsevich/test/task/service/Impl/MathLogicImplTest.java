package by.yatsevich.test.task.service.Impl;

import by.yatsevich.test.task.service.MathLogic;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;

import static org.junit.Assert.*;

public class MathLogicImplTest {

    MathLogic mathLogic = MathLogic.getInstance();

    @Test
    public void getNumberInString() throws IOException {

        FileInputStream fileInputStream = new FileInputStream("src" + File.separator + "test" + File.separator + "data_for_test.xls");
        Workbook workbook = new HSSFWorkbook(fileInputStream);

        long number = 0;
        String numberInStringFormat = null;

        Sheet sheet = workbook.getSheetAt(0);
        for (Row row : sheet) {
            for (Cell cell : row) {

                switch (cell.getCellType()) {
                    case Cell.CELL_TYPE_NUMERIC:
                        number = (long) cell.getNumericCellValue();
                        System.out.print(number + " - ");
                        break;
                    case Cell.CELL_TYPE_STRING:
                        numberInStringFormat = cell.getRichStringCellValue().getString();
                        System.out.println(numberInStringFormat);
                        break;
                    default:
                        System.out.println();
                }
            }
            assertEquals(numberInStringFormat, mathLogic.getNumberInString(new BigInteger(String.valueOf(number))));
        }

    }
}