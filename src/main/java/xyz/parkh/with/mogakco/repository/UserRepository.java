package xyz.parkh.with.mogakco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.parkh.with.mogakco.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
}
