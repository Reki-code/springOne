package me.rekii.tacocloud.data;

import org.springframework.data.repository.CrudRepository;

import me.rekii.tacocloud.User;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findByUsername(String username);
}
