package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.Model;
import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;
import org.academiadecodigo.javabank.services.AccountService;

import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * A JPA {@link AccountService} implementation
 */
public class JpaAccountService implements AccountService {

   private JpaAccountDao jpaAccountDao;


    /**
     * @see AccountService#deposit(Integer, double)
     */
    @Override
    public void deposit(Integer id, double amount) {


        try {
            Optional<Account> account = Optional.ofNullable((jpaAccountDao).findById(id));

            if (!account.isPresent()) {
                jpaAccountDao.getJpaTransactionManager().rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).credit(amount);

        } catch (RollbackException ex) {

            jpaAccountDao.getJpaTransactionManager().rollback();

        }
    }

    /**
     * @see AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        try {

            Optional<Account> account = Optional.ofNullable(jpaAccountDao.findById(id));

            if (!account.isPresent()) {
                jpaAccountDao.getJpaTransactionManager().rollback();
            }

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).debit(amount);


        } catch (RollbackException ex) {

            jpaAccountDao.getJpaTransactionManager().rollback();
        }
    }

    /**
     * @see AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {



        try {
            Optional<Account> srcAccount = Optional.ofNullable(jpaAccountDao.findById(srcId));
            Optional<Account> dstAccount = Optional.ofNullable(jpaAccountDao.findById(dstId));

            if (!srcAccount.isPresent() || !dstAccount.isPresent()) {
                jpaAccountDao.getJpaTransactionManager().rollback();
            }

            srcAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));
            dstAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));

            // make sure transaction can be performed
            if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
                srcAccount.get().debit(amount);
                dstAccount.get().credit(amount);
            }

        } catch (RollbackException ex) {

            jpaAccountDao.getJpaTransactionManager().rollback();
        }
    }

    @Override
    public Account save(Account account) {
            jpaAccountDao.saveOrUpdate(account);
        return account;
    }

    public void setJpaAccountDao(JpaAccountDao jpaAccountDao) {
        this.jpaAccountDao = jpaAccountDao;
    }
}
