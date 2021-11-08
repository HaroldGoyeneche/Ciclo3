package co.usa.ciclo3.ciclo3.repository.crud;


import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import co.usa.ciclo3.ciclo3.model.Reservation;

public interface ReservationCrudRepository extends CrudRepository<Reservation,Integer> {
    
    //JPQL
    @Query("select c.client,COUNT(c.client) from Reservation AS c group by c.client order by COUNT(c.client) desc")
    public List<Object[]> countTotalReservationByClient();
    
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne,Date dateTwo);
    
    public List<Reservation> findAllByStatus(String Status);
}
