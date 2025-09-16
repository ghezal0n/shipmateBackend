package com.example.cost.service;

import com.example.cost.entity.CalculForm;
import com.example.cost.entity.User;
import com.example.cost.repository.CalculFormRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalculFormService {

    @Autowired
    private CalculFormRepository calculFormRepository;

    //Enregistre un formulaire après avoir calculé le total
    public CalculForm saveForm(CalculForm form, User user) {
        double champ1 = form.getChamp1() != null ? form.getChamp1() : 0.0;
        double champ2 = form.getChamp2() != null ? form.getChamp2() : 0.0;
        double champ3 = form.getChamp3() != null ? form.getChamp3() : 0.0;

        form.setTotal(champ1 + champ2 + champ3);
        form.setCreatedAt(LocalDateTime.now());
        form.setUser(user);

        return calculFormRepository.save(form);
    }

    //Récupère tous les formulaires d’un utilisateur
    public List<CalculForm> getFormsByUser(User user) {
        return calculFormRepository.findByUser(user);
    }
}
