package com.tendo.service;

import com.tendo.dto.Bundle;

import java.util.Optional;
import java.util.UUID;

public interface ExternalService {

    Optional<Bundle> getBundle(UUID uuid);
}
