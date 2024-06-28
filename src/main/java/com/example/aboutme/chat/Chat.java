package com.example.aboutme.chat;

import com.example.aboutme.counsel.Counsel;
import com.example.aboutme.user.User;
import com.example.aboutme.voucher.Voucher;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "chat_tb")
public class Chat {

    // 필수 입력
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "counsel_id")
    private Counsel counsel; // 바우처

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="user_id" )
    private User user; //클라이언트


    @Column(nullable = false)
    private String content; // 컨텐츠

    @Builder
    public Chat(Integer id, User user,String content, Counsel counsel) {
        this.id = id;
        this.user = user;
        this.counsel = counsel;
        this.content = content;
    }

}
