package com.sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
    UserDetailsService accountService;
	
    @Autowired
    LoginSuccessHandler loginSuccessHandler;

    @Autowired
    LoginFaildHandler loginFaildHandler;

	/**
     * Web全般のセキュリティ
     */
    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/resources/**"); // resources配下は無視
    }

	/**
	 * http通信のセキュリティ  
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	// 認証状態による画面表示の設定
    	http.authorizeRequests()
    		.antMatchers("/", "/search", "/signin", "/signup").permitAll()	//ログインしなくても画面表示できるPATH
    		.anyRequest().authenticated();	//その他のリクエストは認証が必要    		
    	
    	// ログイン処理の設定
        http.formLogin()
	        .loginPage("/signin") // ログイン画面のパス
            .defaultSuccessUrl("/")	//ログイン成功時のデフォルトリダイレクト先
	        .successHandler(loginSuccessHandler)	//ログイン成功時の処理
            .failureHandler(loginFaildHandler)	//ログイン失敗時の処理
            .usernameParameter("username")	// emailをログインに必要な値に設定
	        .passwordParameter("password")	// パスワード
	        .permitAll();
        
        // ログアウト処理の設定
        http.logout()
	        .logoutRequestMatcher(new AntPathRequestMatcher("/signout")) // ログアウト処理を起動させるパス
	        .logoutSuccessUrl("/") // ログアウト成功時の遷移先
	        .deleteCookies("JSESSIONID", "SESSION")	//ログアウト時にcookieのJSESSIONIDを削除
	        .invalidateHttpSession(true) // ログアウト時にセッションを無効にする
	        .permitAll();
    }
    
    /**
     * 認証の設定
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(accountService);
    }

}