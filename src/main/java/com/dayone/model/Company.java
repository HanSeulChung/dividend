package com.dayone.model;


import lombok.Builder;
import lombok.Data;

// 모든 모델에 @Data 를 쓰는것은 지양 해야함.
@Data
@Builder
public class Company {
    private String ticker;
    private String name;
}
