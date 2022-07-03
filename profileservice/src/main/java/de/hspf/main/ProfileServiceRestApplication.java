package de.hspf.main;

import org.eclipse.microprofile.auth.LoginConfig;
import javax.annotation.security.DeclareRoles;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/data")
@ApplicationScoped
@LoginConfig(authMethod = "MP-JWT")
@DeclareRoles({"protected", "user", "admin"})
public class ProfileServiceRestApplication extends Application {
}
