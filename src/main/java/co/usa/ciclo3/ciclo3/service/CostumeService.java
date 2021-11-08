package co.usa.ciclo3.ciclo3.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import co.usa.ciclo3.ciclo3.model.Costume;
import co.usa.ciclo3.ciclo3.repository.CostumeRepository;

@Service
public class CostumeService {

    @Autowired
    private CostumeRepository costumeRepository;

    public List<Costume> getAll() {
        return costumeRepository.getAll();
    }

    public Optional<Costume> getCostume(int id) {
        return costumeRepository.getCostume(id);
    }

    public Costume save(Costume costume) {
        if (costume.getId() == null) {

            return costumeRepository.save(costume);
        } else {
            Optional<Costume> caux = costumeRepository.getCostume(costume.getId());
            if (caux.isEmpty()) {
                return costumeRepository.save(costume);
            } else {
                return costume;
            }
        }
    }

    public Costume update(Costume costume){
        if (costume.getId()!=null){
            Optional<Costume> cosaux=costumeRepository.getCostume(costume.getId());
            if(!cosaux.isEmpty()){
                if (costume.getName()!=null){
                    cosaux.get().setName(costume.getName());
                }
                if (costume.getBrand()!=null) {
                    cosaux.get().setBrand(costume.getBrand());
                }
                if (costume.getYear()!=null) {
                    cosaux.get().setYear(costume.getYear());
                }
                if (costume.getCategory()!=null) {
                    cosaux.get().setCategory(costume.getCategory());
                }
                costumeRepository.save(cosaux.get());
                return cosaux.get();
            }else{
                return costume;
            }
        }else{
            return costume;
        }

    }

    public boolean deleteCostume(int costumeId){
        Boolean aBoolean =  getCostume(costumeId).map(costume ->{
            costumeRepository.delete(costume);
            return  true;
        }).orElse(false);
        return aBoolean;
    }
}
