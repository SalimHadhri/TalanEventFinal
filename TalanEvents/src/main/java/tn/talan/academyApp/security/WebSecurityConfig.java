package tn.talan.academyApp.security;

import java.security.GeneralSecurityException; 
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import tn.talan.academyApp.security.jwt.AuthEntryPointJwt;
import tn.talan.academyApp.security.jwt.AuthTokenFilter;
import tn.talan.academyApp.security.services.UserDetailsServiceImpl;
import tn.talan.academyApp.services.UserEventParticipationService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsService;

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;

	@Autowired
	public UserEventParticipationService userEventParticipationService;

	@Bean
	public AuthTokenFilter authenticationJwtTokenFilter() {
		return new AuthTokenFilter();
	}

	@Override
	public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/**").permitAll()
				.antMatchers("/event/**").permitAll()
		        .anyRequest().authenticated();

		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}



	//calculate every  min
    @Scheduled(cron = "*/30 * * * * *",zone="Africa/Tunis")
	public void eventNotification() throws GeneralSecurityException, MessagingException, ParseException {
		userEventParticipationService.notifyEventParticipators();
	}
    
    private List<Long> pourcentageParticapationEvenementsParMoi= new ArrayList<>() ;
    
    
    //each 1 minute
    @Scheduled(cron = "*/30 * * * * *",zone="Africa/Tunis")
    public void rateEventsParticipationsperMonth() {

        Long rateEvents  = userEventParticipationService.successRateEvents();
        
        if(rateEvents!=null) {
        	
       

        if(pourcentageParticapationEvenementsParMoi.size()<12) {
        	pourcentageParticapationEvenementsParMoi.add(rateEvents);
        }
        else{
        	pourcentageParticapationEvenementsParMoi= new ArrayList<>();
        	pourcentageParticapationEvenementsParMoi.add(rateEvents);
        }
        }
    }
    
    
    
    public List<Long> getRateEventsParticipationsperMonth() {
        return pourcentageParticapationEvenementsParMoi;
    }



    


}
