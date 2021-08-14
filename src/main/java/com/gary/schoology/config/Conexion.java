package com.gary.schoology.config;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class Conexion {
    DriverManagerDataSource driverManagerDataSource;
    public Conexion(){
        try {
            driverManagerDataSource = new DriverManagerDataSource();
            driverManagerDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
            driverManagerDataSource.setUrl("jdbc:mysql://localhost/schoology?serverTimezone=UTC");
            driverManagerDataSource.setUsername("root");
            driverManagerDataSource.setPassword("");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public DriverManagerDataSource getConnection(){
        return driverManagerDataSource;
    }
}
