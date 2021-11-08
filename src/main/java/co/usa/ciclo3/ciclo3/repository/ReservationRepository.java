package co.usa.ciclo3.ciclo3.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.usa.ciclo3.ciclo3.model.Client;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.repository.crud.ReservationCrudRepository;

@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;

    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    public Optional<Reservation> getReservation(int idReservarion){
        return reservationCrudRepository.findById(idReservarion);
    }
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation); 
    }
    public void delete(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    public List<Reservation> getReservationsByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }
    public List<Reservation> getReservationsPeriod(Date dateOne,Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    public List<CountClient> getTopClients(){
        List<CountClient> res=new ArrayList<>();
    
        List<Object[]> report=reservationCrudRepository.countTotalReservationByClient();
        for(int i=0;i<report.size();i++ ){
            /*           
            Client  cli=(Client) report.get(i)[0];
            Long cantidad=(Long)report.get(i)[1];
            CountClient cc=new CountClient(cantidad , cli);
            res.add(cc);
            */
            res.add(new CountClient((Long) report.get(i)[1] , (Client)report.get(i)[0] ));
        }
 
        return res;
    }
    

}
