package co.usa.ciclo3.ciclo3.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import co.usa.ciclo3.ciclo3.model.Reservation;
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
    public Reservation save(Reservation r){
        return reservationCrudRepository.save(r); 
    }
    public void delete(Reservation r){
        reservationCrudRepository.delete(r);
    }


}
