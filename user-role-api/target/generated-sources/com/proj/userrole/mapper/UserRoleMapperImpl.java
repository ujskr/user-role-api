package com.proj.userrole.mapper;

import com.proj.entity.quiz.UserRole;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2019-05-07T20:33:04+0530",
    comments = "version: 1.0.0.Final, compiler: javac, environment: Java 1.8.0_212 (Oracle Corporation)"
)
@Component
public class UserRoleMapperImpl implements UserRoleMapper {

    @Override
    public UserRole resultSetToUserRole(ResultSet rs) throws SQLException {
        if ( rs == null ) {
            return null;
        }

        UserRole userRole = new UserRole();

        userRole.setUserRole( rs.getString(2)!=null ? rs.getString(2) : null );
        userRole.setUserRoleId( rs.getString(1)!=null ? rs.getString(1) : null );

        return userRole;
    }
}
