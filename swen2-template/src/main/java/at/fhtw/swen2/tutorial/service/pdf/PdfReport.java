package at.fhtw.swen2.tutorial.service.pdf;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class PdfReport {
   public void createReport(Tour tour, List<TourLog> tourLogList) {
       File htmlFile = new File("./src/main/java/at/fhtw/swen2/tutorial/service/pdf/ReportBuilder.html");
       String htmlContent = "";
       try{
           FileInputStream inputStream = new FileInputStream(htmlFile);
           byte[] buffer = new byte[(int) htmlFile.length()];
           inputStream.read(buffer);
           htmlContent = new String(buffer);
           inputStream.close();
       }catch (Exception ex){
           System.out.println(ex);
       }

       //replace Placeholders:
       String title = tour.getName();
       String origin = tour.getOrigin();
       String destination = tour.getDestination();
       String transportType = tour.getTransportType();
       Float distance = tour.getDistance();
       Float time = tour.getTime();
       String description = tour.getDescription();

       htmlContent = htmlContent.replace("{{title}}", title);
       htmlContent = htmlContent.replace("{{origin}}", origin);
       htmlContent = htmlContent.replace("{{destination}}", destination);
       htmlContent = htmlContent.replace("{{transport}}", transportType);
       htmlContent = htmlContent.replace("{{distance}}", distance.toString());
       htmlContent = htmlContent.replace("{{time}}", time.toString());
       htmlContent = htmlContent.replace("{{description}}", description);

       try{
           String dest = "./Reports/" + tour.getName() + ".pdf";
           PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(dest));
           ConverterProperties props = new ConverterProperties();
           HtmlConverter.convertToPdf(htmlContent, pdfWriter, props);
       }catch(Exception ex){
           System.out.println(ex);
       }

   }
}
