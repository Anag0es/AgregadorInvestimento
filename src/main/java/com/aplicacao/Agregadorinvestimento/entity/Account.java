package com.aplicacao.Agregadorinvestimento.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_accounts")
public class Account {

    @Id
    @Column(name = "account_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID accountId;

    @Column(name = "description")
    private String description;

    // muitas contas podem pertencer a um usuario
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // uma conta tem um endereco de cobranca
    // OneToOne -> mapeia um relacionamento um-para-um entre duas entidades
    // PrimaryKeyJoinColumn -> especifica a coluna que sera usada para fazer a juncao entre as entidades
    @OneToOne(mappedBy = "account")
    @PrimaryKeyJoinColumn
    private BillingAddress billingAddress;

    // uma conta pode ter varios stocks
    @OneToMany(mappedBy = "account")
    private List<AccountStock> accountStocks;

    public Account() {
    }

    public Account(UUID accountId, String description) {
        this.accountId = accountId;
        this.description = description;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void setAccountId(UUID accountId) {
        this.accountId = accountId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
