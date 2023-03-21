package at.fhtw.swen2.tutorial.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TourReportEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // FetchType.Lazy -> Object(RouteEntity) sollte nur bei Zugriff geladen werden
    @ManyToOne(fetch = FetchType.LAZY)          //TourReport kann mehrere Tour logs haben
    @JoinColumn(name = "tour_id")               //spezifiziert die Spalte "tour_id", welches die foreign Key vom Tour & Tourlogs speichert
    private RouteEntity tour;
    @OneToMany(mappedBy = "tourReport", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TourLogEntity> tourLogs;

    //'mappedBy' name of the attribute in TourLogEntity that maps this relation
    //'cascade' specifies any operation (create,update,delete) on the tour report entity should also be performed on the associated tour log entities
    //'orphanRemoval' TourLogEntity that is no longer associated with tou TourReportEntity should be removed from DB

}
