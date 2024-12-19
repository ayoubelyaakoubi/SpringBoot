package com.SpringBoot.Tp3.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.SpringBoot.Tp3.DTO.ImageRequest;
import com.SpringBoot.Tp3.DTO.RoleRepo;
import com.SpringBoot.Tp3.DTO.UserRepo;
import com.SpringBoot.Tp3.DTO.UserRequestDTO;
import com.SpringBoot.Tp3.DTO.imageRepo;
import com.SpringBoot.Tp3.Entity.Role;
import com.SpringBoot.Tp3.Entity.User;
import com.SpringBoot.Tp3.Entity.UtilisateurImage;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepo utilisateurRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private imageRepo utilisateurImageRepository;

    @Transactional
    public User createUtilisateurWithRole(UserRequestDTO userRequestDTO) {

        Role role = roleRepository.findByName(userRequestDTO.getRoleName())
                .orElseThrow(() -> new RuntimeException("Rôle non trouvé"));

        User utilisateur = new User();
        utilisateur.setName(userRequestDTO.getNom());
        utilisateur.setEmail(userRequestDTO.getEmail());
        utilisateur.setRole(role);

        return utilisateurRepository.save(utilisateur);
    }

    @Transactional
    public UtilisateurImage addImageToUtilisateur(ImageRequest imageRequest) {
        // Recherche de l'utilisateur
        User utilisateur = utilisateurRepository.findById(imageRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        // Création de l'image
        UtilisateurImage image = new UtilisateurImage();
        image.setNomImage(imageRequest.getNomImage());
        image.setCheminImage(imageRequest.getCheminImage());

        // Associer l'image à l'utilisateur
        image.setUser(utilisateur);

        // Sauvegarder l'image
        utilisateurImageRepository.save(image);

        return image;
    }

    public List<User> getAllUtilisateurs() {

        return utilisateurRepository.findAll();
    }

    public User getUtilisateurById(Long id) {
        return utilisateurRepository.findById(id).get();

    }

    public User assignRoleToUtilisateur(Long utilisateurId, String roleId) {
        // TODO Auto-generated method stub
        User user = utilisateurRepository.findById(utilisateurId).get();
        Role role = roleRepository.findByName(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
        user.setRole(role);
        return utilisateurRepository.save(user);

    }

    public void deleteUtilisateurById(Long id) {
        utilisateurRepository.deleteById(id);

    }

}
