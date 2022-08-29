package payment.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    @Id
    @Column(unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seq;

    @Column(unique = true, nullable = false)
    private String userId;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String signupType;

    @Column
    private String registDate;

    @Column
    private String updateDate;

    @Setter
    @Column
    private String deviceToken;

    @Setter
    @Column
    private String activateYn;

    @Setter
    @Column
    private String reportedYn;

    @Setter
    @Column
    private String crdiYn;

    @Column
    private String role;

    @Setter
    @Column(length = 1000)
    private String refreshToken;

    public List<String> getRoles() {
        return new ArrayList<>(Collections.singleton(role));
    }

    public Member(String email) {this.email = email;}

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<Payment> payments = new ArrayList<>();
    public void addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setCustomer(this);
    }

    @OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @Builder.Default
    private List<CancelPayment> cancelPayments = new ArrayList<>();
    public void addCancelPayment(CancelPayment cancelPayment) {
        this.cancelPayments.add(cancelPayment);
        cancelPayment.setCustomer(this);
    }
}
