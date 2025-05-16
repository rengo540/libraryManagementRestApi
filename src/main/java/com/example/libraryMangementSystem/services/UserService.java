package com.example.libraryMangementSystem.services;



import com.example.libraryMangementSystem.dtos.UserDto;
import com.example.libraryMangementSystem.exceptions.AlreadyExistsException;
import com.example.libraryMangementSystem.exceptions.ResourceNotFoundException;
import com.example.libraryMangementSystem.models.Role;
import com.example.libraryMangementSystem.models.User;
import com.example.libraryMangementSystem.models.enums.Roles;
import com.example.libraryMangementSystem.repos.RoleRepo;
import com.example.libraryMangementSystem.repos.UserRepo;
import com.example.libraryMangementSystem.requestDtos.CreateUserRequest;
import com.example.libraryMangementSystem.requestDtos.UserUpdateRequest;
import com.example.libraryMangementSystem.security.UserAppDetails;
import com.example.libraryMangementSystem.services.iservices.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getUserById(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(()->new ResourceNotFoundException("user not found"));
    }



    @Override
    public User createUser(CreateUserRequest request, boolean isMember) {

        return Optional.of(request)
                .filter(user->!userRepo.existsByEmail(request.getEmail()))
                .map(us-> {
                    User user =new User();
                    if(isMember){
                        request.setRole("ROLE_MEMBER");
                    }

                    Role role = roleRepo.findByName(Roles.valueOf(request.getRole())).orElseGet(() -> {
                        try {
                            return roleRepo.save(new Role(Roles.valueOf(request.getRole())));
                        }catch (Exception e){
                            throw new ResourceNotFoundException("invalid role "+ request.getRole());
                        }
                    });

                    user.getRoles().add(role);
                    user.setEmail(request.getEmail());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
//                    user.setRoles();
                    return userRepo.save(user);
                }).orElseThrow(()->new AlreadyExistsException("the user already exist"));
    }

    @Override
    public User updateUser(UserUpdateRequest request, Long userId) {
        return userRepo.findById(userId)
                .map(user -> {
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepo.save(user);
                }).orElseThrow(()->new ResourceNotFoundException("user not found"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepo.findById(userId).ifPresentOrElse(userRepo::delete,
                ()->{throw new ResourceNotFoundException("user not exist");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserAppDetails userD= (UserAppDetails) authentication.getPrincipal();
        return userRepo.findByEmail(userD.getEmail())
                .orElseThrow(()->new UsernameNotFoundException("user not found"));
    }


}
