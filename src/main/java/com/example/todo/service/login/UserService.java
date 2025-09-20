package com.example.todo.service.login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.example.todo.controller.login.UserDTO;
import com.example.todo.repository.UserRepository;
import com.example.todo.security.UserPrincipal;
import jakarta.transaction.Transactional;


@Service
public class UserService implements UserDetailsService{
    private final PasswordEncoder passwordEncoder;
    public UserService(@Lazy PasswordEncoder passwordEncoder){
        this.passwordEncoder = passwordEncoder;
    }
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        UserEntity user = userRepository.findByUsername(username);
        if (user == null){
            throw new UsernameNotFoundException("User not found");
        }
        return new UserPrincipal(user);
    }

    //新たにメソッドを追加
    public UserEntity findByUsername(String username){
        return userRepository.findByUsername(username);
    }
    
    @Transactional
    public void save(UserDTO userDTO){
        UserEntity user = new UserEntity();
        user.setUsername(userDTO.getUsername());
        //パスワードをハッシュ化してから保存
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setEmail(userDTO.getEmail());

        userRepository.save(user);
    }

    // ユーザー名を取得
    public String getusername(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return username;
    }

    

    

}
