package com.example.libraryMangementSystem.services;


import com.example.libraryMangementSystem.dtos.SimpleNameDto;
import com.example.libraryMangementSystem.exceptions.ResourceNotFoundException;
import com.example.libraryMangementSystem.models.Member;
import com.example.libraryMangementSystem.models.User;
import com.example.libraryMangementSystem.models.enums.Roles;
import com.example.libraryMangementSystem.repos.MemberRepo;
import com.example.libraryMangementSystem.repos.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {


    private final MemberRepo memberRepo;
    private final UserRepo userRepo;

    public Member approveUser(Long userId, SimpleNameDto dto){

        User user =userRepo.findById(userId).orElseThrow(
                ()-> new ResourceNotFoundException("this user doesn't exist")
        );
//        if(!user.getRoles().contains(Roles.ROLE_MEMBER)){
//            throw new ResourceNotFoundException("the attached user id not an member");
//        }

        Member member = new Member();
        member.setUser(user);
        member.setNickname(dto.getName());

        return memberRepo.save(member);
    }
}
