package com.SpringBoot.Tp3.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageRequest {

    private String nomImage;
    private String cheminImage;
    private Long userId;

}
