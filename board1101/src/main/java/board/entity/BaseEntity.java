package board.entity;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {
    @CreationTimestamp //생성된 시간을 나타내는? 저장하는? 가져오는?
    @Column(updatable = false)// 수정했을 때는 관여하지 않는다고 설정
    private LocalDateTime createdTime;

    @UpdateTimestamp //업데이트(수정)된 시간을  나타내는? 저장하는? 가져오는?
    @Column(insertable = false)// 입력했을 때는 관여하지 않는다고 설정
    private LocalDateTime updatedTime;
}
