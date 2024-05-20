package ldubgd.coursework.TestFTE.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "inline_keyboard", schema = "public")
@Data
public class InlineKeyboard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String menu;
    private String keyboard;
    private String callback;
    private Integer sort;
}
