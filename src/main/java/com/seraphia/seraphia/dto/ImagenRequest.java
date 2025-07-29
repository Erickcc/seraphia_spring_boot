package com.seraphia.seraphia.dto;

import com.seraphia.seraphia.model.Images;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ImagenRequest {
    //    private String imageUrl;
//    private Integer imageOrder;
    private List<Images> images;
}