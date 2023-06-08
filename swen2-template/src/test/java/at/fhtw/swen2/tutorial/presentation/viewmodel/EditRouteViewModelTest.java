package at.fhtw.swen2.tutorial.presentation.viewmodel;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.presentation.view.TourEditController;
import at.fhtw.swen2.tutorial.presentation.view.TourInfoController;
import at.fhtw.swen2.tutorial.service.RouteService;
import at.fhtw.swen2.tutorial.service.impl.RouteServiceImpl;
import at.fhtw.swen2.tutorial.service.mapper.TourMapper;
import at.fhtw.swen2.tutorial.service.model.Tour;
import org.aspectj.lang.annotation.Before;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EditRouteViewModelTest {

    @Mock
    RouteServiceImpl routeService = new RouteServiceImpl();

    @Mock
    TourMapper tourMapper;

    @InjectMocks
    private EditRouteViewModel editRouteViewModel;

    @Mock
    RouteListViewModel routeListViewModel;

    @Mock
    TourInfoViewModel tourInfoViewModel;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void updateRoute() {
        Tour selectedTour = Tour.builder()
                .id(0l)
                .name("Test")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3)
                .time(3)
                .build();
        TourEntity selectedTourEntity = TourEntity.builder()
                .id(0l)
                .name("Test")
                .description("TestDesc")
                .origin("Wien")
                .destination("Graz")
                .transportType("Bus")
                .distance(3f)
                .time(3f)
                .build();

        Mockito.doNothing().when(routeListViewModel).updateTourList();
        Mockito.doNothing().when(tourInfoViewModel).updateInfo(selectedTour);

        /*newRouteViewModel.setName("Test");
        newRouteViewModel.setDescription("TestDesc");
        newRouteViewModel.setOrigin("Wien");
        newRouteViewModel.setDestination("Graz");
        newRouteViewModel.setTransport("Bus");*/

        editRouteViewModel.setName("NewTest");
        editRouteViewModel.setDescription("NewTestDec");
        editRouteViewModel.setOrigin("Graz");
        editRouteViewModel.setDestination("Wien");
        editRouteViewModel.setDescription("Nett hier");
        editRouteViewModel.setTransport("Zug");
        editRouteViewModel.setDistance("5");
        editRouteViewModel.setTime("10");

        Tour testTour = editRouteViewModel.updateRoute(selectedTour);

        //Tour updatedTour = routeService.getById(0l);

        assertEquals("NewTest", testTour.getName());

    }

    @Test
    void setProperties() {
    }

    @Test
    void cancel() {
    }

    @Test
    void openDialog() {
    }
}