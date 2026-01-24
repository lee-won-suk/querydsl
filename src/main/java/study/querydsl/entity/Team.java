package study.querydsl.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "name" })
public class Team {
    @Id @GeneratedValue
    private Long id;
    private String name;

    // 초기화 안하면 NPE 가능하므로 초기화
    @OneToMany(mappedBy = "team")
    public List<Member> members = new ArrayList<>();

    public Team(String name) {
        this.name = name;
    }
}
