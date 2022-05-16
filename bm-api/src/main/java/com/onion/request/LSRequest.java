package com.onion.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.onion.entity.Depot;
import com.onion.entity.Node;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class LSRequest {
    @JsonProperty("Node")
    private Collection<Node> node;

    @JsonProperty("Depot")
    private Collection<Node> depot;

    @JsonProperty("NumberofVehicles")
    private int Soluongxe;
}
