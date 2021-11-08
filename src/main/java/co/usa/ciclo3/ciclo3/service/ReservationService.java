package co.usa.ciclo3.ciclo3.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Reservation;
import co.usa.ciclo3.ciclo3.model.custom.CountClient;
import co.usa.ciclo3.ciclo3.model.custom.StatusAmount;
import co.usa.ciclo3.ciclo3.repository.ReservationRepository;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAll() {
        return reservationRepository.getAll();
    }

    public Optional<Reservation> getReservation(int idReservarion) {
        return reservationRepository.getReservation(idReservarion);
    }

    public Reservation save(Reservation reservation) {
        if (reservation.getIdReservation() == null) {

            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> caux = reservationRepository.getReservation(reservation.getIdReservation());
            if (caux.isEmpty()) {
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }

    public Reservation update(Reservation reservation) {
        if (reservation.getIdReservation() != null) {
            Optional<Reservation> raux = reservationRepository.getReservation(reservation.getIdReservation());
            if (!raux.isEmpty()) {
                if (reservation.getStartDate() != null) {
                    raux.get().setStartDate(reservation.getStartDate());
                }
                if (reservation.getDevolutionDate() != null) {
                    raux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                if (reservation.getStatus() != null) {
                    raux.get().setStatus(reservation.getStatus());
                }
                reservationRepository.save(raux.get());
                return raux.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }

    }

    public boolean deleteReservation(int reservationId) {
        Boolean aBoolean = getReservation(reservationId).map(reservation -> {
            reservationRepository.delete(reservation);
            return true;
        }).orElse(false);
        return aBoolean;
    }

    public List<CountClient> getTopClients() {
        return reservationRepository.getTopClients();
    }

    public StatusAmount getStatusReport() {
        List<Reservation> completed = reservationRepository.getReservationsByStatus("Completed");
        List<Reservation> cancelled = reservationRepository.getReservationsByStatus("Cancelled");

        StatusAmount descAmt = new StatusAmount(completed.size(), cancelled.size());
        return descAmt;
    }

    public List<Reservation> getReservationsPeriod(String  d1,String d2){

        SimpleDateFormat parser=new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne=new Date();
        Date dateTwo=new Date();
        try {
            dateOne=parser.parse(d1);
            dateTwo=parser.parse(d2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(dateOne.before(dateTwo)){
            return reservationRepository.getReservationsPeriod(dateOne, dateTwo);
        }else{
            return new ArrayList<>();
        }
    }
}
