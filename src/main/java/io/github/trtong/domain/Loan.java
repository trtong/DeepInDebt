package io.github.trtong.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Loan.
 */
@Entity
@Table(name = "loan")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Loan implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "servicer")
    private String servicer;

    @Column(name = "principal")
    private Long principal;

    @Column(name = "interest")
    private Long interest;

    @Column(name = "payment_due_date")
    private LocalDate paymentDueDate;

    @OneToMany(mappedBy = "loan")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Payment> payments = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("loans")
    private User user;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServicer() {
        return servicer;
    }

    public Loan servicer(String servicer) {
        this.servicer = servicer;
        return this;
    }

    public void setServicer(String servicer) {
        this.servicer = servicer;
    }

    public Long getPrincipal() {
        return principal;
    }

    public Loan principal(Long principal) {
        this.principal = principal;
        return this;
    }

    public void setPrincipal(Long principal) {
        this.principal = principal;
    }

    public Long getInterest() {
        return interest;
    }

    public Loan interest(Long interest) {
        this.interest = interest;
        return this;
    }

    public void setInterest(Long interest) {
        this.interest = interest;
    }

    public LocalDate getPaymentDueDate() {
        return paymentDueDate;
    }

    public Loan paymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
        return this;
    }

    public void setPaymentDueDate(LocalDate paymentDueDate) {
        this.paymentDueDate = paymentDueDate;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public Loan payments(Set<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public Loan addPayments(Payment payment) {
        this.payments.add(payment);
        payment.setLoan(this);
        return this;
    }

    public Loan removePayments(Payment payment) {
        this.payments.remove(payment);
        payment.setLoan(null);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public User getUser() {
        return user;
    }

    public Loan user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Loan loan = (Loan) o;
        if (loan.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), loan.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Loan{" +
            "id=" + getId() +
            ", servicer='" + getServicer() + "'" +
            ", principal=" + getPrincipal() +
            ", interest=" + getInterest() +
            ", paymentDueDate='" + getPaymentDueDate() + "'" +
            "}";
    }
}
