package io.kwangsik.loginrestapi.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BaseService {
    private ObjectMapper objectMapper;

    @Autowired
    private void setObjectMapper(ObjectMapper newObjectMapper) {
        this.objectMapper = newObjectMapper;
    }

    protected <T> T map(Object source, Class<T> target) {
        return this.objectMapper.convertValue(source, target);
    }

    protected <T> List<T> mapList(List<?> sources, @SuppressWarnings("SameParameterValue") Class<T> target) {
        final List<T> result = new ArrayList<>();

        if (!sources.isEmpty()) {
            for (Object source : sources) {
                result.add(map(source, target));
            }
        }
        return result;
    }
}