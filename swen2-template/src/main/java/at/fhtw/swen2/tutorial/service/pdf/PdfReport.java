package at.fhtw.swen2.tutorial.service.pdf;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

public class PdfReport {
    private static final Logger logger = LogManager.getLogger(PdfReport.class);

    public void createReport(Tour tour, List<TourLog> tourLogList) {
       File htmlFile = new File("./src/main/java/at/fhtw/swen2/tutorial/service/pdf/ReportBuilder.html");
       String htmlContent = "";
       try{
           logger.info("preparing report...");

           FileInputStream inputStream = new FileInputStream(htmlFile);
           byte[] buffer = new byte[(int) htmlFile.length()];
           inputStream.read(buffer);
           htmlContent = new String(buffer);
           inputStream.close();
       }catch (Exception ex){
           logger.error("Report generation failed.");
           logger.error(ex);
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

       for (TourLog log : tourLogList
            ) {
            String logInfo =
                """
                    <table class='border'>
                        <tr>
                            <td class='border'>
                                <p class="underline">Date:</p>
                            </td>
                            <td class="border">
                                <p>{{date}}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="border">
                                <p class="underline">Time:</p>
                            </td>
                            <td class="border">
                                <p>{{time}}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="border">
                                <p class="underline">Comment:</p>
                            </td>
                            <td class="border">
                                <p>{{comment}}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="border">
                                <p class="underline">Difficulty:</p>
                            </td>
                            <td class="border">
                                <p>{{difficulty}}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="border">
                                <p class="underline">Total Time:</p>
                            </td>
                            <td class="border">
                                <p>{{totalTime}}</p>
                            </td>
                        </tr>
                        <tr>
                            <td class="border">
                                <p class="underline">Rating:</p>
                            </td>
                            <td class="border">
                                <p>{{rating}}</p>
                            </td>
                        </tr>
                    </table>
                    <br>
                    <br>
                """;

            logInfo = logInfo.replace("{{date}}", log.getDate());
            logInfo = logInfo.replace("{{time}}", log.getTime().toString());
            logInfo = logInfo.replace("{{comment}}", log.getComment());
            logInfo = logInfo.replace("{{difficulty}}", log.getDifficulty());
            logInfo = logInfo.replace("{{totalTime}}", log.getTotalTime().toString());
            logInfo = logInfo.replace("{{rating}}", log.getRating().toString());

            htmlContent += logInfo;
       }

       try{
            logger.info("generating report...");

           String dest = "./Reports/" + tour.getName() + ".pdf";
           PdfWriter pdfWriter = new PdfWriter(new FileOutputStream(dest));
           ConverterProperties props = new ConverterProperties();
           HtmlConverter.convertToPdf(htmlContent, pdfWriter, props);
       }catch(Exception ex){
           logger.error("Error generating report");
           logger.error(ex);
       }

   }
}
