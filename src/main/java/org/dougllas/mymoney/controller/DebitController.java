package org.dougllas.mymoney.controller;

import org.dougllas.mymoney.generic.AbstractCrudRestController;
import org.dougllas.mymoney.model.Debit;
import org.dougllas.mymoney.repository.DebitRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/debits")
public class DebitController extends AbstractCrudRestController<Debit, Integer, DebitRepository> {

}