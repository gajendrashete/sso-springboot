package com.citi.gfts.base.multiauth.db.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "APPUSERS")
//
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
 
    @Id
    @GeneratedValue
    private Long id;
 
    private String username;
    private String password;
 
    //@OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    //private List<Role> roles = new ArrayList<>();

    // Roles could be separate table, but csv string here for brevity 
    private String roles; 
}
 

@Entity
@Table(name = "APPROLES")
//
@Data
@AllArgsConstructor
@NoArgsConstructor
class AppRole {
 
    @Id
    @GeneratedValue
    private Long id;
 
    private String roleName;

}