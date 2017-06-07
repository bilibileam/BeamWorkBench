package com.beam.archtec.manager.config;

import java.util.Collection;

import com.beam.archtec.dto.CodeEntity;

public interface BasicConfigManager<T extends CodeEntity> {

    void updateConfig(Collection<T> configs);

    Collection<T> getConfig();

}
