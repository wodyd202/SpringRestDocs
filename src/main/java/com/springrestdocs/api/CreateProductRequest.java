package com.springrestdocs.api;

import com.springrestdocs.application.CreateProductDto;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CreateProductRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    @Min(0)
    private long price;

    public CreateProductDto toDto() {
        return CreateProductDto.of(name, description, price);
    }
}
