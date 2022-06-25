package com.onion.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.onion.LocalSearchService;
import com.onion.entity.Depot;
import com.onion.entity.ListNode;
import com.onion.entity.Node;
import com.onion.entity.Vehicle;
import com.onion.repository.DepotRepository;
import com.onion.repository.VehicleRepository;
import com.onion.request.LSRequest;
import org.json.simple.parser.ParseException;
import org.springframework.asm.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping(path="api/localsearch")
public class LocalSeachAPI {

    @Autowired
    private DepotRepository depotRepository;

    @Autowired
    LocalSearchService localSearchService;

    @Autowired
    VehicleRepository vehicleRepository;

//    @Autowired
//    CustomerRepository customerRepository;

    @PostMapping("/add/depot")
    public Depot AddDepot(@RequestBody Depot depot){
        return depotRepository.save(depot);
    }

    @GetMapping("/test")
    public String test() throws IOException, ParseException {
       System.out.println(localSearchService.KhoangCachDuongDi("Nhà Thờ Đức Bà","Dinh Độc Lập"));
        return "hello";
    }

    @PostMapping("/add/location")
    public ArrayList<Vehicle> addLocation(@RequestBody LSRequest lsRequest) throws Exception{
        ArrayList<Node> node = new ArrayList<>();
        Node depot = new Node();
        int i = 1;
        for(Node nodes : lsRequest.getNode()){
            Node node1 = new Node();
            node1.setId_customer(i);
            node1.setAddress(nodes.getAddress());
            node1.setLat(nodes.getLat());
            node1.setLng(nodes.getLng());
            node1.setDemand(nodes.getDemand());
            i++;
            node.add(node1);
            //System.out.println(node);
            //node1.setId_customer(lsRequest.getNode());
        }

        for(Node nodes1 : lsRequest.getDepot()){
            depot.setId_customer(nodes1.getId_customer());
            depot.setAddress(nodes1.getAddress());
            depot.setLng(nodes1.getLng());
            depot.setLat(nodes1.getLat());
            depot.setDemand(nodes1.getDemand());
        }
        System.out.println("depot : " +depot);
        System.out.println("nodes" + node);


//        for(Depot depots : lsRequest.getDepot()){
//            Depot depot = new Depot();
//            depot.setId_depot(depots.getId_depot());
//            depot.setAddress(depots.getAddress());
//            depot.setLng(depots.getLng());
//            depot.setLat(depots.getLat());
//            depot.setDemand(depots.getDemand());
//        }
        ArrayList<Vehicle> vehicle = localSearchService.init(node,depot,lsRequest.getSoluongxe());
        System.out.println("xe" + vehicle);
        for(Vehicle vehicle1 : vehicle){
            if(vehicle1.getCost() > 0){
                Vehicle updateVehicle = new Vehicle();
                updateVehicle.setId_vehicle(vehicle1.getId_vehicle());
                updateVehicle.setCapacity(vehicle1.getCapacity());
                updateVehicle.setCost(vehicle1.getCost());
                updateVehicle.setLoading(vehicle1.getLoading());
                updateVehicle.setStatus(true);
                vehicleRepository.save(updateVehicle);
            }

        }
        return vehicle;
    }

//    @PostMapping("/refresh")
//    public String RefreshDriver(){
//        List<Vehicle> vehicles = vehicleRepository.findAll();
//        for(Vehicle vehicle : vehicles){
//            vehicle.setStatus(false);
//            Vehicle updateVehicle = vehicleRepository.save(vehicle);
//        }
//        return "Refresh status thành công";
//    }




//    @PostMapping("/update/route")
//    public String updateRoute(){
//        localSearchService.LocalSearchInterandInstra();
//        return "complete";
//    }
}
