package com.uday.novapay.dto.response;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegisterResponse {
    private long id;
    private String fullName;
    private String email;
    private String phoneNumber;
  private String message;
}
