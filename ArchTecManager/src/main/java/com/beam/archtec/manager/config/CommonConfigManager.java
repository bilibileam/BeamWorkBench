package com.beam.archtec.manager.config;

import java.util.Collection;
import java.util.Map;

import com.beam.CoreException;
import com.beam.archtec.column.BasicConfigColumns;
import com.beam.archtec.dto.CodeEntity;

public interface CommonConfigManager<T extends CodeEntity,C extends BasicConfigColumns> extends BasicConfigManager<T> {

    void updateCommonConfig(Collection<Map<String, Object>> configs) throws CoreException;

    Collection<Map<String, Object>> getCommonConfig() throws CoreException;

}
