package at.fhtw.swen2.tutorial.presentation.viewmodel;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class SearchViewModelTest {

    @InjectMocks
    private SearchViewModel searchViewModel;

    @Mock
    RouteListViewModel routeListViewModel;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void search() {
        Mockito.doNothing().when(routeListViewModel).filterList(anyString());
        searchViewModel.setSearchString("Test");
        searchViewModel.search();

        verify(routeListViewModel, times(1)).filterList(anyString());
    }
}