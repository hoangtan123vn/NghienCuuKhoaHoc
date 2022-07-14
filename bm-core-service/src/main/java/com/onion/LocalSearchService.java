package com.onion;

import com.google.maps.model.DistanceMatrix;
import com.onion.entity.*;
import com.onion.repository.DepotRepository;
import com.onion.repository.VehicleRepository;
import lombok.var;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class LocalSearchService {
    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    DepotRepository depotRepository;

    public ArrayList<Vehicle> init(ArrayList<Node> customers,Node depot,int Capacity) throws IOException, ParseException {
        //xe được lấy trong trong database (user)
        List<Vehicle> vehicles = vehicleRepository.getVehiclesByStatusFalse();
        List<Vehicle> vehiclesCapacity = new ArrayList<>();
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleType().getCapacity() == Capacity){
                vehiclesCapacity.add(vehicle);
            }
            else if(Capacity == 1){
                vehiclesCapacity.add(vehicle);
            }
        }
        System.out.println(vehiclesCapacity);


        //khởi tạo kho hàng
    //    Node depot = new Node(0,"Nhà Thờ Đức Bà Sài Gòn",10.7797908,106.6968302,0);
        //khởi tạo các khách hàng
//        ArrayList<Node> customers = new ArrayList<>();
//        Node customer1 = new Node(1,"Công viên Hoàng Văn Thụ",10.801711,106.665989,25);
//        Node customer2 = new Node(2,"Phố đi bộ Bùi Viện",10.767318,106.69424,20);
//        Node customer3 = new Node(3,"KEM SỐ 1 TRÀNG TIỀN",10.786834,106.665364,35);
//        Node customer4 = new Node(4,"Trường Đại học Công nghệ TP.HCM - HUTECH",10.801879,106.715198,5);
//        Node customer5 = new Node(5,"Lotte Cinema Cong Hoa",10.8013047,106.6535187,50);
//        Node customer6 = new Node(6,"Trường Đại học Sài Gòn",10.7599171,106.6800696,27);
//        Node customer7 = new Node(7,"AEON MALL Tan Phu Celadon",10.8008761,106.6168497,11);
//        Node customer8 = new Node(8,"Trường Đại học Kinh Tế TP.HCM - Cơ sở B",10.7610532,106.6661693,24);
//        Node customer9 = new Node(9,"Công Viên Gia Định",10.8109725,106.6741247,40);
//        Node customer10 = new Node(10,"Lotte Mart Go Vap",10.8384502,106.6712387,50);
//        Node customer11 = new Node(11,"Công ty TNHH Nhà Máy Bia Heineken Việt Nam",10.8666538,106.6465396,10);
//        Node customer12 = new Node(12,"Chợ Tân Chánh Hiệp",10.8537351,106.6174115,29);
//        Node customer13 = new Node(13,"Dinh Độc Lập",10.7710473,106.6965524,23);
//        Node customer14 = new Node(14,"Chợ Hạnh Thông Tây",10.8352924,106.6588331,62);
//        Node customer15 = new Node(15,"Siêu thị Bách hóa XANH 26/1 Nguyễn Thị Búp",10.8751563,106.6241216,38);
//        customers.add(customer1);
//        customers.add(customer2);
//        customers.add(customer3);
//        customers.add(customer4);
//        customers.add(customer5);
//        customers.add(customer6);
//        customers.add(customer7);
//        customers.add(customer8);
//        customers.add(customer9);
//        customers.add(customer10);
//        customers.add(customer11);
//        customers.add(customer12);
//        customers.add(customer13);
//        customers.add(customer14);
//        customers.add(customer15);
//        System.out.println(KhoangCachDuongDi("Nhà Thờ Đức Bà Sài Gòn","Trường Đại Học Sài Gòn"));


        ArrayList <Node> nodes = new ArrayList<Node>();
        nodes.add(depot);
        for(int i=0;i< customers.size();i++){
            Node customer = customers.get(i);
            nodes.add(customer);
        }

        Solution s = new Solution();
        ArrayList <Vehicle> rtList = s.vehicles;




        for(int i=0;i< vehiclesCapacity.size();i++){
                rtList.add(vehiclesCapacity.get(i));
                //System.out.println(vehicles.get(i).isStatus());
                System.out.println(rtList);
        }
//        for(Vehicle vehicle : vehicles){
//            rtList.add(vehicle);
//            System.out.println(rtList);
//        }
        //Cho tất cả khách hàng là chưa được tính trong tuyến đường
        for (int i = 0 ; i < customers.size(); i++)
        {
            customers.get(i).isRouted = false;
        }



//
//        //TINH KHOANG CACH
//        for (int i = 0 ; i < nodes.size(); i++)
//        {
//            Node from = nodes.get(i);
//            System.out.println("from " + from.address);
//
//            for (int j = 0 ; j < nodes.size(); j++)
//            {
//                Node to = nodes.get(j);
//                System.out.println("to " + to.address);
//
////                double Delta_x = (from.x - to.x);
////                double Delta_y = (from.y - to.y);
////                double distance = Math.sqrt((Delta_x * Delta_x) + (Delta_y * Delta_y));
//                Long distance = KhoangCachDuongDi(from.getAddress(), to.getAddress());
//
//              //  distance = Math.round(distance);
//
//              //  distanceMatrix[i][j] = distance;
//                System.out.println(distance);
//
//            }
//        }
//        System.out.println("Vehicle size : " + vehicles.size());
//        System.out.println("rtList  : " + rtList);
//        System.out.println("customers  : " + customers);
//        System.out.println("depot : " + depot);
//        System.out.println("rtList  : " + s);
        System.out.println(customers);
        LocalSearch(vehiclesCapacity.size(), rtList,customers,depot,s);
      //  LocalSearchInterandInstra(vehiclesCapacity.size(),rtList,s);
        for (int j=0; j< vehiclesCapacity.size(); j++)
        {
            int vehicle_number = j+1;
            System.out.println("Route for Vehicle #" + vehicle_number);
            for (int k=0; k<s.vehicles.get(j).nodes.size(); k++)
            {
                System.out.print(s.vehicles.get(j).nodes.get(k).getIdnode() + "  ");
            }
            System.out.println("");
            System.out.println("Route Cost = " + s.vehicles.get(j).cost);
            System.out.println("Final Load: " + s.vehicles.get(j).loading);
            System.out.println("Final Remaining Capacity = " + (rtList.get(j).getVehicleType().getCapacity() - s.vehicles.get(j).loading));
            System.out.println("----------------------------------------");

        }
        System.out.println("Total Solution Cost = " + s.cost);
      //  System.out.println(s.vehicles);
     //   LocalSearchInterandInstra(vehicles.size(),rtList,s);
        return s.vehicles;

    }

    public Long KhoangCachDuongDi(String source,String destination) throws IOException, ParseException {
        String key = "AIzaSyC3Z_A5IhAZ7tmJ6aJuk3XLPniHqZDijjk";
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url("https://maps.googleapis.com/maps/api/distancematrix/json?origins="+source+"&destinations="+destination+"&key="+key)
                .method("GET", null)
                .build();
        var response = client.newCall(request).execute().body().string();
        JSONParser jp = new JSONParser();
        JSONObject jo = (JSONObject) jp.parse(response);
        JSONArray ja = (JSONArray) jo.get("rows");
        jo = (JSONObject) ja.get(0);
        ja = (JSONArray) jo.get("elements");
        jo = (JSONObject) ja.get(0);
        JSONObject distance = (JSONObject) jo.get("distance");
        JSONObject duration = (JSONObject) jo.get("duration");
        Long value_distance = (Long) distance.get("value");
        return value_distance;
    }


    public void LocalSearch(int numberofVehicles,ArrayList<Vehicle> rtList,ArrayList<Node> customers, Node depot,Solution s) throws IOException, ParseException {
        int notInserted = customers.size();
        for (int j=0; j < numberofVehicles; j++)
        {
            ArrayList<Node> nodeSequence = rtList.get(j).nodes;
            nodeSequence.add(depot);
            int capacity = rtList.get(j).getVehicleType().getCapacity(); // Sức chứa của mỗi xe (=50)
            int load = rtList.get(j).loading; // Tải trọng ban đầu của chiếc xe (=0)
            boolean isFinal = false; // Thiết lập giá trị boolen để kiểm tra tuyến đường đã xong hay chưa
            // Nếu như không còn điểm giao hàng nào nữa,điểm kết thúc là kho hàng sẽ được thêm vào
            if (notInserted == 0) {
                isFinal = true;
                nodeSequence.add(depot);
            }
            while (isFinal == false)
            {
                int positionOfTheNextOne = -1; //đây sẽ là vị trí của điểm giao hàng gần nhất - khởi tạo thành -1
                // This will hold the minimal cost for moving to the next customer - initialized to something very large
                Long bestCostForTheNextOne = Long.MAX_VALUE;
                //Đây là khách hàng cuối cùng của tuyến (hoặc là kho nếu tuyến còn trống)
                Node lastInTheRoute = nodeSequence.get(nodeSequence.size() - 1);
                for (int k = 0 ; k < customers.size(); k++) //Xác định khách hàng không định tuyến gần nhất
                {
                    Node candidate = customers.get(k); // Khách hàng được kiểm tra được gọi là ứng viên
                  //  System.out.println("candidate :" + candidate);
                    if (candidate.isRouted == false){// nếu ứng cử viên này chưa được xe đến thăm
                        ////Đây là chi phí để chuyển từ khách hàng đã ghé thăm cuối cùng sang khách hàng ứng viên
                        //double trialCost = distanceMatrix[lastInTheRoute.ID][candidate.ID];
                        Long trialCost = KhoangCachDuongDi(lastInTheRoute.getAddress(),candidate.getAddress());
                        // Nếu đây là chi phí tối thiểu được tìm thấy cho đến hiện giờ -> lưu trữ chi phí này và vị trí của ứng viên tốt nhất này
                        if (trialCost < bestCostForTheNextOne && candidate.demand<= capacity)
                        {
                            positionOfTheNextOne = k;
                            bestCostForTheNextOne = trialCost;
                        }
                    }
                } //Di chuyển tới khách hàng tiếp theo
                if (positionOfTheNextOne != -1 )
                // Step 2: Push the customer in the solution
                // We have found the customer to be pushed.
                // He is located in the positionOfTheNextOne position of the customers list
                // Let's inert him and update the cost of the solution and of the route, accordingly
                {
                    Node insertedNode = customers.get(positionOfTheNextOne);
                    nodeSequence.add(insertedNode); //Push the customer in the sequence
                    rtList.get(j).cost = rtList.get(j).cost + bestCostForTheNextOne;
                    s.cost = s.cost + bestCostForTheNextOne;
                    insertedNode.isRouted = true;
                    capacity = capacity - insertedNode.demand;
                    rtList.get(j).loading = load + insertedNode.demand;
                    load = load + insertedNode.demand;
                    notInserted = notInserted - 1;
                } else
                {
                    //Nếu như biến the positionOfTheNextOne = -1, Có nghĩa là không còn khách hàng cho chieexc xe này này nữa. nên chúng ta thêm kho hàng vào
                    nodeSequence.add(depot);
                   // rtList.get(j).cost = rtList.get(j).cost + distanceMatrix[lastInTheRoute.ID][0];
                    rtList.get(j).cost = rtList.get(j).cost + KhoangCachDuongDi(lastInTheRoute.getAddress(), depot.getAddress());
                   // s.cost = s.cost + distanceMatrix[lastInTheRoute.ID][0];
                    s.cost = s.cost + KhoangCachDuongDi(lastInTheRoute.getAddress(),depot.getAddress());
                    isFinal = true;
                }
            }
        }



    }
    public void LocalSearchInterandInstra(int numberofVehicles,ArrayList<Vehicle> rtList,Solution s) throws IOException, ParseException {

        //this is a boolean flag (true/false) for terminating the local search procedure
        boolean terminationCondition = false;

        //this is a counter for holding the local search iterator
        int localSearchIterator = 0;

        //Here we apply the best relocation move local search scheme
        //This is an object for holding the best relocation move that can be applied to the candidate solution
        RelocationMove rm = new RelocationMove(); // in order to apply one relocation  move for all routes - dont want to lose previous if i change vehicle

        //Initialize the relocation move rm
        rm.positionOfRelocated = -1;
        rm.positionToBeInserted = -1;
        rm.fromRoute = 0;
        rm.toRoute = 0;
        rm.fromMoveCost = Long.MAX_VALUE;
        rm.toMoveCost = Long.MAX_VALUE;

        // Until the termination condition is set to true repeat the following block of code
        while (terminationCondition == false)
        {
            //With this function we look for the best relocation move
            //the characteristics of this move will be stored in the object rm
            findBestRelocationMove(rm, s, numberofVehicles);

            // If rm (the identified best relocation move) is a cost improving move, or in other words
            // if the current solution is not a local optimum
            if (rm.moveCost < 0)
            {
                //This is a function applying the relocation move rm to the candidate solution
                applyRelocationMove(rm, s);
            }
            else
            {
                //if no cost improving relocation move was found,
                //or in other words if the current solution is a local optimum
                //terminate the local search algorithm
                terminationCondition = true;
            }

            localSearchIterator = localSearchIterator + 1;
        }


        /*
         * ###########
         * # RESULTS #
         * ###########
         * */


        System.out.println("");
        for (int j=0; j<numberofVehicles; j++)
        {
            int vehicle_number = j+1;
            System.out.println("Updated Route for Vehicle #" + vehicle_number);
            for (int k=0; k<s.vehicles.get(j).nodes.size(); k++)
            {
                System.out.print(s.vehicles.get(j).nodes.get(k).getIdnode() + "  ");
            }
            System.out.println("");
            System.out.println("Route Cost = " + s.vehicles.get(j).cost);
            System.out.println("Final Load: " + s.vehicles.get(j).loading);
            System.out.println("Final Remaining Capacity = " + (rtList.get(j).getVehicleType().getCapacity() - s.vehicles.get(j).loading));
            System.out.println("----------------------------------------");

        }
        System.out.println("Total Solution Cost = " + s.cost);
        System.out.println("Total Local Search Iterations = " + localSearchIterator);
        System.out.println("Total Local Search Relocations = " + (localSearchIterator-1));
    }

    private void findBestRelocationMove(RelocationMove rm, Solution s, int numberOfVehicles) throws IOException, ParseException {
        //This is a variable that will hold the cost of the best relocation move
        double bestMoveCost = Double.MAX_VALUE;

        //We will iterate through all available vehicles

        //Vehicles to relocate FROM
        for (int from = 0; from<numberOfVehicles; from++)
        {
            // Vehicles to relocate TO
            for (int to = 0; to<numberOfVehicles; to++)
            {

                for (int fromIndex = 1; fromIndex < s.vehicles.get(from).nodes.size() - 1; fromIndex++)
                {
                    //Node A is the predecessor of B
                    Node A = s.vehicles.get(from).nodes.get(fromIndex - 1);

                    //Node B is the relocated node
                    Node B = s.vehicles.get(from).nodes.get(fromIndex);

                    //Node C is the successor of B
                    Node C = s.vehicles.get(from).nodes.get(fromIndex + 1);

                    System.out.println("Node A " + A );
                    System.out.println("Node B " + A );
                    System.out.println("Node C " + A );

                    //We will iterate through all possible re-insertion positions for B
                    for (int afterIndex = 0; afterIndex < s.vehicles.get(to).nodes.size() -1; afterIndex ++)
                    {

                        if ((afterIndex != fromIndex && afterIndex != fromIndex - 1)||from != to)
                        {
                            //Node F the node after which B is going to be reinserted
                            Node F = s.vehicles.get(to).nodes.get(afterIndex);
                            System.out.println("Node F " + F );
                            //Node G the successor of F
                            Node G = s.vehicles.get(to).nodes.get(afterIndex + 1);
                            System.out.println("Node G " + G );

                            //The arcs A-B, B-C, and F-G break
                            //double costRemovedFrom = distanceMatrix[A.ID][B.ID] + distanceMatrix[B.ID][C.ID];
                            Long costRemovedFrom = KhoangCachDuongDi(A.getAddress(),B.getAddress()) + KhoangCachDuongDi(B.getAddress(),C.getAddress());;
                            Long costRemovedTo = KhoangCachDuongDi(F.getAddress(),G.getAddress());
                            //double costRemovedTo = distanceMatrix[F.ID][G.ID];

                            //The arcs A-C, F-B and B-G are created
                          //  double costAddedFrom = distanceMatrix[A.ID][C.ID];
                            Long costAddedFrom = KhoangCachDuongDi(A.getAddress(),C.getAddress());
                         //   double costAddedTo  = distanceMatrix[F.ID][B.ID] + distanceMatrix[B.ID][G.ID];
                            Long costAddedTo = KhoangCachDuongDi(F.getAddress(),B.getAddress()) + KhoangCachDuongDi(B.getAddress(),G.getAddress());;
                            //This is the cost of the move, or in other words
                            //the change that this move will cause if applied to the current solution
                            Long fromMoveCost = costAddedFrom - costRemovedFrom;
                            Long toMoveCost = costAddedTo - costRemovedTo;
                            System.out.println(s.vehicles.get(to).loading);
                            System.out.println(s.vehicles.get(from).nodes.get(fromIndex).demand);
                            System.out.println(s.vehicles.get(to).getVehicleType().getCapacity());
                            //If this move is the best found so far
                            Long moveCost = fromMoveCost+toMoveCost;
                            if ((moveCost < bestMoveCost)&&(from == to || (s.vehicles.get(to).loading + s.vehicles.get(from).nodes.get(fromIndex).demand<=s.vehicles.get(to).getVehicleType().getCapacity())))
                            {
                                //set the best cost equal to the cost of this solution
                                bestMoveCost = moveCost;

                                //store its characteristics
                                rm.positionOfRelocated = fromIndex;
                                rm.positionToBeInserted = afterIndex;
                                rm.toMoveCost = toMoveCost;
                                rm.fromMoveCost = fromMoveCost;
                                rm.fromRoute = from;
                                rm.toRoute = to;
                                rm.moveCost = moveCost;
                                if (from != to) {
                                    rm.fromUpdLoad = s.vehicles.get(from).loading - s.vehicles.get(from).nodes.get(fromIndex).demand;
                                    rm.toUpdLoad = s.vehicles.get(to).loading + s.vehicles.get(from).nodes.get(fromIndex).demand;
                                }
                                else {
                                    rm.fromUpdLoad = s.vehicles.get(from).loading;
                                    rm.toUpdLoad = s.vehicles.get(to).loading;
                                }


                            }
                        }
                    }
                }
            }
        }
    }


    private static void applyRelocationMove(RelocationMove rm, Solution s)
    {
        //This is the node to be relocated
        Node relocatedNode = s.vehicles.get(rm.fromRoute).nodes.get(rm.positionOfRelocated);

        //Take out the relocated node
        s.vehicles.get(rm.fromRoute).nodes.remove(rm.positionOfRelocated);

        //Reinsert the relocated node into the appropriate position
        //Where??? -> after the node that WAS (!!!!) located in the rm.positionToBeInserted of the route

        //Watch out!!!
        //If the relocated customer is reinserted backwards we have to re-insert it in (rm.positionToBeInserted + 1)
        if (((rm.positionToBeInserted < rm.positionOfRelocated) && (rm.toRoute == rm.fromRoute))||(rm.toRoute!=rm.fromRoute))
        {
            s.vehicles.get(rm.toRoute).nodes.add(rm.positionToBeInserted + 1, relocatedNode);
        }
        ////else (if it is reinserted forward) we have to re-insert it in (rm.positionToBeInserted)
        else
        {
            s.vehicles.get(rm.toRoute).nodes.add(rm.positionToBeInserted, relocatedNode);
        }

        System.out.println("FROM: Vehicle #" + (rm.fromRoute+1) + " Position: " + (rm.positionOfRelocated+1) + " --> Updated Load = " + rm.fromUpdLoad);
        System.out.println("TO:   Vehicle #" + (rm.toRoute+1) + " Position: " + (rm.positionToBeInserted+1) + " --> Updated Load = " + rm.toUpdLoad);
        System.out.println("--------------------------------------------------");


        //update the cost of the solution and the corresponding cost of the route object in the solution
        s.cost = s.cost + rm.moveCost;
        s.vehicles.get(rm.toRoute).cost = s.vehicles.get(rm.toRoute).cost + rm.toMoveCost;
        s.vehicles.get(rm.fromRoute).cost = s.vehicles.get(rm.fromRoute).cost + rm.fromMoveCost;
        if  (rm.toRoute != rm.fromRoute) {
            s.vehicles.get(rm.toRoute).loading = rm.toUpdLoad;
            s.vehicles.get(rm.fromRoute).loading = rm.fromUpdLoad;
        }
        else {
            s.vehicles.get(rm.toRoute).loading = rm.toUpdLoad;
        }

    }


}
