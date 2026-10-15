package com.Sailaja .novapay.mapper;
import com.Sailaja .novapay.dto.request.RegisterRequest;
import com.Sailaja .novapay.entity.User;
import org.springframework.stereotype.Component;
import com.Sailaja .novapay.enums.UserStatus;
import com.Sailaja .novapay.enums.UserRole;


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
