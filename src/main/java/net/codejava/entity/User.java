package net.codejava.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import net.codejava.entity.enums.Provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Setter
@Getter
@Entity
@ToString
@Table(name = "users")
public class User implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true)
	private String username;

	private String password;

	private boolean enabled;

	private boolean accountNonExpired = true;

	private boolean accountNonLocked = true;

	private boolean credentialsNonExpired = true;
	
	@Enumerated(EnumType.STRING)
	private Provider provider;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Role> roles = this.getRoles();
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();

		for (Role role : roles)
			authorities.add(new SimpleGrantedAuthority(role.getName()));

		return authorities;
	}
}
