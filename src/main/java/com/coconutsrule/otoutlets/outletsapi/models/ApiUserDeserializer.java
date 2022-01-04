package com.coconutsrule.otoutlets.outletsapi.models;

import java.io.IOException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class ApiUserDeserializer extends JsonDeserializer<ApiUser> {

    @Override
    public ApiUser deserialize(JsonParser jsonParser, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
       ObjectCodec oc = jsonParser.getCodec();
       JsonNode node = oc.readTree(jsonParser);
       ApiUser user = new ApiUser();
       user.setUsername(node.get("username").asText());
       user.setPassword(node.get("password").asText());

       return user;
    }
    
}
