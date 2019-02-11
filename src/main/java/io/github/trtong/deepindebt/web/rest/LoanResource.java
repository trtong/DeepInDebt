package io.github.trtong.deepindebt.web.rest;
import io.github.trtong.deepindebt.domain.Loan;
import io.github.trtong.deepindebt.repository.LoanRepository;
import io.github.trtong.deepindebt.web.rest.errors.BadRequestAlertException;
import io.github.trtong.deepindebt.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing Loan.
 */
@RestController
@RequestMapping("/api")
public class LoanResource {

    private final Logger log = LoggerFactory.getLogger(LoanResource.class);

    private static final String ENTITY_NAME = "loan";

    private final LoanRepository loanRepository;

    public LoanResource(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    /**
     * POST  /loans : Create a new loan.
     *
     * @param loan the loan to create
     * @return the ResponseEntity with status 201 (Created) and with body the new loan, or with status 400 (Bad Request) if the loan has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/loans")
    public ResponseEntity<Loan> createLoan(@RequestBody Loan loan) throws URISyntaxException {
        log.debug("REST request to save Loan : {}", loan);
        if (loan.getId() != null) {
            throw new BadRequestAlertException("A new loan cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Loan result = loanRepository.save(loan);
        return ResponseEntity.created(new URI("/api/loans/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /loans : Updates an existing loan.
     *
     * @param loan the loan to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated loan,
     * or with status 400 (Bad Request) if the loan is not valid,
     * or with status 500 (Internal Server Error) if the loan couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/loans")
    public ResponseEntity<Loan> updateLoan(@RequestBody Loan loan) throws URISyntaxException {
        log.debug("REST request to update Loan : {}", loan);
        if (loan.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Loan result = loanRepository.save(loan);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, loan.getId().toString()))
            .body(result);
    }

    /**
     * GET  /loans : get all the loans.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of loans in body
     */
    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        log.debug("REST request to get all Loans");
        return loanRepository.findAll();
    }

    /**
     * GET  /loans/:id : get the "id" loan.
     *
     * @param id the id of the loan to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the loan, or with status 404 (Not Found)
     */
    @GetMapping("/loans/{id}")
    public ResponseEntity<Loan> getLoan(@PathVariable Long id) {
        log.debug("REST request to get Loan : {}", id);
        Optional<Loan> loan = loanRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(loan);
    }

    /**
     * DELETE  /loans/:id : delete the "id" loan.
     *
     * @param id the id of the loan to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/loans/{id}")
    public ResponseEntity<Void> deleteLoan(@PathVariable Long id) {
        log.debug("REST request to delete Loan : {}", id);
        loanRepository.deleteById(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
