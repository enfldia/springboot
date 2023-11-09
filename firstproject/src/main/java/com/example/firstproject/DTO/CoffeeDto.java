package com.example.firstproject.DTO;

import com.example.firstproject.entity.Coffee;
import lombok.AllArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class CoffeeDto {
    public Long id;
    public String name;
    public String price;

    public Coffee toEntity() {
        return new Coffee(id, name, price);
    }
}
