package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.model.Tour;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RouteListViewModelTest {
    @InjectMocks
    private RouteListViewModel routeListViewModel;

    @Mock
    TourLogInfoViewModel tourLogInfoViewModel;

    @Mock
    RouteService routeService;

    @Mock
    TourInfoViewModel tourInfoViewModel;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void deleteSelected_validIndex() {
        Mockito.doNothing().when(tourLogInfoViewModel).deleteAllTourLogs();

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.deleteSelected(0);

        verify(routeService, times(1)).deleteById(0);   //schaut wie oft ne funktion aufgerufen wird, if called more than once or never -> test fail
    }

    @Test
    void deleteSelected2_validIndex() {
        Mockito.doNothing().when(tourLogInfoViewModel).deleteAllTourLogs();

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        Tour mockTour2 = Tour.builder()
                .id(1l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        mockTourList.add(mockTour2);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.deleteSelected(1);

        verify(routeService, times(1)).deleteById(1);
    }

    @Test
    void deleteSelected_invalidIndex() {
        Mockito.doNothing().when(tourLogInfoViewModel).deleteAllTourLogs();

        Tour mockTour = Tour.builder()
                .id(-2l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.deleteSelected(Math.toIntExact(mockTour.getId()));

        verify(routeService, times(0)).deleteById(anyInt());
    }

    @Test
    void deleteSelected2_invalidIndex() {
        Mockito.doNothing().when(tourLogInfoViewModel).deleteAllTourLogs();

        Tour mockTour = Tour.builder()
                .id(-24l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.deleteSelected(Math.toIntExact(mockTour.getId()));

        verify(routeService, times(0)).deleteById(anyInt());
    }

    @Test
    void updateTourList() {
        Tour newMockTour = Tour.builder()
                .id(1l)
                .name("otherName")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        List<Tour> newMockTourList = new ArrayList<>();
        newMockTourList.add(newMockTour);


        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        when(routeService.getRouteList()).thenReturn(newMockTourList);

        routeListViewModel.updateTourList();

        assertEquals("otherName", routeService.getRouteList().get(0).getName());
    }

    @Test
    void updateTourList2() {
        Tour newMockTour = Tour.builder()
                .id(1l)
                .name("otherName")
                .description("TestDesc")
                .origin("Graz")
                .destination("Wien")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        List<Tour> newMockTourList = new ArrayList<>();
        newMockTourList.add(newMockTour);


        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        when(routeService.getRouteList()).thenReturn(newMockTourList);

        routeListViewModel.updateTourList();

        assertEquals("Graz", routeService.getRouteList().get(0).getOrigin());
    }

    @Test
    void updateSelectedIndex_validIndex() {
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(any(Tour.class));
        Mockito.doNothing().when(tourLogInfoViewModel).showTourLogs(anyInt());
        Mockito.doNothing().when(tourLogInfoViewModel).setSelectedTour(any(Tour.class));

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.updateSelectedIndex(0);

        verify(tourInfoViewModel, times(1)).updateInfo(any(Tour.class));
        verify(tourLogInfoViewModel, times(1)).showTourLogs(anyInt());
        verify(tourLogInfoViewModel, times(1)).setSelectedTour(any(Tour.class));
    }

    @Test
    void updateSelectedIndex2_validIndex() {
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(any(Tour.class));
        Mockito.doNothing().when(tourLogInfoViewModel).showTourLogs(anyInt());
        Mockito.doNothing().when(tourLogInfoViewModel).setSelectedTour(any(Tour.class));

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        Tour mockTour2 = Tour.builder()
                .id(1l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        mockTourList.add(mockTour2);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.updateSelectedIndex(1);

        verify(tourInfoViewModel, times(1)).updateInfo(any(Tour.class));
        verify(tourLogInfoViewModel, times(1)).showTourLogs(anyInt());
        verify(tourLogInfoViewModel, times(1)).setSelectedTour(any(Tour.class));
    }

    @Test
    void updateSelectedIndex_invalidIndex() {
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(any(Tour.class));
        Mockito.doNothing().when(tourLogInfoViewModel).showTourLogs(anyInt());
        Mockito.doNothing().when(tourLogInfoViewModel).setSelectedTour(any(Tour.class));

        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        ObservableList<Tour> mockTourList = FXCollections.observableArrayList();
        mockTourList.add(mockTour);
        routeListViewModel.setTourList(mockTourList);

        routeListViewModel.updateSelectedIndex(-1);

        verify(tourInfoViewModel, times(0)).updateInfo(any(Tour.class));
        verify(tourLogInfoViewModel, times(0)).showTourLogs(anyInt());
        verify(tourLogInfoViewModel, times(0)).setSelectedTour(any(Tour.class));
    }

    @Test
    void filterList_notEmpty() {
        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("MyNameJeff")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        List<Tour> mockTourList = new ArrayList<>();
        mockTourList.add(mockTour);

        when(routeService.filter("MyNameJeff")).thenReturn(mockTourList);


        routeListViewModel.filterList("MyNameJeff");

        assertEquals("NameInList", routeListViewModel.getTourList().get(0).getName());
    }

    @Test
    void filterList2_notEmpty() {
        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("MyNameJeff")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        List<Tour> mockTourList = new ArrayList<>();
        mockTourList.add(mockTour);

        when(routeService.filter("MyNameJeff")).thenReturn(mockTourList);
        when(routeService.filter(anyString())).thenReturn(mockTourList);


        routeListViewModel.filterList("MyNameNotJeff");

        assertEquals("NameInList", routeListViewModel.getTourList().get(0).getName());
    }

    @Test
    void filterList_Empty() {
        Tour mockTour = Tour.builder()
                .id(0l)
                .name("NameInList")
                .description("MyNameJeff")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();

        List<Tour> mockTourList = new ArrayList<>();
        mockTourList.add(mockTour);

        when(routeService.filter("MyNameJeff")).thenReturn(mockTourList);


        routeListViewModel.filterList("");

        assertTrue(routeListViewModel.getTourList().isEmpty());
    }
}