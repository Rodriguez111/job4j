//package sellcars.servlets;
//
//
//
//import java.util.ArrayList;
//
//public class UserService implements UserDetailsService {
//
//    @Override
//    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
//        if("user".equals(login)) {
//            return new User(login, "password", new ArrayList<>());
//        } else {
//            throw new UsernameNotFoundException("User not found with login: " + login);
//        }
//    }
//}
