package com.punjab.elasticsearch.autocomplete.config;

import lombok.AllArgsConstructor;
import org.elasticsearch.common.inject.spi.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.index.AliasAction;
import org.springframework.data.elasticsearch.core.index.AliasActionParameters;
import org.springframework.data.elasticsearch.core.index.AliasActions;
import org.springframework.data.elasticsearch.core.index.PutTemplateRequest;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TemplateInitializer {
    private static final String TEMPLATE_NAME = "msg-template";
    private static final String TEMPLATE_PATTERN = "msg-*";

    private final ElasticsearchOperations operations;

    @Autowired
    public void setup() {
        var indexOps = operations.indexOps(Message.class);
        if (!indexOps.existsTemplate(TEMPLATE_NAME)) {
            var mapping = indexOps.createMapping();
            var aliasActions = new AliasActions().add(new AliasAction.Add(AliasActionParameters.builderForTemplate()
                            .withAliases(indexOps.getIndexCoordinates().getIndexNames())
                            .build())
            );
            var request = PutTemplateRequest.builder(TEMPLATE_NAME, TEMPLATE_PATTERN)
                    .withMappings(mapping)
                    .withAliasActions(aliasActions)
                    .build();
            indexOps.putTemplate(request);
        }
    }
}
