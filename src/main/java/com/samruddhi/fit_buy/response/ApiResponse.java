package com.samruddhi.fit_buy.response;

import com.samruddhi.fit_buy.dto.ImageDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//we are going to use this class to return data from our front end

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ApiResponse {
    private String message;
    private Object data;
}
