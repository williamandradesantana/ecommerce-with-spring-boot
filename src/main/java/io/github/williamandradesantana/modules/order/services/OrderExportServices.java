package io.github.williamandradesantana.modules.order.services;

import io.github.williamandradesantana.modules.order.entity.Order;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
public class OrderExportServices {

    public ByteArrayInputStream exportOrdersToExcel(List<Order> orders) throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Orders");

            Row header = sheet.createRow(0);
            header.createCell(0).setCellValue("ID");
            header.createCell(1).setCellValue("order_date");
            header.createCell(2).setCellValue("order_status");
            header.createCell(3).setCellValue("total");

            int rowIndex = 1;

            for (Order order : orders) {
                Row row = sheet.createRow(rowIndex++);

                row.createCell(0).setCellValue(order.getId().toString());
                row.createCell(1).setCellValue(order.getOrderDate().toString());
                row.createCell(2).setCellValue(order.getOrderStatus().toString());
                row.createCell(3).setCellValue(order.getTotal().doubleValue());
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
