package com.tendo.service;

import com.tendo.dto.BundleResource;

import java.util.Optional;
import java.util.UUID;

public interface ExternalService {

    Optional<BundleResource> getBundle(UUID uuid);
}
