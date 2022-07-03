package com.messenger.data.repositories;

import com.messenger.data.entities.Messenger;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessengerRepository extends JpaRepository<Messenger, Long> {
}
