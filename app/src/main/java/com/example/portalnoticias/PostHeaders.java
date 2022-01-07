package com.example.portalnoticias;

import com.google.gson.annotations.SerializedName;

// Clase con los atributos y getters y setters necesarios para tratar la informacion devuelta
// por el servicio Rest al realizar la llamada a login.
public class PostHeaders {

    @SerializedName(value = "user")
    private String user;
    @SerializedName(value = "username")
    private String  username;
    @SerializedName(value = "Authorization")
    private String  authorization;
    @SerializedName(value = "apikey")
    private String  apikey;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }


}
