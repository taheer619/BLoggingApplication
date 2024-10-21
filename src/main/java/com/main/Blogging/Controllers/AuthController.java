package com.main.Blogging.Controllers;

import java.security.Principal;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.Blogging.Payloads.UserDto;
import com.main.Blogging.Repositories.UserRepository;
import com.main.Blogging.Services.UserService;
import com.main.Blogging.blog.security.JwtTokenHelper;
import com.main.Blogging.model.JwtRequest;
import com.main.Blogging.model.JwtResponse;
import com.main.Blogging.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private JwtTokenHelper jwtTokenHelper;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ModelMapper mapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> createToken(@RequestBody JwtRequest request) throws Exception {
        
        // Log the received request
        System.out.println("Received request: " + request);
        System.out.println("Request Email: " + request.getEmail());
        System.out.println("Request Password: " + request.getPassword());

        // Authenticate the user first
        this.authenticate(request.getEmail(), request.getPassword());

        // Load user details after successful authentication
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.jwtTokenHelper.generateToken(userDetails);

        // Build the response with the token and username
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String username, String password) throws Exception {
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(username, password);        
        try {
            // Authenticate the user with the provided username and password
            this.authenticationManager.authenticate(authenticationToken);
        } 
        catch (BadCredentialsException e) {
            System.out.println("Invalid Credentials !!");
            throw new Exception("Invalid username or password !!");
        }
    }


    @GetMapping("/current-user/")
    public ResponseEntity<UserDto> getUser(Principal principal) {
        User user = this.userRepo.findByEmail(principal.getName()).get();
        return new ResponseEntity<>(this.mapper.map(user, UserDto.class), HttpStatus.OK);
    }
    
    @PostMapping("/register")
    public ResponseEntity<UserDto> registerNewUser(@Valid @RequestBody UserDto userDto){
    	UserDto registeredUser= userService.registerUser(userDto);
    	return new  ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
    }
}
