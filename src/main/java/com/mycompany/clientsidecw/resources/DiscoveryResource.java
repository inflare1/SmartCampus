package com.mycompany.clientsidecw.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.Map;

@Path("")
public class DiscoveryResource {

    private static final Map<String, Object> discoveryInfo = new HashMap<>();

    static {
        discoveryInfo.put("version", "v1");
        discoveryInfo.put("adminName", "Support Team");
        discoveryInfo.put("adminEmail", "support@myapp.com");

        Map<String, String> resources = new HashMap<>();
        resources.put("rooms", "/api/v1/rooms");
        resources.put("sensors", "/api/v1/sensors");

        discoveryInfo.put("resources", resources);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getDiscovery() {
        return discoveryInfo;
    }
}