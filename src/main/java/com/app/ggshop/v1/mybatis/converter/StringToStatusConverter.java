package com.app.ggshop.v1.mybatis.converter;

import com.app.ggshop.v1.common.enumeration.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToStatusConverter implements Converter<String, Status> {
    @Override
    public Status convert(String source) {
        return Status.getStatus(source);
    }
}
