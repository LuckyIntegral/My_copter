package my.copter.persistence.sql.entity.token;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import my.copter.persistence.sql.entity.user.User;
import my.copter.persistence.sql.type.TokenType;

@Data
@Entity
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class Token {

    @Id
    @GeneratedValue
    public Integer id;

    @Column(unique = true)
    public String token;

    @Enumerated(EnumType.STRING)
    public TokenType tokenType = TokenType.BEARER;

    public boolean revoked;

    public boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
