package dev.obukhov.calendar.service;

import dev.obukhov.calendar.domain.entity.User;
import dev.obukhov.calendar.domain.exception.NotFoundException;
import dev.obukhov.calendar.domain.exception.UserFieldsNotUnique;
import dev.obukhov.calendar.domain.helper.ObjectProperties;
import dev.obukhov.calendar.persistence.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User get(String id) {
        return get(UUID.fromString(id));
    }

    @Override
    public User get(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Override
    public void delete(UUID id) throws NotFoundException {
        throwIfNotExist(id);
        userRepository.deleteById(id);
    }

    @Override
    public User create(User user) throws UserFieldsNotUnique {
        user.setId(null);
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserFieldsNotUnique();
        }
    }

    @Override
    public User update(User user) throws NotFoundException, UserFieldsNotUnique {
        throwIfNotExist(user);
        try {
            return userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new UserFieldsNotUnique();
        }
    }

    @Override
    public User patch(User user) throws NotFoundException, NotFoundException {
        User targetUser = userRepository.findById(user.getId()).orElseThrow(() -> new NotFoundException(user.getId()));
        BeanUtils.copyProperties(user, targetUser , ObjectProperties.getNullFieldNames(user));
        try {
            return userRepository.save(targetUser);
        } catch (DataIntegrityViolationException e) {
            throw new UserFieldsNotUnique();
        }
    }

    private void throwIfNotExist(User user) throws NotFoundException {
        throwIfNotExist(user.getId());
    }

    private void throwIfNotExist(UUID id) throws NotFoundException {
        if (!userRepository.existsById(id))
            throw new NotFoundException(id);
    }

}
