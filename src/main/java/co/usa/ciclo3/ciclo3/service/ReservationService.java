package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;

@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int idReservarion){
        return reservationRepository.getReservation(idReservarion);
    }
    
    public Reservation save(Reservation r){
         if (r.getIdReservation()==null){

         
        return reservationRepository.save(r);
        }else{
            Optional<Reservation> caux=reservationRepository.getReservation(r.getIdReservation());
            if(caux.isEmpty()){
                return reservationRepository.save(r); 
            }else{
                return r;
            }
        }
    }
    public Reservation Update(Reservation r){
        if (r.getIdReservation()!=null){
            Optional<Reservation> e=reservationRepository.getReservation(r.getIdReservation());
            if(!e.isEmpty()){
                if (r.getStartDate()!=null){
                    e.get().setStartDate(r.getStartDate());
                }
                if (r.getDevolutionDate()!=null) {
                    e.get().setDevolutionDate(r.getDevolutionDate());
                }
                if (r.getStatus()!=null) {
                    e.get().setStatus(r.getStatus());
                }
                reservationRepository.save(e.get());
                return e.get();
            }else{
                return r;
            }
        }else{
            return r;
        }

    }

    public boolean deleteReservation(int reservationId){
        Boolean aBoolean =  getReservation(reservationId).map(reservation ->{
            reservationRepository.delete(reservation);
            return  true;
        }).orElse(false);
        return aBoolean;
    }
    
}
