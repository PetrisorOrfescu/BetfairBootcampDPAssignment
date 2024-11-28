package com.example.foodDelivery.service;

import com.example.foodDelivery.converter.UserConverter;
import com.example.foodDelivery.dao.UserEntity;
import com.example.foodDelivery.dto.UserDto;
import com.example.foodDelivery.exception.ResourceNotFoundException;
import com.example.foodDelivery.exception.UserAlreadyExistsException;
import com.example.foodDelivery.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void createUser(UserDto userDto) throws UserAlreadyExistsException {

        Optional<UserEntity> existingUser = userRepository.findByPhoneNumber(userDto.getPhoneNumber());
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(String.format("User with Phone Number %s already exists", userDto.getPhoneNumber()));
        }
        UserEntity userToSave = UserConverter.dtoToEntity(userDto);
        userRepository.save(userToSave);
    }

    public UserDto findUser(String phoneNumber) throws ResourceNotFoundException {
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with Phone Number %s does not exist", phoneNumber)));
        return UserConverter.entityToDto(userEntity);

    }

    public boolean deleteUser(String phoneNumber) throws ResourceNotFoundException {
        UserEntity userEntity = userRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("User with Phone Number %s does not exist", phoneNumber)));
        userRepository.deleteById(userEntity.getId());
        return true;
    }

    public boolean updateUser(UserDto userDto) throws ResourceNotFoundException {
        if (userRepository.findByPhoneNumber(userDto.getPhoneNumber()).isEmpty()) {
            throw new ResourceNotFoundException(String.format("User with Phone Number %s does not exist", userDto.getPhoneNumber()));
        } else {
            UserEntity userToUpdate = UserConverter.dtoToEntity(userDto);
            userToUpdate.setId(userRepository.findByPhoneNumber(userDto.getPhoneNumber()).get().getId());
            userRepository.save(userToUpdate);
            return true;
        }
    }

    public List<UserDto> findAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserConverter::entityToDto)
                .toList();
    }
}
