package com.onion.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.onion.entity.Node;
import lombok.Data;

import java.util.Collection;

@Data
public class RegisterUser {
    @JsonProperty("Node")
    private Collection<Node> node;

    @JsonProperty("Depot")
    private Collection<Node> depot;

}
