package it.uniroma3.siw.authentication;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import javax.sql.DataSource;
import static it.uniroma3.siw.model.Credentials.ADMIN_ROLE;


@Configuration
@EnableWebSecurity
public class AuthConfiguration extends WebSecurityConfigurerAdapter {

   
    @Autowired
    DataSource datasource;

 
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                
                .authorizeRequests()
               
                .antMatchers(HttpMethod.GET, "/", "/index", "/login" ,"/prenotazione", "/index/prenotazione/elencoStanze", "/index/prenotazione/elencoStanze/dati", "/index/prenotazione/elencoStanze/dati/registrazione").permitAll()
                .antMatchers(HttpMethod.POST, "/", "/index", "/login" ,"/prenotazione", "/index/prenotazione/elencoStanze", "/index/prenotazione/elencoStanze/dati", "/index/prenotazione/elencoStanze/dati/registrazione").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()

                .antMatchers(HttpMethod.GET, "/segreteriaHome", "/addCamera", "/removeCamera", "/removePrenotazione", "/segreteriaHome/add", "/segreteriaHome/rem", "/segreteriaHome/remPren", "/ricercaCamere", "/segreteriaHome/camere", "/elencoUpdate", "/elencoPrenotazioni", "/elencoUpdate/updateCamera", "/elencoUpdate/updateCamera/updated").hasAnyAuthority(ADMIN_ROLE)
                .antMatchers(HttpMethod.POST, "/segreteriaHome", "/addCamera", "/removeCamera", "/removePrenotazione", "/segreteriaHome/add", "/segreteriaHome/rem", "/segreteriaHome/remPren", "/ricercaCamere", "/segreteriaHome/camere", "/elencoUpdate", "/elencoPrenotazioni", "/elencoUpdate/updateCamera", "/elencoUpdate/updateCamera/updated").hasAnyAuthority(ADMIN_ROLE)
                
                .anyRequest().authenticated()

               
                .and().formLogin()
              
                .defaultSuccessUrl("/adminPage")

                
                .and().logout()
                .logoutUrl("/logout")               
                .logoutSuccessUrl("/index");
        
        
        		
    }

	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
    	
        		auth.jdbcAuthentication().dataSource(this.datasource)
                
                .authoritiesByUsernameQuery("SELECT username, role FROM credentials WHERE username=?")
                
                .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credentials WHERE username=?");
    }

   
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}