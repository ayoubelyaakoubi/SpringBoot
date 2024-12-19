package com.SpringBoot.Tp3.Controller;

import org.springframework.web.bind.annotation.*;

import com.SpringBoot.Tp3.DTO.ImageRequest;
import com.SpringBoot.Tp3.DTO.UserRequestDTO;
import com.SpringBoot.Tp3.Entity.User;
import com.SpringBoot.Tp3.Entity.UtilisateurImage;
import com.SpringBoot.Tp3.Service.ImageService;
import com.SpringBoot.Tp3.Service.RoleService;
import com.SpringBoot.Tp3.Service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
public class UserController {

    private final UserService utilisateurService;

    private final RoleService service;
    private final ImageService imageService;

    @GetMapping
    public List<User> getAllUtilisateurs() {
        return utilisateurService.getAllUtilisateurs();
    }

    // Endpoint pour créer un nouvel utilisateur avec son rôle
    @PostMapping("/create")
    public User createUtilisateur(@RequestBody UserRequestDTO utilisateurRequest) {
        return utilisateurService.createUtilisateurWithRole(utilisateurRequest);
    }

    @GetMapping("/{id}")
    public User getUtilisateurById(@PathVariable Long id) {
        return utilisateurService.getUtilisateurById(id);
    }

    @PutMapping("{utilisateurId}/role/{roleId}")
    public User assignRoleToUtilisateur(@PathVariable Long utilisateurId, @PathVariable String roleId) {
        return utilisateurService.assignRoleToUtilisateur(utilisateurId, roleId);
    }

    // Endpoint pour ajouter une image à un utilisateur existant
    @PostMapping("/addImage")
    public UtilisateurImage addImageToUtilisateur(@RequestBody ImageRequest imageRequest) {
        return utilisateurService.addImageToUtilisateur(imageRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUtilisateurById(@PathVariable Long id) {
        utilisateurService.deleteUtilisateurById(id);
    }

    @DeleteMapping("/roles/{roleId}")
    public void deleteRoleById(@PathVariable String roleId) {
        service.deleteRoleById(roleId);
    }

    @DeleteMapping("/{utilisateurId}/image/{imageId}")
    public void deleteImageFromUtilisateur(@PathVariable Long utilisateurId, @PathVariable Long imageId) {
        imageService.deleteImageFromUtilisateur(utilisateurId, imageId);
    }

}
