package io.github.trtong.deepindebt.repository;

import io.github.trtong.deepindebt.domain.Loan;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data  repository for the Loan entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {

    @Query("select loan from Loan loan where loan.user.login = ?#{principal.username}")
    List<Loan> findByUserIsCurrentUser();

}
