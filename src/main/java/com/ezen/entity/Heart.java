package com.ezen.entity;

import jakarta.persistence.*;
import lombok.*;

@ToString(exclude = {"member", "recipe"})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Heart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long heartSeq;

    @ManyToOne
    @JoinColumn(name="username", nullable=false, updatable=false)
    private Member member; // 좋아요 누른 멤버

    public void setMember(Member member) {
        this.member = member;
        member.getHeartList().add(this);
    }

    @ManyToOne
    @JoinColumn(name="recipe_seq", nullable=false, updatable=false)
    private Recipe recipe; // 좋아요 누른 레시피

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.getHeartList().add(this);
    }

}
