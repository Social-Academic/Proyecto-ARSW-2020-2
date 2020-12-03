package edu.escuelaing.arsw.SOCIALACADEMIC.auth;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/js/**").permitAll()
		.antMatchers("/css/**").permitAll()
		.antMatchers("/images/**").permitAll()
		.antMatchers("/fonts/**").permitAll()
		.antMatchers("/webjars/**").permitAll()
		.antMatchers("/stompendpoint/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/edit-profile-basic.html").permitAll()
		.antMatchers("/edit-profile-password.html").permitAll()
		.antMatchers("/edit-profile-work-edu.html").permitAll()
		.antMatchers("/timeline.html").permitAll()
		.antMatchers("/newsfeed-messages.html").permitAll()
		.antMatchers("/timeline-friends.html").permitAll()
		.antMatchers("/timeline-about.html").permitAll()
		.antMatchers(HttpMethod.POST, "/usuarios").permitAll()
		.anyRequest().authenticated();
	}
	
}
