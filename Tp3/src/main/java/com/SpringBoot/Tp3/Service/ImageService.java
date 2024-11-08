package com.SpringBoot.Tp3.Service;

import org.springframework.stereotype.Service;

import com.SpringBoot.Tp3.DTO.UserRepo;
import com.SpringBoot.Tp3.DTO.imageRepo;
import com.SpringBoot.Tp3.Entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final imageRepo imgRepo;
    private final UserRepo userRepo;

    public void deleteImageFromUtilisateur(Long utilisateurId, Long imageId) {

        User user = userRepo.findById(utilisateurId).get();
        user.setUserImage(null);
        imgRepo.deleteById(imageId);
    }

}
