package at.fhtw.swen2.tutorial.persistence;

import at.fhtw.swen2.tutorial.persistence.entities.TourEntity;
import at.fhtw.swen2.tutorial.persistence.repositories.RouteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RoutePersistenceTests {

	@Autowired
	private RouteRepository routeRepository;

	@Test
	void testPersonRepository() {
		TourEntity route1 = TourEntity.builder()
				.name("Wien1")
				.description("fast")
				.from("Ottakring")
				.to("Simmering")
				.transportType("subway")
				.distance(15.5F)
				.routeInformation("C:\\Test\\Test.jpg")
				.build();
		routeRepository.save(route1);
		routeRepository.findAll().forEach(System.out::println);
	}

}
