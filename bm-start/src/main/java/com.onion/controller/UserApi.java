package com.onion.controller;

import com.onion.config.jwt.UserDetailsServiceImpl;
import com.onion.UserService;
import com.onion.config.jwt.JwtTokenProvider;
import com.onion.entity.*;
import com.onion.repository.HistoryRouteRepository;
import com.onion.repository.UserRepository;
import com.onion.repository.VehicleRepository;
import com.onion.request.LoginRequest;
import com.onion.request.RequestIdVehicle;
import com.onion.respone.JwtAuthenticationRespone;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLDataException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path="api/auth")
@RequiredArgsConstructor
public class UserApi {
    @Autowired
    private UserRepository userRepository;

    private final UserService userService;

    private final UserDetailsServiceImpl userDetailsServiceimpl;

    @Autowired
    private VehicleRepository vehicleRepository;


    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    HistoryRouteRepository historyRouteRepository;

//    @PostMapping("/register")
//    public User Register(@RequestBody User user){
//        return userDetailsServiceimpl.save(user);
//    }

    @PostMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest loginRequest) throws Exception {
        try {
            System.out.println(loginRequest);
            /* Authentication authentication =*/
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            //  SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            throw new Exception("Loi ", e);
        }
        final UserDetails userDetails = userDetailsServiceimpl
                .loadUserByUsername(loginRequest.getUsername());

        System.out.println(userDetails);
        final String token = jwtTokenProvider.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationRespone(token));
    }

    @GetMapping("/admin")
    public String forAdmin() {
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String forUser() {
        return "Hello User";
    }

    @GetMapping("/users")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    /* public ResponseEntity<List<User>> getAllUsers(){
         return ResponseEntity.ok().body(userService.getAllUser());
     }*/
    @GetMapping("/userinfo")
    public Optional<User> getUser(@AuthenticationPrincipal UserDetails user) {
        String username = user.getUsername();
        Optional<User> user123 = userRepository.findByUsername(username);
        return user123;
    }

    @GetMapping("/context")
    public UserDetails getCurrentUserContext() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return ((UserDetails) authentication.getPrincipal());
    }

    @PostMapping("/register")
    public User addUser(@RequestBody User newUser) throws Exception {
        User user = new User(newUser.getUsername(), passwordEncoder.encode(newUser.getPassword()), newUser.getFullname(), newUser.getAge(), newUser.getAddress(), newUser.getPhonenumber());
        Role role = new Role(2, "USER");
        Vehicle vehicle = null;
        if(newUser.getVehicle().getId_vehicle() == 1){
            vehicle = new Vehicle(100, 0, false);
        }
        else if(newUser.getVehicle().getId_vehicle() == 2){
            vehicle = new Vehicle(500, 0, false);
        }
        user.setVehicle(vehicle);
        user.setRole(role);
        //vehicle.setUser(user);
        vehicleRepository.save(vehicle);
        return userRepository.save(user);
    }

    @PostMapping("/addvehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) {
        System.out.println(vehicle.toString());
        return vehicleRepository.save(vehicle);
    }

    @PostMapping("/refresh")
    public List<User> RefreshDriver() {
        List<User> users = userService.getAllUser();
        List<User> updatedUser = new ArrayList<>();
        for (User user : users) {
            user.getVehicle().setStatus(false);
            user.getVehicle().setLoading(0);
            user.getVehicle().setCost(0);
            userRepository.save(user);
            updatedUser.add(user);
        }
        return updatedUser;
    }


    @PutMapping("/vehicle/{id}")
    public Vehicle updateVehicle(
            @PathVariable(value = "id") Long id_vehicle, @RequestBody Vehicle vehicle) {
        Vehicle updateVehicle = vehicleRepository.findById(Math.toIntExact(id_vehicle)).orElse(null);
        updateVehicle.setStatus(vehicle.isStatus());
        updateVehicle.setLoading(0);
        updateVehicle.setCost(0);
        return vehicleRepository.save(updateVehicle);
    }

    @GetMapping("/vehicle/{id}")
    public List<HistoryRoutes> GetHistoryRoutes(@PathVariable(value = "id") Long id_vehicle){
        Vehicle findVehicle = vehicleRepository.findById(Math.toIntExact(id_vehicle)).orElse(null);
        System.out.println(findVehicle);
        List<HistoryRoutes> ListhistoryRoutes = historyRouteRepository.findAllByVehicle(findVehicle);
        return ListhistoryRoutes;
    }


    @GetMapping("/gethistoryRoutes/{id}")
    public HistoryRoutes getHistoryRoutes(@PathVariable(value = "id") int id_route){
    HistoryRoutes updateHistoryRoutes = historyRouteRepository.findById(id_route).orElse(null);
    return updateHistoryRoutes;
    }

    @GetMapping("/getIdVehicle")
    public int GetIdVehicle(@RequestBody RequestIdVehicle requestIdVehicle){
        User newuser = userRepository.findByUsername(requestIdVehicle.getUsername()).orElse(null);
        System.out.println(newuser);
        int id_vehicle = newuser.getVehicle().getId_vehicle();
        return id_vehicle;
    }

    @PostMapping("/newhistoryRoutes")
    public HistoryRoutes addHistoryRoutes(@RequestBody HistoryRoutes historyRoutes){
        HistoryRoutes updatehistoryroutes = new HistoryRoutes();
        updatehistoryroutes.setLoading_route(historyRoutes.getLoading_route());
        updatehistoryroutes.setCapacity_route(historyRoutes.getLoading_route());
        updatehistoryroutes.setCost_route(historyRoutes.getCost_route());
        updatehistoryroutes.setTime(historyRoutes.getTime());
        updatehistoryroutes.setVehicle(historyRoutes.getVehicle());
        updatehistoryroutes.setStatus_route(historyRoutes.isStatus_route());
//        for(Node node : historyRoutes.getNodes()) {
//            Node updatedNode = new Node();
//            updatedNode.setId_node(node.getId_node());
//            updatedNode.setLng(node.getLng());
//            updatedNode.setLat(node.getLat());
//        }
        return historyRouteRepository.save(updatehistoryroutes);
    }

//    @GetMapping("/getInfohostoryRoutes/{id}")
//    public HistoryRoutes getInfoHistoryRoutes(@PathVariable(value = "id"),int id_route){
//
//    }


}






