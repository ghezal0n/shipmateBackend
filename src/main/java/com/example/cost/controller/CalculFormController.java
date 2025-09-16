package com.example.cost.controller;

import com.example.cost.entity.CalculForm;
import com.example.cost.entity.User;
import com.example.cost.repository.UserRepository;
import com.example.cost.service.CalculFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forms")
public class CalculFormController {

    @Autowired
    private CalculFormService calculFormService;

    @Autowired
    private UserRepository userRepository;

    //TEMPORAIRE : pour simuler l'utilisateur (jusqu’à ce qu’on ajoute l’authentification)
    private User getFakeUser() {
        return userRepository.findById(1L).orElseThrow(() -> new RuntimeException("User non trouvé"));
    }

    //POST : créer un formulaire de calcul
    @PostMapping
    public CalculForm createForm(@RequestBody CalculForm form) {
        User user = getFakeUser(); // à remplacer plus tard par l’utilisateur authentifié
        return calculFormService.saveForm(form, user);
    }

    //GET : afficher les formulaires de l’utilisateur
    @GetMapping
    public List<CalculForm> getUserForms() {
        User user = getFakeUser(); // à remplacer plus tard par l’utilisateur authentifié
        return calculFormService.getFormsByUser(user);
    }
}
