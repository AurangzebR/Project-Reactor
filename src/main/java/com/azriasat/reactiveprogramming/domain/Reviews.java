package com.azriasat.reactiveprogramming.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {
    private Long reviewId;
    private Long bookId;
    private double ratings;
    private String comments;
}
