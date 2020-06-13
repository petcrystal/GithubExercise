package com.zlm.project.connect;

import com.zlm.project.data.model.api.UserDetailResponse;
import com.zlm.project.data.model.api.UsersResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    // -------------------------------------------

    /**
     * Get user information
     */
    @GET("/users/{username}")
    Observable<UserDetailResponse> loadUserInfo(@Path("username") String username);

    /**
     * Get User list.
     */
    @GET("users")
    Observable<UsersResponse[]> loadUser();


    /**
     * Get user information
     */
    @GET("/users?since={since}&page={page}&per_page={per_page}")
    Observable<UsersResponse[]> loadUser(@Path("since") int since,@Path("page") int page,@Path("per_page") int per_page);

}
