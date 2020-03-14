package com.epam.util;

import com.epam.model.User;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePDFUtils {

        public static ByteArrayInputStream getUsersPDF(List<User> users) {

            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();

            try {

                PdfPTable table = new PdfPTable(3);
                table.setWidthPercentage(60);
                table.setWidths(new int[]{1, 3, 3});

                Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

                PdfPCell hcell;
                hcell = new PdfPCell(new Phrase("Id", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("First Name", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                hcell = new PdfPCell(new Phrase("Last Name", headFont));
                hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(hcell);

                for (User user : users) {

                    PdfPCell cell;

                    cell = new PdfPCell(new Phrase(user.getId().toString()));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(user.getFirstName()));
                    cell.setPaddingLeft(5);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                    table.addCell(cell);

                    cell = new PdfPCell(new Phrase(String.valueOf(user.getLastName())));
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setPaddingRight(5);
                    table.addCell(cell);
                }

                PdfWriter.getInstance(document, out);
                document.open();
                document.add(table);

                document.close();

            } catch (DocumentException ex) {
                ex.printStackTrace();
            }

            return new ByteArrayInputStream(out.toByteArray());
        }
}
