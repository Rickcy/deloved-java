package ru.deloved

import grails.plugin.springsecurity.userdetails.GrailsUser
import grails.plugin.springsecurity.userdetails.GrailsUserDetailsService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException

class UserDetailsService implements GrailsUserDetailsService {

	static transactional = true

	UserDetails loadUserByUsername(String username, boolean loadRoles) throws UsernameNotFoundException {
		return loadUserByUsername(username);
	}

	UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User.withTransaction { status ->
			log.debug('loadUserByUsername: '+username)
			User user = User.findByUsername(username)
			if (!user) {
				throw new UsernameNotFoundException('Пользователь не найден', username)
			}

			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority(Role.get(user.roleId).authority));

			return new GrailsUser(user.username, user.password, user.enabled, true, true, true, authorities, user.id)
		}
	}
}