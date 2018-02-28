package org.dougllas.mymoney.service.impl;

import org.dougllas.mymoney.model.BillingCycle;
import org.dougllas.mymoney.model.Credit;
import org.dougllas.mymoney.model.Debit;
import org.dougllas.mymoney.repository.BillingCycleRepository;
import org.dougllas.mymoney.repository.CreditRepository;
import org.dougllas.mymoney.repository.DebitRepository;
import org.dougllas.mymoney.service.BillingCycleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Criado por dougllas.sousa em 28/02/2018.
 */

@Service
public class BillingCycleServiceImpl implements BillingCycleService {

    @Autowired
    private BillingCycleRepository billingCycleRepository;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private DebitRepository debitRepository;

    public void setDebitRepository(DebitRepository debitRepository) {
        this.debitRepository = debitRepository;
    }

    public void setCreditRepository(CreditRepository creditRepository) {
        this.creditRepository = creditRepository;
    }

    public void setBillingCycleRepository(BillingCycleRepository billingCycleRepository) {
        this.billingCycleRepository = billingCycleRepository;
    }

    @Override
    public List<BillingCycle> findAll(boolean fetchLists) {
        List<BillingCycle> all = billingCycleRepository.findAll();

        all.stream().forEach( b -> {
            if(fetchLists) {
                List<Credit> credits = creditRepository.findByBillingCycle(b);
                b.setCredits(credits);

                List<Debit> debits = debitRepository.findByBillingCycle(b);
                b.setDebits(debits);
            }else{
                b.setCredits(Collections.emptyList());
                b.setDebits(Collections.emptyList());
            }
        });

        return all;
    }

    @Override
    public Optional<BillingCycle> findById(Integer id) {
        return Optional.ofNullable(billingCycleRepository.findOne(id));
    }

    @Override
    public BillingCycle save(BillingCycle billingCycle){
        List<Credit> credits = billingCycle.getCredits();
        List<Debit> debits = billingCycle.getDebits();

        BillingCycle save = billingCycleRepository.save(billingCycle);

        credits.forEach( c -> creditRepository.save(c) );
        debits.forEach( d -> debitRepository.save(d) );

        return save;
    }

    @Override
    public void delete(Integer id){
        billingCycleRepository.delete(id);
    }
}
