package mba.myAEBackEnd.service;

import lombok.AllArgsConstructor;
import mba.myAEBackEnd.entity.Compteur;
import mba.myAEBackEnd.enums.ParamEnum;
import mba.myAEBackEnd.repository.CompteurRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CompteurService {

    private CompteurRepository compteurRepository;

    public Compteur getParameterByCode(ParamEnum paramEnum){
        Optional<Compteur> optional = compteurRepository.findByCode(paramEnum);
        if(optional.isEmpty()){
            Compteur compteur = new Compteur().setCode(paramEnum).setValue(1);
            compteurRepository.save(compteur);
            return compteur;
        }
        return optional.get();
    }

    public void incrementAndSave(Compteur compteur){
        compteur.increment();
        compteurRepository.save(compteur);
    }
}
