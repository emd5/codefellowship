package com.lizmahoney1.CodeFellowShip;

import com.lizmahoney1.CodeFellowShip.Model.ApplicationUser;
import org.springframework.data.repository.CrudRepository;

public interface AppUserRepository extends CrudRepository<ApplicationUser, Long> {
    ApplicationUser findByUsername(String username);
}
