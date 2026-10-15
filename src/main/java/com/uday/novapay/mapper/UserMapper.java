package com.uday.novapay.mapper;
import com.uday.novapay.dto.request.RegisterRequest;
import com.uday.novapay.entity.User;
import org.springframework.stereotype.Component;
import com.uday.novapay.enums.UserStatus;
import com.uday.novapay.enums.UserRole;


@Component
public class UserMapper {
    public User toEntity(RegisterRequest  request){
        User user = new User();
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhoneNumber(request.getPhoneNumber());

        user.setRole(UserRole.USER);
        user.setStatus(UserStatus.ACTIVE);
        return user;
    }
}
