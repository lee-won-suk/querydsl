package study.querydsl.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
//ToString을 쓸 때는 연관관계 필드는 안쓰는게 좋음. 순환참조 될 수 있어서.
@ToString(of = {"id", "username", "age"})
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    private String username;
    private int age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "team_id")
    private Team team;

    public Member(String username) {
        this(username, 0);
    }

    public Member(String username, int age) {
        this(username, age, null);
    }

    public Member(String username, int age, Team team) {
        this.username = username;
        this.age = age;
        if(team!=null) {
            changeTeam(team);
        }
    }
    //양방향이면 둘다 세팅해주는게 필요.
    public void changeTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
