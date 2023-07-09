package my.copter.persistence.sql.listener;

import jakarta.persistence.PostLoad;

import my.copter.persistence.sql.entity.user.User;

import org.apache.commons.lang3.StringUtils;

public class FullNameGeneratorListener {

    @PostLoad
    public void generateFullName(User user) {
        if (StringUtils.isNotBlank(user.getFirstName()) && StringUtils.isNotBlank(user.getLastName())) {
            user.setFullName(user.getFirstName() + " " + user.getLastName());
        } else {
            user.setFullName("unanimous");
        }
    }
}
