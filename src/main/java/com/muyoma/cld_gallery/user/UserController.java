package com.muyoma.cld_gallery.user;

import com.muyoma.cld_gallery.jwt.JwtUtil;
import com.muyoma.cld_gallery.media.collections.CollectionService;
import com.muyoma.cld_gallery.user.UserAuthModels.UserLogin;
import com.muyoma.cld_gallery.user.UserAuthModels.UserLoginResponse;
import com.muyoma.cld_gallery.user.UserAuthModels.UserRegister;
import com.muyoma.cld_gallery.user.UserAuthModels.UserVerify;
import com.muyoma.grpc.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.beans.factory.annotation.Autowired;

@GrpcService
public class UserController extends UserServiceGrpc.UserServiceImplBase {

    private final UserServices userServices;
    private final CollectionService collectionService;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserController(UserServices userServices, JwtUtil jwtUtil, CollectionService collectionService) {
        this.userServices = userServices;
        this.jwtUtil = jwtUtil;
        this.collectionService = collectionService;
    }

    @Override
    public void createUser(CreateUserRequest request, StreamObserver<CreateUserResponse> responseObserver) {
        User user = new User(
                request.getFirstName(),
                request.getLastName(),
                request.getPhoneNumber(),
                request.getEmail(),
                request.getPassword(),
                request.getValidationCode(),
                request.getVerificationStatus()
        );

        boolean success = userServices.addUser(user);

        if (success) {
            // Create default collection for the new user
            collectionService.createCollection("Default", "Default", user);
        }

        CreateUserResponse response = CreateUserResponse.newBuilder()
                .setId(user.getId() != null ? user.getId() : 0)
                .setMessage(success ? "User created successfully" : "User creation failed")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void login(LoginRequest request, StreamObserver<LoginResponse> responseObserver) {
        UserLogin login = new UserLogin(request.getEmail(), request.getPassword());
        var loginResponse = userServices.login(login);

        if (loginResponse != null) {
            User user = userServices.findUserByEmail(request.getEmail());
            LoginResponse response = LoginResponse.newBuilder()
                    .setSuccess(true)
                    .setMessage("Login successful")
                    .setToken(loginResponse.token)
                    .setUser(buildUserMessage(user))
                    .build();
            responseObserver.onNext(response);
        } else {
            LoginResponse response = LoginResponse.newBuilder()
                    .setSuccess(false)
                    .setMessage("Invalid email or password")
                    .build();
            responseObserver.onNext(response);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void verifyCode(VerifyCodeRequest request, StreamObserver<VerifyCodeResponse> responseObserver) {
        boolean verified = userServices.validateVerificationCode(request.getEmail(), request.getCode());

        VerifyCodeResponse response = VerifyCodeResponse.newBuilder()
                .setVerified(verified)
                .setMessage(verified ? "Verification successful" : "Invalid verification code")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(GetUserRequest request, StreamObserver<UserResponse> responseObserver) {
        User user = userServices.findUserByEmail(request.getEmail());
        if (user != null) {
            UserResponse response = UserResponse.newBuilder()
                    .setUser(buildUserMessage(user))
                    .build();
            responseObserver.onNext(response);
        } else {
            responseObserver.onError(new RuntimeException("User not found"));
        }
        responseObserver.onCompleted();
    }

    private com.muyoma.grpc.User buildUserMessage(User user) {
        return com.muyoma.grpc.User.newBuilder()
                .setId(user.getId() != null ? user.getId() : 0)
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .setPhoneNumber(user.getPhoneNumber())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .setValidationCode(user.getValidationCode())
                .setVerificationStatus(user.getVerificationStatus())
                .build();
    }
}
