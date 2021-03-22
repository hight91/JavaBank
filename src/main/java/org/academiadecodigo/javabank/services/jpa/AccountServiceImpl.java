package org.academiadecodigo.javabank.services.jpa;

import org.academiadecodigo.javabank.model.account.AbstractAccount;
import org.academiadecodigo.javabank.model.account.Account;
import org.academiadecodigo.javabank.persistence.dao.jpa.DAO;
import org.academiadecodigo.javabank.persistence.dao.jpa.JpaAccountDao;

import javax.persistence.RollbackException;
import java.util.Optional;

/**
 * A JPA {@link org.academiadecodigo.javabank.services.AccountService} implementation
 */
public class AccountServiceImpl implements org.academiadecodigo.javabank.services.AccountService {

   private DAO<AbstractAccount> accountDAO;


    /**
     * @see org.academiadecodigo.javabank.services.AccountService#deposit(Integer, double)
     */
    @Override
    public void deposit(Integer id, double amount) {
        try {
            Optional<Account> account = Optional.ofNullable(accountDAO.findById(id));
            System.out.println("axxount: "+account);
            if (!account.isPresent()) {
                accountDAO.getTM().rollback();
            }
            System.out.println("account: "+account);

            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).credit(amount);
            save(account.get());
            accountDAO.close();

        } catch (RollbackException ex) {
            accountDAO.getTM().rollback();
        }
        finally{
            accountDAO.close();
        }
    }

    /**
     * @see org.academiadecodigo.javabank.services.AccountService#withdraw(Integer, double)
     */
    @Override
    public void withdraw(Integer id, double amount) {

        try {

            Optional<Account> account = Optional.ofNullable(accountDAO.findById(id));

            if (!account.isPresent()) {
                accountDAO.getTM().rollback();
            }
            account.orElseThrow(() -> new IllegalArgumentException("invalid account id")).debit(amount);
            save(account.get());



        } catch (RollbackException ex) {

            accountDAO.getTM().rollback();
        }
        finally{
            accountDAO.close();
        }
    }

    /**
     * @see org.academiadecodigo.javabank.services.AccountService#transfer(Integer, Integer, double)
     */
    @Override
    public void transfer(Integer srcId, Integer dstId, double amount) {

        try {
            Optional<Account> srcAccount = Optional.ofNullable(accountDAO.findById(srcId));
            Optional<Account> dstAccount = Optional.ofNullable(accountDAO.findById(dstId));

            if (!srcAccount.isPresent() || !dstAccount.isPresent()) {
                accountDAO.getTM().rollback();
            }

            srcAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));
            dstAccount.orElseThrow(() -> new IllegalArgumentException("invalid account id"));

            // make sure transaction can be performed
            if (srcAccount.get().canDebit(amount) && dstAccount.get().canCredit(amount)) {
                srcAccount.get().debit(amount);
                dstAccount.get().credit(amount);
            }

        } catch (RollbackException ex) {

            accountDAO.getTM().rollback();
        }
        finally{
            accountDAO.close();
        }
    }

    @Override
    public Account save(Account account) {
        System.out.println("saving");
        Account savedAccount = accountDAO.saveOrUpdate((AbstractAccount) account);
        System.out.println("afterSaving");
        accountDAO.close();
        return savedAccount;

    }

    @Override
    public void setAccountDAO(DAO<AbstractAccount> accountDAO) {
        this.accountDAO = accountDAO;
    }

    }

