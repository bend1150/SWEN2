package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.model.Tour;
import at.fhtw.swen2.tutorial.service.model.TourLog;
import at.fhtw.swen2.tutorial.service.pdf.PdfReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Float.parseFloat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.postgresql.hostchooser.HostRequirement.any;

class TourInfoViewModelTest {

    @InjectMocks
    private TourInfoViewModel tourInfoViewModel;

    @Mock
    TourLogInfoViewModel tourLogInfoViewModel;

    @Mock
    PdfReport pdfReport;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void updateInfo() {
        Tour mockTour = Tour.builder()
                .id(0l)
                .name("MockTour")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        tourInfoViewModel.setName("NewName");
        tourInfoViewModel.setDescription("NewTestDec");
        tourInfoViewModel.setOrigin("Graz");
        tourInfoViewModel.setDestination("Wien");
        tourInfoViewModel.setDescription("Nett hier");
        tourInfoViewModel.setTransport("Zug");
        tourInfoViewModel.setDistance("5");
        tourInfoViewModel.setTime("10");

        tourInfoViewModel.updateInfo(mockTour);

        assertEquals("MockTour", tourInfoViewModel.getName());
    }

    @Test
    void updateInfo2() {
        Tour mockTour = Tour.builder()
                .id(0l)
                .name("AwesomeTourNr2")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        tourInfoViewModel.setName("MockTour");
        tourInfoViewModel.setDescription("NewTestDec");
        tourInfoViewModel.setOrigin("Graz");
        tourInfoViewModel.setDestination("Wien");
        tourInfoViewModel.setDescription("Nett hier");
        tourInfoViewModel.setTransport("Zug");
        tourInfoViewModel.setDistance("5");
        tourInfoViewModel.setTime("10");

        tourInfoViewModel.updateInfo(mockTour);

        assertEquals("AwesomeTourNr2", tourInfoViewModel.getName());
    }

    @Test
    void clearInfo() {
        Mockito.doNothing().when(tourLogInfoViewModel).setSelectedTour(null);

        tourInfoViewModel.setName("NameInWindow");
        tourInfoViewModel.setDescription("NewTestDec");
        tourInfoViewModel.setOrigin("Graz");
        tourInfoViewModel.setDestination("Wien");
        tourInfoViewModel.setDescription("Nett hier");
        tourInfoViewModel.setTransport("Zug");
        tourInfoViewModel.setDistance("9");
        tourInfoViewModel.setTime("10");

        tourInfoViewModel.clearInfo();

        assertEquals("", tourInfoViewModel.getName());
        assertEquals("", tourInfoViewModel.getDescription());
        assertEquals("", tourInfoViewModel.getOrigin());
        assertEquals("", tourInfoViewModel.getDestination());
        assertEquals("", tourInfoViewModel.getDescription());
        assertEquals("", tourInfoViewModel.getTransport());
        assertEquals("", tourInfoViewModel.getDistance());
        assertEquals("", tourInfoViewModel.getTime());
    }

    @Test
    void generateReport_successfully() {
        File f = new File("Reports\\MockTour.pdf");
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("MockTour")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        TourLog mockTourLog = TourLog.builder()
                .date("Test")
                .time(123f)
                .comment("Hello")
                .difficulty("High")
                .totalTime(5f)
                .rating(12345f)
                .build();

        List<TourLog> mockTourLogList = new ArrayList<>();
        mockTourLogList.add(mockTourLog);

        when(tourLogInfoViewModel.getSelectedTour()).thenReturn(mockTour);
        when(tourLogInfoViewModel.getTourLogList()).thenReturn(null);
        Mockito.doNothing().when(pdfReport).createReport(any(Tour.class), anyList());
        when(tourLogInfoViewModel.getTourLogList()).thenReturn(mockTourLogList);

        tourInfoViewModel.generateReport();

        boolean success = false;
        if(f.exists() && !f.isDirectory()) {
            success = true;
        }

        assertTrue(success);

        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }

    @Test
    void generateReport_failed() {
        File f = new File("Reports\\MockTour.pdf");
        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }

        Tour mockTour = null;

        TourLog mockTourLog = TourLog.builder()
                .date("Test")
                .time(123f)
                .comment("Hello")
                .difficulty("High")
                .totalTime(5f)
                .rating(12345f)
                .build();

        List<TourLog> mockTourLogList = new ArrayList<>();
        mockTourLogList.add(mockTourLog);

        when(tourLogInfoViewModel.getSelectedTour()).thenReturn(mockTour);
        when(tourLogInfoViewModel.getTourLogList()).thenReturn(null);
        Mockito.doNothing().when(pdfReport).createReport(any(Tour.class), anyList());
        when(tourLogInfoViewModel.getTourLogList()).thenReturn(mockTourLogList);

        tourInfoViewModel.generateReport();

        boolean success = false;
        if(f.exists() && !f.isDirectory()) {
            success = true;
        }

        assertFalse(success);

        if(f.exists() && !f.isDirectory()) {
            f.delete();
        }
    }
}